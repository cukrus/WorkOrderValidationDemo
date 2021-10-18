package net.cukrus.woValidationDemo.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void dateFromString_blankStrReturnNull() {
        assertNull(DateUtils.dateFromString(" "));
    }

    @Test
    void dateFromString_nullStrReturnNull() {
        assertNull(DateUtils.dateFromString(null));
    }

    @Test
    void dateFromString_success() {
        //given
        String dateStr = "2020-05-15";

        //when
        Date result = DateUtils.dateFromString(dateStr);

        //then
        assertNotNull(result);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(result);
        assertEquals(2020, calendar.get(Calendar.YEAR));
        assertEquals(4, calendar.get(Calendar.MONTH));
        assertEquals(15, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(dateStr, new SimpleDateFormat(DateUtils.DEFAULT_DATE_FORMAT_STR).format(result));
    }

    @Test
    void dateToString_nullDateReturnNull() {
        assertNull(DateUtils.dateToString(null));
    }

    @Test
    void dateToString_success() {
        //given
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        String expected = "" + calendar.get(Calendar.YEAR) + "-" + (currentMonth < 10 ? "0" : "") + currentMonth + "-" + calendar.get(Calendar.DAY_OF_MONTH);

        //when
        String result = DateUtils.dateToString(date);

        //then
        assertEquals(expected, result);
    }

    @Test
    void dateFromDate_nullDateReturnNull() {
        assertNull(DateUtils.dateFromDate(null));
    }

    @Test
    void dateFromDate_success() throws ParseException {
        //given
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        String expectedStr = "" + calendar.get(Calendar.YEAR) + "-" + (currentMonth < 10 ? "0" : "") + currentMonth + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        Date expected = new SimpleDateFormat(DateUtils.DEFAULT_DATE_FORMAT_STR).parse(expectedStr);

        //when
        Date result = DateUtils.dateFromDate(date);

        //then
        assertEquals(expected, result);
    }
}