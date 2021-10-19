package net.cukrus.woValidationDemo.service;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;
import net.cukrus.woValidationDemo.repo.WorkOrderValidationRequestRepo;
import net.cukrus.woValidationDemo.validation.ValidatorFactory;
import net.cukrus.woValidationDemo.validation.WorkOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOrderValidationServiceImpl implements WorkOrderValidationService {
    @Autowired
    private WorkOrderValidationRequestRepo workOrderValidationRequestRepo;

    @Override
    public WorkOrderValidationResult validateWorkOrderRequest(WorkOrderValidationRequest request) {
        WorkOrderValidationResult result = null;
        if (request != null) {
            //TODO can refactor this by creating and involving a validator processor by different strategies for logging and/or other purposes
            WorkOrderValidator validator = ValidatorFactory.createValidator(request);
            result =  validator.validate();

            //TODO can refactor to do this in separate thread for quicker response time
            request.setValid(result.valid());
            workOrderValidationRequestRepo.save(request);
        }
        return result;
    }

    @Override
    public List<WorkOrderValidationRequest> fetchValidationHistory() {
        List<WorkOrderValidationRequest> result = new ArrayList<>();
        workOrderValidationRequestRepo.findAll().forEach(wovr -> result.add(wovr));
        return result;
    }
}