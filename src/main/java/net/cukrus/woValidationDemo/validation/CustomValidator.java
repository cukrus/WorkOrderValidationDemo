package net.cukrus.woValidationDemo.validation;

import java.util.function.Supplier;

/**
 * Validator for custom logic validation
 */
public class CustomValidator extends Validator {
    private final Supplier<String> resultSupplier;

    /**
     * creates a custom logic Validator with a supplier for custom logic check
     * @param resultSupplier supplier that should return the error message on validation fail or null otherwise
     */
    public CustomValidator(Supplier<String> resultSupplier) {
        this.resultSupplier = resultSupplier;
    }

    @Override
    protected String internalValidate() {
        return resultSupplier.get();
    }
}