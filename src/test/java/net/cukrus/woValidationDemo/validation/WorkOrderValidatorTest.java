package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.model.dto.Analysis;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class WorkOrderValidatorTest {
    @Test
    void validate_success() {
        //given
        WorkOrderValidator validator = new WorkOrderValidator(new Analysis());
        ValidationRule rule = new ValidationRule("field", "description", true, new CustomValidator(() -> null));
        validator.addRules(rule);

        //when
        WorkOrderValidationResult result = validator.validate();

        //then
        assertTrue(result.valid());
        assertTrue(CollectionUtils.isEmpty(result.getErrors()));
    }

    @Test
    void validate_failure() {
        //given
        WorkOrderValidator validator = new WorkOrderValidator(new Analysis());
        ValidationRule rule = new ValidationRule("field", "description", true, new CustomValidator(() -> "some error"));
        validator.addRules(rule);

        //when
        WorkOrderValidationResult result = validator.validate();

        //then
        assertFalse(result.valid());
        assertFalse(CollectionUtils.isEmpty(result.getErrors()));
    }

    @Test
    void validate_noRules() {
        //given
        WorkOrderValidator validator = new WorkOrderValidator(new Analysis());

        //when
        WorkOrderValidationResult result = validator.validate();

        //then
        assertTrue(result.valid());
        assertTrue(CollectionUtils.isEmpty(result.getErrors()));
    }
}