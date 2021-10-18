package net.cukrus.woValidationDemo.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Currency;

public class StringToCurrencyConverter extends StdConverter<String, Currency> {
    @Override
    public Currency convert(String value) {
        Currency result = null;
        if(value != null) {
            try{
                result = Currency.getInstance(value);
            } catch (Exception e) {
                //ignore conversion errors
            }
        }
        return result;
    }
}