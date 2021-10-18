package net.cukrus.woValidationDemo.rest;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import net.cukrus.woValidationDemo.service.WorkOrderValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @Autowired
    private WorkOrderValidationService validationService;

    @PostMapping("/validate")
    public Object validate(@RequestBody WorkOrder workOrder) {
        WorkOrderValidationRequest request = new WorkOrderValidationRequest(workOrder);
        Object result = validationService.validateWorkOrderRequest(request);
        return result;
    }
}
