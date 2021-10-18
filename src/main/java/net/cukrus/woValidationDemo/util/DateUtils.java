package net.cukrus.woValidationDemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String DEFAULT_DATE_FORMAT_STR = "yyyy-MM-dd";
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT_STR);

    public static Date dateFromString(String dateStr) {
        if(dateStr != null) {
            try {
                return simpleDateFormat.parse(dateStr);
            } catch (ParseException e) {
                //ignore error
            }
        }
        return null;
    }

    public static String dateToString(Date date) {
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return null;
    }

    public static Date dateFromDate(Date date) {
        return dateFromString(dateToString(date));
    }
}
