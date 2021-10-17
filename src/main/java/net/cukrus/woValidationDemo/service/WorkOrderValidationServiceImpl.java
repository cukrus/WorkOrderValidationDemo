package net.cukrus.woValidationDemo.service;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;
import net.cukrus.woValidationDemo.validation.ValidatorFactory;
import net.cukrus.woValidationDemo.validation.WorkOrderValidator;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderValidationServiceImpl implements WorkOrderValidationService {

    @Override
    public WorkOrderValidationResult validateWorkOrderRequest(WorkOrderValidationRequest request) {
        WorkOrderValidationResult result = null;
        if (request != null) {
            //TODO can refactor this by creating and involving a validator processor by different strategies for logging and/or other purposes
            WorkOrderValidator validator = ValidatorFactory.createValidator(request);
            result =  validator.validate();
            //TODO add save to DB
        }
        return result;
    }
}