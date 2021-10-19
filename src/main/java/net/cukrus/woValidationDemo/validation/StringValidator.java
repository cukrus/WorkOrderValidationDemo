package net.cukrus.woValidationDemo.validation;

import java.util.regex.Pattern;

/**
 * Validator for logic tied to String comparison
 */
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

    /**
     * method for building a Validator for checking if a given String matches a regex pattern
     * @param toValidate String to check
     * @param regex regex to compare to
     * @return StringValidator that checks if <i>toValidate</i> matches <i>regex</i>
     */
    public static StringValidator pattern(String toValidate, String regex) {
        return new StringValidator(StringValidationMode.PATTERN, toValidate, regex);
    }

    public enum StringValidationMode {
        PATTERN;//TODO extendable for the future
    }
}