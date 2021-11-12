package com.example.core.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean转换工具类
 */
public class BeanUtils {

    public static final String PROPERTY_NAME = "context.key.lowercase";
    public static boolean LOWERCASE = false;
    private static String DATEFORMAT = "yyyy-MM-dd";
    private static String TIMEFORMAT = "yyyy-MM-dd hh:mm:ss.S";
    private static ConcurrentHashMap customEditors = new ConcurrentHashMap();

    static {
        String lowercase = System.getProperty(PROPERTY_NAME, "true");
        LOWERCASE = Boolean.valueOf(lowercase).booleanValue();
        registerCustomEditor(Date.class, new CustomPropertyEditor() {
            public Object getValue() {
                return toDate(value);
            }
        });
        registerCustomEditor(Timestamp.class, new CustomPropertyEditor() {
            public Object getValue() {
                return toTimestamp(value);
            }
        });
        registerCustomEditor(java.sql.Date.class, new CustomPropertyEditor() {
            public Object getValue() {
                return toSqlDate(value);
            }
        });
        registerCustomEditor(Time.class, new CustomPropertyEditor() {
            public Object getValue() {
                return toTime(value);
            }
        });
    }

    public BeanUtils() {
    }

    private static class CustomPropertyEditor extends PropertyEditorSupport {
        protected Object value;

        public void setAsText(String text) throws IllegalArgumentException {
            value = text;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        private CustomPropertyEditor() {
        }
    }

    private static void registerCustomEditor(Class clazz, PropertyEditor editor) {
        customEditors.put(clazz, editor);
    }

    /**
     * 获取转换后的名字
     * @param name
     * @return
     */
    public static String getConvertedName(String name) {
        if (name == null || name.length() == 0 || LOWERCASE)
            return name;
        else
            return (new StringBuilder())
                    .append(Character.toUpperCase(name.charAt(0)))
                    .append(name.substring(1))
                    .toString();
    }

    /**
     * bean转bean
      * @param srcBeanObject
     * @param clazz 目标bean
     * @return
     */
    public static <T> T beanToBean(Object srcBeanObject, Class<T> clazz) {
        try {
            T t = clazz.getConstructor().newInstance();
            if (srcBeanObject instanceof List)
                listToBean((List) srcBeanObject, t, "list");
            else
                beanToBean(srcBeanObject, t);
            return t;
        } catch (Exception e) {
            throw new RuntimeException("Util.BeanToMap Fail", e);
        }
    }

