package net.cukrus.woValidationDemo.validation;

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

    protected abstract String internalValidate();

    public Validator withErrorOverride(String errorOverride) {
        this.errorOverride = errorOverride;
        return this;
    }

    public String getErrorOverride() {
        return errorOverride;
    }
}