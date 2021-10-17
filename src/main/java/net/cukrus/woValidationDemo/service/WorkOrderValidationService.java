package net.cukrus.woValidationDemo.service;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;

public interface WorkOrderValidationService {
    Object validateWorkOrderRequest(WorkOrderValidationRequest request);//TODO refactor
}
