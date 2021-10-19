package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.model.dto.RuleValidationResult;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a WorkOrder validator that consists of collection of ValidationRules for its fields
 */
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

    /**
     * appends current <i>validationRules</i> with additional provided <i>rules</i>
     * @param rules ValidationRule rules to add
     */
    public void addRules(ValidationRule... rules){
        if(rules != null && rules.length > 0) {
            if (validationRules == null) {
                validationRules = new ArrayList();
            }
            validationRules.addAll(List.of(rules));
        }
    }

    /**
     * executes the logic of ValidationRules held and produces a WorkOrderValidationResult
     * @return WorkOrderValidationResult object that holds information and errors about executed logic
     */
    public WorkOrderValidationResult validate() {
        WorkOrderValidationResult result = new WorkOrderValidationResult(workOrder);
        for (ValidationRule rule : validationRules) {
            RuleValidationResult ruleResult = rule.validate();
            if (!ruleResult.valid()) {
                result.getErrors().add(ruleResult.toString());
                if (failFast) {
                    break;
                }
            }
        }
        return result;
    }

    public List<ValidationRule> getValidationRules() {
        return validationRules;
    }
}