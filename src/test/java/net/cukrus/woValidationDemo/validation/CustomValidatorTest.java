package net.cukrus.woValidationDemo.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomValidatorTest {
    @Test
    void validate_success() {
        assertNull(new CustomValidator(() -> null).validate());
    }

    @Test
    void validate_failure() {
        assertNotNull(new CustomValidator(() -> "some validation error").validate());
    }
}