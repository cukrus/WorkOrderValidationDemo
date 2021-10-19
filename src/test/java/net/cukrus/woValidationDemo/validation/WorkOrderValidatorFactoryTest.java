package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.Analysis;
import net.cukrus.woValidationDemo.model.dto.Repair;
import net.cukrus.woValidationDemo.model.dto.Replacement;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.*;

class WorkOrderValidatorFactoryTest {
    @Test
    void createValidator_analysis() {
        //given
        WorkOrderValidationRequest request = new WorkOrderValidationRequest(new Analysis());

        //when
        WorkOrderValidator validator = WorkOrderValidatorFactory.createValidator(request);

        //then
        assertNotNull(validator);
        assertFalse(CollectionUtils.isEmpty(validator.getValidationRules()));
        assertEquals(5, validator.getValidationRules().size());
    }

    @Test
    void createValidator_repair() {
        //given
        WorkOrderValidationRequest request = new WorkOrderValidationRequest(new Repair());

        //when
        WorkOrderValidator validator = WorkOrderValidatorFactory.createValidator(request);

        //then
        assertNotNull(validator);
        assertFalse(CollectionUtils.isEmpty(validator.getValidationRules()));
        assertEquals(9, validator.getValidationRules().size());
    }

    @Test
    void createValidator_replacement() {
        //given
        WorkOrderValidationRequest request = new WorkOrderValidationRequest(new Replacement());

        //when
        WorkOrderValidator validator = WorkOrderValidatorFactory.createValidator(request);

        //then
        assertNotNull(validator);
        assertFalse(CollectionUtils.isEmpty(validator.getValidationRules()));
        assertEquals(8, validator.getValidationRules().size());
    }
}