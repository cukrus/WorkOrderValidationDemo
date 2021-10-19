package net.cukrus.woValidationDemo.rest;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;
import net.cukrus.woValidationDemo.service.WorkOrderValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ValidationController {

    @Autowired
    private WorkOrderValidationService validationService;

    @PostMapping("/validate")
    public WorkOrderValidationResult validate(@RequestBody WorkOrder workOrder) {
        WorkOrderValidationRequest request = new WorkOrderValidationRequest(workOrder);
        WorkOrderValidationResult result = validationService.validateWorkOrderRequest(request);
        return result;
    }

    @GetMapping("/validationHistory")
    public List<WorkOrderValidationRequest> validationHistory() {
        return validationService.fetchValidationHistory();
    }
}
