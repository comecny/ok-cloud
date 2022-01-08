package com.example.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import java.util.Date;

public class DateUtils {

  public static final String DATE_FORMATTER1 = "yyyy-MM-dd";
  public static final String DATE_FORMATTER2 = "yyyy/MM/dd";
  public static final String DATE_FORMATTER3 = "yyyyMMdd";
  public static final String DATE_FORMATTER4 = "MMdd";
  public static final String DATE_FORMATTER5 = "yyMMdd";
  public static final String DATETIME_FORMATTER1 = "yyyy-MM-dd HH:mm:ss";
  public static final String DATETIME_FORMATTER2 = "yyyyMMdd HH:mm:ss";
  public static final String DATETIME_FORMATTER3 = "yyyyMMddHHmmss";
  public static final String DATETIME_FORMATTER4 = "yyyyMMddHHmmssSSS";
  public static final String DATETIME_FORMATTER5 = "yyyy/MM/dd HH:mm:ss";
  public static final String TIME_FORMATTER1 = "HH:mm:ss";
  public static final String TIME_FORMATTER2 = "HHmmss";
  public static final String TIME_FORMATTER3 = "HHmmssS";

    public static String getCurrentTime() {
        DateTime dateTime = new DateTime();
        return dateTime.toString(DATETIME_FORMATTER1);
    }

    public static Date format(String date, String pattern) {
        if (date == null) {
            return null;
        }
        DateTime dateTime = DateTimeFormat.forPattern(pattern).parseDateTime(date);
        return dateTime.toDate();
    }

    public static String format(Date date, String pattern) {
        if (date == null && StringUtils.isEmpty(pattern)) {
            return null;
        }
        return new DateTime(date).toString(pattern);
    }


    public static void main(String[] args) throws Exception{
        System.out.println(getCurrentTime());
        System.out.println(format(getCurrentTime(),DATE_FORMATTER1));

    }
}
