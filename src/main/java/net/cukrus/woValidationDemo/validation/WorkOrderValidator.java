package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.model.dto.RuleValidationResult;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkOrderValidator {
    private final WorkOrder workOrder;
    private List<ValidationRule> validationRules = new ArrayList();
    private boolean failFast = false;

    public WorkOrderValidator(WorkOrder workOrder) {
        this(workOrder, false);
    }

    public WorkOrderValidator(WorkOrder workOrder, boolean failFast) {
        this.workOrder = workOrder;
        this.failFast = failFast;
    }

    public void addRules(ValidationRule... rules){
        if(rules != null && rules.length > 0) {
            if (validationRules == null) {
                validationRules = new ArrayList();
            }
            validationRules.addAll(List.of(rules));
        }
    }

    public WorkOrderValidationResult validate() {
        WorkOrderValidationResult result = new WorkOrderValidationResult(workOrder);
        if (!CollectionUtils.isEmpty(validationRules)) {
            for (ValidationRule rule : validationRules) {
                RuleValidationResult ruleResult = rule.validate();
                if (!ruleResult.valid()) {
                    result.getErrors().add(ruleResult.toString());
                    if(failFast) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public List<ValidationRule> getValidationRules() {
        return validationRules;
    }
}