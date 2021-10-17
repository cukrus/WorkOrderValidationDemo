package net.cukrus.woValidationDemo.validation;

public class EmptyValidator extends Validator {
    private final Object toValidate;

    public EmptyValidator(Object toValidate) {
        this.toValidate = toValidate;
    }

    @Override
    protected String internalValidate() {
        if (toValidate == null) {
            return "missing value";
        }
        if(toValidate instanceof String && ((String) toValidate).isBlank()) {
            return "blank value";
        }
        return null;
    }
}