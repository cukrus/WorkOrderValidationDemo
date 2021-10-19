package net.cukrus.woValidationDemo.service;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;

import java.util.List;

public interface WorkOrderValidationService {
    WorkOrderValidationResult validateWorkOrderRequest(WorkOrderValidationRequest request);
    List<WorkOrderValidationRequest> fetchValidationHistory();
}
