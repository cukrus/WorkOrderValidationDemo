package net.cukrus.woValidationDemo.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import net.cukrus.woValidationDemo.util.DateUtils;

import java.util.Date;

public class StringToDateConverter extends StdConverter<String, Date> {
    @Override
    public Date convert(String value) {
        return DateUtils.dateFromString(value);
    }
}
