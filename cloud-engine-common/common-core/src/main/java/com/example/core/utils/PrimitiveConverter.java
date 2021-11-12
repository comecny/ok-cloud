package com.example.core.utils;

import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrimitiveConverter {
    private static String DATEFORMAT = "yyyy-MM-dd";
    private static String TIMEFORMAT = "yyyy-MM-dd hh:mm:ss.S";

    public PrimitiveConverter() {
    }

    public static <T extends Number> T toNumber(Object obj, Class<T> targetClass) {
        Assert.notNull(obj, "Number must not be null");
        Assert.notNull(targetClass, "Target class must not be null");
        if (obj instanceof Number) {
            T value = NumberUtils.convertNumberToTargetClass((Number)obj, targetClass);
            return value;
        } else {
            return NumberUtils.parseNumber(String.valueOf(obj), targetClass);
        }
    }

    public static byte toByte(Object obj) {
        return (Byte)toNumber(obj, Byte.class);
    }

    public static char toChar(Object obj) {
        Assert.notNull(obj, "Char must not be null");
        if (obj.getClass() != Character.TYPE && obj.getClass() != Character.class) {
            String s = String.valueOf(obj);
            return s.charAt(0);
        } else {
            return (Character)obj;
        }
    }

    public static boolean toBoolean(Object obj) {
        if (obj == null) {
            return false;
        } else {
            return obj.getClass() != Boolean.TYPE && obj.getClass() != Boolean.class ? Boolean.parseBoolean(String.valueOf(obj)) : (Boolean)obj;
        }
    }

    public static int toInt(Object obj) {
        return (Integer)toNumber(obj, Integer.class);
    }

    public static short toShort(Object obj) {
        return (Short)toNumber(obj, Short.class);
    }

    public static long toLong(Object obj) {
        return (Long)toNumber(obj, Long.class);
    }

    public static float toFloat(Object obj) {
        return (Float)toNumber(obj, Float.class);
    }

    public static double toDouble(Object obj) {
        return (Double)toNumber(obj, Double.class);
    }

    public static int[] toIntArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == int[].class) {
            return (int[])((int[])obj);
        } else if (obj.getClass().isArray()) {
            int[] result = new int[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toInt(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new int[]{toInt(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            int[] result = new int[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toInt(it.next())) {
            }

            return result;
        }
    }

    public static byte[] toByteArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == byte[].class) {
            return (byte[])((byte[])obj);
        } else if (obj.getClass().isArray()) {
            byte[] result = new byte[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toByte(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new byte[]{toByte(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            byte[] result = new byte[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toByte(it.next())) {
            }

            return result;
        }
    }

    public static char[] toCharArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == char[].class) {
            return (char[])((char[])obj);
        } else if (obj.getClass().isArray()) {
            char[] result = new char[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toChar(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new char[]{toChar(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            char[] result = new char[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toChar(it.next())) {
            }

            return result;
        }
    }

    public static boolean[] toBooleanArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == boolean[].class) {
            return (boolean[])((boolean[])obj);
        } else if (obj.getClass().isArray()) {
            boolean[] result = new boolean[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toBoolean(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new boolean[]{toBoolean(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            boolean[] result = new boolean[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toBoolean(it.next())) {
            }

            return result;
        }
    }

    public static short[] toShortArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == short[].class) {
            return (short[])((short[])obj);
        } else if (obj.getClass().isArray()) {
            short[] result = new short[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toShort(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new short[]{toShort(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            short[] result = new short[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toShort(it.next())) {
            }

            return result;
        }
    }

    public static long[] toLongArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == long[].class) {
            return (long[])((long[])obj);
        } else if (obj.getClass().isArray()) {
            long[] result = new long[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toLong(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new long[]{toLong(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            long[] result = new long[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toLong(it.next())) {
            }

            return result;
        }
    }

    public static float[] toFloatArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == float[].class) {
            return (float[])((float[])obj);
        } else if (obj.getClass().isArray()) {
            float[] result = new float[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toFloat(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new float[]{toFloat(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            float[] result = new float[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toFloat(it.next())) {
            }

            return result;
        }
    }

    public static double[] toDoubleArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == double[].class) {
            return (double[])((double[])obj);
        } else if (obj.getClass().isArray()) {
            double[] result = new double[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toDouble(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new double[]{toDouble(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            double[] result = new double[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toDouble(it.next())) {
            }

            return result;
        }
    }

    public static BigDecimal toBigDecimal(Object obj) {
        return (BigDecimal)toNumber(obj, BigDecimal.class);
    }

    public static BigInteger toBigInteger(Object obj) {
        return (BigInteger)toNumber(obj, BigInteger.class);
    }

    public static BigDecimal[] toBigDecimalArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == BigDecimal[].class) {
            return (BigDecimal[])((BigDecimal[])obj);
        } else if (obj.getClass().isArray()) {
            BigDecimal[] result = new BigDecimal[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toBigDecimal(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new BigDecimal[]{toBigDecimal(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            BigDecimal[] result = new BigDecimal[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toBigDecimal(it.next())) {
            }

            return result;
        }
    }

    public static BigInteger[] toBigBigIntegerArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == BigInteger[].class) {
            return (BigInteger[])((BigInteger[])obj);
        } else if (obj.getClass().isArray()) {
            BigInteger[] result = new BigInteger[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toBigInteger(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new BigInteger[]{toBigInteger(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            BigInteger[] result = new BigInteger[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toBigInteger(it.next())) {
            }

            return result;
        }
    }

    public static Object toPrimitive(Object obj, Class<?> clazz) {
        if (obj == null) {
            return null;
        } else if (clazz != Byte.TYPE && clazz != Byte.class) {
            if (clazz != Character.TYPE && clazz != Character.class) {
                if (clazz != Boolean.TYPE && clazz != Boolean.class) {
                    if (clazz != Short.TYPE && clazz != Short.class) {
                        if (clazz != Integer.TYPE && clazz != Integer.class) {
                            if (clazz != Long.TYPE && clazz != Long.class) {
                                if (clazz != Float.TYPE && clazz != Float.class) {
                                    if (clazz != Double.TYPE && clazz != Double.class) {
                                        if (clazz == BigDecimal.class) {
                                            return toBigDecimal(obj);
                                        } else if (clazz == BigInteger.class) {
                                            return toBigInteger(obj);
                                        } else if (clazz == Date.class) {
                                            return toSqlDate(obj);
                                        } else if (clazz == java.util.Date.class) {
                                            return toDate(obj);
                                        } else if (clazz == Timestamp.class) {
                                            return toTimestamp(obj);
                                        } else {
                                            return clazz == Time.class ? toTime(obj) : obj;
                                        }
                                    } else {
                                        return toDouble(obj);
                                    }
                                } else {
                                    return toFloat(obj);
                                }
                            } else {
                                return toLong(obj);
                            }
                        } else {
                            return toInt(obj);
                        }
                    } else {
                        return toShort(obj);
                    }
                } else {
                    return toBoolean(obj);
                }
            } else {
                return toChar(obj);
            }
        } else {
            return toByte(obj);
        }
    }

    public static Date toSqlDate(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Date.class) {
            return (Date)obj;
        } else if (java.util.Date.class.isAssignableFrom(obj.getClass())) {
            return new Date(((java.util.Date)obj).getTime());
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

    public static Date[] toSqlDateArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Date[].class) {
            return (Date[])((Date[])obj);
        } else if (obj.getClass().isArray()) {
            Date[] array = new Date[Array.getLength(obj)];

            for(int i = 0; i < array.length; ++i) {
                array[i] = toSqlDate(Array.get(obj, i));
            }

            return array;
        } else if (!(obj instanceof Collection)) {
            return new Date[]{toSqlDate(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            Date[] array = new Date[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); array[i++] = toSqlDate(it.next())) {
            }

            return array;
        }
    }

    public static java.util.Date toDate(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Date.class) {
            return (java.util.Date)obj;
        } else if (java.util.Date.class.isAssignableFrom(obj.getClass())) {
            return new java.util.Date(((java.util.Date)obj).getTime());
        } else if (Number.class.isAssignableFrom(obj.getClass())) {
            return new java.util.Date(((Number)obj).longValue());
        } else if (obj.getClass() == Long.TYPE) {
            return new java.util.Date((Long)obj);
        } else if (obj.getClass() == Integer.TYPE) {
            return new java.util.Date((long)(Integer)obj);
        } else {
            String str = String.valueOf(obj);

            try {
                long mills = Long.parseLong(str);
                return new java.util.Date(mills);
            } catch (NumberFormatException var5) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);

                try {
                    return new java.util.Date(sdf.parse(str).getTime());
                } catch (ParseException var4) {
                    throw new RuntimeException(var4.getMessage(), var4);
                }
            }
        }
    }

    public static java.util.Date[] toDateArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == java.util.Date[].class) {
            return (java.util.Date[])((java.util.Date[])obj);
        } else if (obj.getClass().isArray()) {
            java.util.Date[] array = new java.util.Date[Array.getLength(obj)];

            for(int i = 0; i < array.length; ++i) {
                array[i] = toDate(Array.get(obj, i));
            }

            return array;
        } else if (!(obj instanceof Collection)) {
            return new java.util.Date[]{toDate(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            java.util.Date[] array = new java.util.Date[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); array[i++] = toDate(it.next())) {
            }

            return array;
        }
    }

    public static Timestamp toTimestamp(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Timestamp.class) {
            return (Timestamp)obj;
        } else if (java.util.Date.class.isAssignableFrom(obj.getClass())) {
            return new Timestamp(((java.util.Date)obj).getTime());
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

    public static Timestamp[] toTimestampArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Timestamp[].class) {
            return (Timestamp[])((Timestamp[])obj);
        } else if (obj.getClass().isArray()) {
            Timestamp[] array = new Timestamp[Array.getLength(obj)];

            for(int i = 0; i < array.length; ++i) {
                array[i] = toTimestamp(Array.get(obj, i));
            }

            return array;
        } else if (!(obj instanceof Collection)) {
            return new Timestamp[]{toTimestamp(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            Timestamp[] array = new Timestamp[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); array[i++] = toTimestamp(it.next())) {
            }

            return array;
        }
    }

    public static Time toTime(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Time.class) {
            return (Time)obj;
        } else if (java.util.Date.class.isAssignableFrom(obj.getClass())) {
            return new Time(((java.util.Date)obj).getTime());
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

    public static Time[] toTimeArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == Time[].class) {
            return (Time[])((Time[])obj);
        } else if (obj.getClass().isArray()) {
            Time[] array = new Time[Array.getLength(obj)];

            for(int i = 0; i < array.length; ++i) {
                array[i] = toTime(Array.get(obj, i));
            }

            return array;
        } else if (!(obj instanceof Collection)) {
            return new Time[]{toTime(obj)};
        } else {
            Collection<?> c = (Collection)obj;
            Time[] array = new Time[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); array[i++] = toTime(it.next())) {
            }

            return array;
        }
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof String) {
            return (String)obj;
        } else {
            return obj instanceof BigDecimal ? ((BigDecimal)obj).toPlainString() : obj.toString();
        }
    }

    public static String[] toStringArray(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj.getClass() == String[].class) {
            return (String[])((String[])obj);
        } else if (obj.getClass().isArray()) {
            String[] result = new String[Array.getLength(obj)];

            for(int i = 0; i < result.length; ++i) {
                result[i] = toString(Array.get(obj, i));
            }

            return result;
        } else if (!(obj instanceof Collection)) {
            return new String[]{obj.toString()};
        } else {
            Collection<?> c = (Collection)obj;
            String[] result = new String[c.size()];
            int i = 0;

            for(Iterator it = c.iterator(); it.hasNext(); result[i++] = toString(it.next())) {
            }

            return result;
        }
    }

    public static Object toCollection(Object obj, Class<? extends Collection> clazz) {
        if (obj == null) {
            return null;
        } else if (clazz.isAssignableFrom(obj.getClass())) {
            return obj;
        } else {
            if (clazz.isInterface()) {
                if (clazz != List.class && clazz != Collection.class) {
                    if (clazz == Set.class) {
                        clazz = LinkedHashSet.class;
                    }
                } else {
                    clazz = ArrayList.class;
                }
            } else if (Modifier.isAbstract(clazz.getModifiers())) {
                if (clazz != AbstractList.class && clazz != AbstractCollection.class) {
                    if (clazz == AbstractSet.class) {
                        clazz = LinkedHashSet.class;
                    }
                } else {
                    clazz = ArrayList.class;
                }
            }

            try {
                Collection result = (Collection)clazz.newInstance();
                if (obj.getClass().isArray()) {
                    for(int i = 0; i < Array.getLength(obj); ++i) {
                        result.add(Array.get(obj, i));
                    }
                } else if (Collection.class.isAssignableFrom(obj.getClass())) {
                    result.addAll((Collection)obj);
                } else {
                    result.add(obj);
                }

                return result;
            } catch (Throwable var4) {
                return null;
            }
        }
    }

}
