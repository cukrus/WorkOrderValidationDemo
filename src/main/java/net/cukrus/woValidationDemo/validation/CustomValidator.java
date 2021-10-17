package net.cukrus.woValidationDemo.validation;

import java.util.function.Supplier;

public class CustomValidator extends Validator {
    private final Supplier<String> resultSupplier;

    public CustomValidator(Supplier<String> resultSupplier) {
        this.resultSupplier = resultSupplier;
    }

    @Override
    protected String internalValidate() {
        return resultSupplier.get();
    }
}