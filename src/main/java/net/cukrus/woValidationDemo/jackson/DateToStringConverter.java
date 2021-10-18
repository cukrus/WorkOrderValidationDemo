package net.cukrus.woValidationDemo.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import net.cukrus.woValidationDemo.util.DateUtils;

import java.util.Date;

public class DateToStringConverter extends StdConverter<Date, String> {
    @Override
    public String convert(Date value) {
        return DateUtils.dateToString(value);
    }
}
