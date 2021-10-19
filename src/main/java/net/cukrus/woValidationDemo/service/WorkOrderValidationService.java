package net.cukrus.woValidationDemo.service;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;

import java.util.List;

public interface WorkOrderValidationService {
    /**
     * method for validating WorkOrderValidationRequest
     * @param request WorkOrderValidationRequest to validate
     * @return WorkOrderValidationResult of executed validation logic
     */
    WorkOrderValidationResult validateWorkOrderRequest(WorkOrderValidationRequest request);

    /**
     * method for retrieving processed WorkOrderValidationRequest history
     * @return list of processed WorkOrderValidationRequests
     */
    List<WorkOrderValidationRequest> fetchValidationHistory();
}