    /**
     * bean转bean
     * @param srcBeanObject  源bean
     * @param covBeanObject 目标bean
     * @return
     */
    public static void beanToBean(Object srcBeanObject, Object covBeanObject) {
        BeanWrapperImpl srcBean = new BeanWrapperImpl(srcBeanObject);
        BeanWrapperImpl covBean = new BeanWrapperImpl(covBeanObject);
        PropertyDescriptor covDesc[] = covBean.getPropertyDescriptors();
        try {
            for (int i = 0; i < covDesc.length; i++) {
                String name = covDesc[i].getName();
                if (covBean.isWritableProperty(name) && srcBean.isReadableProperty(name)) {
                    Object srcValue = srcBean.getPropertyValue(name);
                    if (srcValue != null)
                        covBean.setPropertyValue(name, srcValue);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Util.BeanToMap Fail", e);
        }
    }

    /**
     * map转bean
     * @param map 源map
     * @param obj 转换bean
     * @return
     */
    public static Object mapToBean(Map map, Object obj) {
        try {
            BeanWrapper covBean = new BeanWrapperImpl(obj);
            return mapToCovBean(map, covBean);
        }catch (Exception e){
            throw new RuntimeException("Util.MapToBean Fail", e);
        }
    }

    /**
     *  map转bean
     * @param map 源map
     * @param clazz 需要转换的bean
     * @return
     */
    public static <T> T mapToBean(Map map, Class<T> clazz) {
        try {
            T t = clazz.getConstructor().newInstance();
            BeanWrapper covBean = new BeanWrapperImpl(t);
            Map.Entry en;
            for (Iterator iterator = customEditors.entrySet().iterator();
                 iterator.hasNext();
                 covBean.registerCustomEditor((Class) en.getKey(), (PropertyEditor) en.getValue()))
                en = (Map.Entry) iterator.next();
            mapToCovBean(map, covBean);
            return t;
        }catch (Exception e){
            throw new RuntimeException("Util.BeanToMap Fail", e);
        }


    }

    /**
     * map内部转换bean
     * @param map
     * @param covBean
     * @return
     */
    private static Object mapToCovBean(Map map, BeanWrapper covBean) {
        PropertyDescriptor props[] = covBean.getPropertyDescriptors();
        for (int j = 0; j < props.length; j++) {
            PropertyDescriptor prop = props[j];
            String name = prop.getName();
            if (!covBean.isWritableProperty(name) || !covBean.isReadableProperty(name))
                continue;
            Class cls = prop.getPropertyType();
            String convertedName = null;
            Object value;
            if (Enum.class.isAssignableFrom(cls)) {
                convertedName = getConvertedName(name);
                value = map.get(convertedName);
                if (value == null)
                    continue;
                if (value.getClass() == cls) {
                    covBean.setPropertyValue(name, value);
                    continue;
                }
                String enumValue = String.valueOf(value);
                if (enumValue.length() > 0) {
                    Enum v = Enum.valueOf(cls, enumValue);
                    covBean.setPropertyValue(name, v);
                }
                continue;
            }
            convertedName = getConvertedName(name);
            value = map.get(convertedName);
            if (value != null)
                covBean.setPropertyValue(name, value);
        }

        return covBean.getWrappedInstance();
    }

    /**
     * bean转map
     * @param covBeanObject 转换后的bean
     * @return
     */
    public static Map beanToMap(Object covBeanObject) {
        BeanWrapperImpl covBean = new BeanWrapperImpl(covBeanObject);
        PropertyDescriptor covDesc[] = covBean.getPropertyDescriptors();
        Map dataMap = new HashMap(covDesc.length);
        try {
            for (int i = 0; i < covDesc.length; i++) {
                String name = covDesc[i].getName();
                if (!covBean.isWritableProperty(name) || !covBean.isReadableProperty(name))
                    continue;
                Object object = covBean.getPropertyValue(name);
                if (object != null) {
                    String convertedName = getConvertedName(name);
                    dataMap.put(convertedName, object);
                }
            }
            return dataMap;
        } catch (Exception e) {
            throw new RuntimeException("Util.BeanToMap Fail", e);
        }
    }

    /**
     * List<Bean>转List<Map>
     * @param list
     * @return
     */
    public static <T> List<Map> listBeanToListMap(List<T> list) {
        try {
            List<Map> result = new ArrayList<Map>();
            Map tmp;
            for (Iterator it = list.iterator();
                 it.hasNext();
                 result.add(tmp))
                tmp = beanToMap(it.next());
            return result;
        }catch (Exception e){
            throw new RuntimeException("Util.ListBeanToListMap Fail", e);
        }
    }

    /**
     * List<Map>转List<Bean>
     * @param list
     * @param clazz
     * @return
     */
    public static <T> List<T> listMapToListBean(List list, Class<T> clazz) {
        try {
            List<T> result = new ArrayList<T>();
            Object obj;
            for (Iterator it = list.iterator(); it.hasNext(); result.add((T) obj))
                obj = mapToBean((Map) it.next(), clazz);
            return result;
        }catch (Exception e){
            throw new RuntimeException("Util.ListMapToListBean Fail", e);
        }
    }

    public static void listToBean(List srcBeanObject, Object covBeanObject, String listPropName) {
        try {
            BeanWrapperImpl covBean = new BeanWrapperImpl(covBeanObject);
            covBean.setPropertyValue(listPropName, srcBeanObject);
        }catch (Exception e){
            throw new RuntimeException("Util.ListToBean Fail", e);
        }
    }

    private static Date toDate(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == java.sql.Date.class) {
            return (Date)obj;
        } else if (Date.class.isAssignableFrom(obj.getClass())) {
            return new Date(((Date)obj).getTime());
        } else if (Number.class.isAssignableFrom(obj.getClass())) {
            return new Date(((Number)obj).longValue());
        } else if (obj.getClass() == Long.TYPE) {
            return new Date((Long)obj);
        } else if (obj.getClass() == Integer.TYPE) {
            return new Date((long)(Integer)obj);
        } else {
            String str = String.valueOf(obj);

            try {
                long mills = Long.parseLong(str);
                return new Date(mills);
            } catch (NumberFormatException var5) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);

                try {
                    return new Date(sdf.parse(str).getTime());
                } catch (ParseException var4) {
                    throw new RuntimeException(var4.getMessage(), var4);
                }
            }
        }
    }

