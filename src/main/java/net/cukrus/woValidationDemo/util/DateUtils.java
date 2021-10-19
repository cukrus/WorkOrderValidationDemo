package net.cukrus.woValidationDemo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * contains convenient methods for Date manipulations
 */
public class DateUtils {
    public static final String DEFAULT_DATE_FORMAT_STR = "yyyy-MM-dd";
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT_STR);

    /**
     * null and error safe method for creating Date from a <i>yyyy-MM-dd</i> string pattern
     * @param dateStr String representation of a date in <i>yyyy-MM-dd</i> pattern
     * @return Date object created from the given <i>dateStr</i> or null if any errors occurred
     */
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

    /**
     * method for transforming a Date object to String in <i>yyyy-MM-dd</i> pattern
     * @param date Date to transform into String
     * @return String object created from the given <i>date</i> in <i>yyyy-MM-dd</i> pattern or null if <i>date</i> is null
     */
    public static String dateToString(Date date) {
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return null;
    }

    /**
     * method for transforming a Date object to a new Date with time precision of a day
     * @param date Date to transform into another Date with no time set
     * @return new Date object representing the same date but with nullified time
     */
    public static Date dateFromDate(Date date) {
        return dateFromString(dateToString(date));
    }
}
