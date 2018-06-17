package com.wombat.blw.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String FORMAT_YMDTHM = "yyyy-mm-dd'T'hh:mm";

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String date2Str(Date d) {
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }
}