    private static Timestamp toTimestamp(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Timestamp.class) {
            return (Timestamp)obj;
        } else if (Date.class.isAssignableFrom(obj.getClass())) {
            return new Timestamp(((Date)obj).getTime());
        } else if (Number.class.isAssignableFrom(obj.getClass())) {
            return new Timestamp(((Number)obj).longValue());
        } else if (obj.getClass() == Long.TYPE) {
            return new Timestamp((Long)obj);
        } else if (obj.getClass() == Integer.TYPE) {
            return new Timestamp((long)(Integer)obj);
        } else {
            String str = String.valueOf(obj);

            try {
                long mills = Long.parseLong(str);
                return new Timestamp(mills);
            } catch (NumberFormatException var5) {
                SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT);

                try {
                    return new Timestamp(sdf.parse(str).getTime());
                } catch (ParseException var4) {
                    throw new RuntimeException(var4.getMessage(), var4);
                }
            }
        }
    }

    private static java.sql.Date toSqlDate(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == java.sql.Date.class) {
            return (java.sql.Date)obj;
        } else if (Date.class.isAssignableFrom(obj.getClass())) {
            return new java.sql.Date(((Date)obj).getTime());
        } else if (Number.class.isAssignableFrom(obj.getClass())) {
            return new java.sql.Date(((Number)obj).longValue());
        } else if (obj.getClass() == Long.TYPE) {
            return new java.sql.Date((Long)obj);
        } else if (obj.getClass() == Integer.TYPE) {
            return new java.sql.Date((long)(Integer)obj);
        } else {
            String str = String.valueOf(obj);

            try {
                long mills = Long.parseLong(str);
                return new java.sql.Date(mills);
            } catch (NumberFormatException var5) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);

                try {
                    return new java.sql.Date(sdf.parse(str).getTime());
                } catch (ParseException var4) {
                    throw new RuntimeException(var4.getMessage(), var4);
                }
            }
        }
    }

    private static Time toTime(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Time.class) {
            return (Time)obj;
        } else if (Date.class.isAssignableFrom(obj.getClass())) {
            return new Time(((Date)obj).getTime());
        } else if (Number.class.isAssignableFrom(obj.getClass())) {
            return new Time(((Number)obj).longValue());
        } else if (obj.getClass() == Long.TYPE) {
            return new Time((Long)obj);
        } else if (obj.getClass() == Integer.TYPE) {
            return new Time((long)(Integer)obj);
        } else {
            String str = String.valueOf(obj);

            try {
                long mills = Long.parseLong(str);
                return new Time(mills);
            } catch (NumberFormatException var5) {
                SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT);

                try {
                    return new Time(sdf.parse(str).getTime());
                } catch (ParseException var4) {
                    throw new RuntimeException(var4.getMessage(), var4);
                }
            }
        }
    }
}