package net.cukrus.woValidationDemo.validation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ValueInValidatorTest {
    @Test
    void inArray_success() {
        String toValidate = "someValue";
        Validator validator = ValueInValidator.inArray(toValidate, toValidate + 1, toValidate + 2, toValidate);
        assertNull(validator.validate());
    }

    @Test
    void inArray_failure() {
        String toValidate = "someValue";
        Validator validator = ValueInValidator.inArray(toValidate, toValidate + 1, toValidate + 2);
        assertNotNull(validator.validate());
    }

    @Test
    void inCollection_success() {
        String toValidate = "someValue";
        Validator validator = ValueInValidator.inCollection(toValidate, Arrays.asList(toValidate + 1, toValidate + 2, toValidate));
        assertNull(validator.validate());
    }

    @Test
    void inCollection_failure() {
        String toValidate = "someValue";
        Validator validator = ValueInValidator.inCollection(toValidate, Arrays.asList(toValidate + 1, toValidate + 2));
        assertNotNull(validator.validate());
    }

    @Test
    void inSupplier_success() {
        String toValidate = "someValue";
        Validator validator = ValueInValidator.inSupplier(toValidate,() -> Arrays.asList(toValidate + 1, toValidate + 2, toValidate));
        assertNull(validator.validate());
    }

    @Test
    void inSupplier_failure() {
        String toValidate = "someValue";
        Validator validator = ValueInValidator.inSupplier(toValidate, () -> Arrays.asList(toValidate + 1, toValidate + 2));
        assertNotNull(validator.validate());
    }
}