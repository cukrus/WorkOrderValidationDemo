package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.model.dto.RuleValidationResult;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class ValidationRuleTest {
    @Test
    void validate_success() {
        //given
        ValidationRule rule = new ValidationRule("field", "description", true, new CustomValidator(() -> null));

        //when
        RuleValidationResult result = rule.validate();

        //then
        assertTrue(result.valid());
        assertTrue(CollectionUtils.isEmpty(result.getErrors()));
    }

    @Test
    void validate_failure() {
        //given
        ValidationRule rule = new ValidationRule("field", "description", true, new CustomValidator(() -> "some error"));

        //when
        RuleValidationResult result = rule.validate();

        //then
        assertFalse(result.valid());
        assertFalse(CollectionUtils.isEmpty(result.getErrors()));
    }

    @Test
    void validate_noValidators() {
        //given
        ValidationRule rule = new ValidationRule("field", "description", true);

        //when
        RuleValidationResult result = rule.validate();

        //then
        assertTrue(result.valid());
        assertTrue(CollectionUtils.isEmpty(result.getErrors()));
    }
}