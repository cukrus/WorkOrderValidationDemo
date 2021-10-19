package net.cukrus.woValidationDemo.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {
    @Test
    void pattern_success() {
        String toValidate = "DE12345678";
        Validator validator = StringValidator.pattern(toValidate, "[a-zA-Z]{2}\\d{8}");
        assertNull(validator.validate());
    }

    @Test
    void pattern_failure() {
        String toValidate = "DE1234567";
        Validator validator = StringValidator.pattern(toValidate, "[a-zA-Z]{2}\\d{8}");
        assertNotNull(validator.validate());
    }
}