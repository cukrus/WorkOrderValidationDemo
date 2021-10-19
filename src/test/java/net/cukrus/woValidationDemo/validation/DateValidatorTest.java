package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.util.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateValidatorTest {
    @Test
    void before_basicSuccess() {
        //given
        Date toValidate = DateUtils.dateFromString("2020-08-13");
        Date toCompare = DateUtils.dateFromString("2020-08-14");

        //when
        String result = DateValidator.before(toValidate, toCompare).validate();

        //then
        assertNull(result, "For validation to succeed result should be null");
    }

    @Test
    void after_basicSuccess() {
        //given
        Date toValidate = DateUtils.dateFromString("2020-08-14");
        Date toCompare = DateUtils.dateFromString("2020-08-13");

        //when
        String result = DateValidator.after(toValidate, toCompare).validate();

        //then
        assertNull(result, "For validation to succeed result should be null");
    }

    @Test
    void between_basicSuccess() {
        //given
        Date toValidate = DateUtils.dateFromString("2020-08-15");
        Date toCompare1 = DateUtils.dateFromString("2020-08-14");
        Date toCompare2 = DateUtils.dateFromString("2020-08-16");

        //when
        String result = DateValidator.between(toValidate, toCompare1, toCompare2).validate();

        //then
        assertNull(result, "For validation to succeed result should be null");
    }
}