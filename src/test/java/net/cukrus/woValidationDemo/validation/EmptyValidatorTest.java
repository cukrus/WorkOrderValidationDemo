package net.cukrus.woValidationDemo.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmptyValidatorTest {
    @Test
    void validate_whenNull() {
        assertNotNull(new EmptyValidator(null).validate());
    }

    @Test
    void validate_whenNotNull() {
        assertNull(new EmptyValidator(new Object()).validate());
    }

    @Test
    void validate_whenBlankString() {
        assertNotNull(new EmptyValidator(" ").validate());
    }

    @Test
    void validate_whenNonBlankString() {
        assertNull(new EmptyValidator("Some String").validate());
    }
}