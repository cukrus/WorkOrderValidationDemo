package net.cukrus.woValidationDemo.validation;

/**
 * abstract class for Validator, that has basic validation instructions for other validators that implement it.
 */
public abstract class Validator {
    private String errorOverride;

    /**
     * runs the validation check logic
     * @return error String if invalid, null otherwise
     */
    public String validate(){
        String result = internalValidate();
        return result == null ? null : errorOverride != null ? errorOverride : result;
    }

    /**
     * validation logic of concrete Validator implementation
     * @return error String if invalid, null otherwise
     */
    protected abstract String internalValidate();

    /**
     * method for setting a custom error message in case of validation check fail
     * @param errorOverride error message the Validator to return in case of validation fail
     * @return this Validator with a set <i>errorOverride</i> value
     */
    public Validator withErrorOverride(String errorOverride) {
        this.errorOverride = errorOverride;
        return this;
    }

    public String getErrorOverride() {
        return errorOverride;
    }
}