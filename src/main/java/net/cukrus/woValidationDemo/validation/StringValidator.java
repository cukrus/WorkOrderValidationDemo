package net.cukrus.woValidationDemo.validation;

import java.util.regex.Pattern;

public class StringValidator extends Validator {
    private final StringValidationMode mode;
    private final String toValidate;
    private final String regex;

    private StringValidator(StringValidationMode mode, String toValidate, String regex) {
        this.mode = mode;
        this.toValidate = toValidate;
        this.regex = regex;
    }

    @Override
    protected String internalValidate() {
        String result = null;
        if (StringValidationMode.PATTERN == mode && !Pattern.matches(regex, toValidate)) {
            result = "value does not match correct pattern";
        }
        return result;
    }

    public static StringValidator pattern(String toValidate, String regex) {
        return new StringValidator(StringValidationMode.PATTERN, toValidate, regex);
    }

    public enum StringValidationMode {
        PATTERN;//TODO extendable for the future
    }
}