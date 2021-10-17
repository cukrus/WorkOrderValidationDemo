package net.cukrus.woValidationDemo.model.dto;

import java.util.ArrayList;
import java.util.List;

public class WorkOrderValidationResult {
    private final WorkOrder workOrder;
    private List<RuleValidationResult> ruleValidationResults = new ArrayList<>();

    public WorkOrderValidationResult(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public List<RuleValidationResult> getRuleValidationResults() {
        return ruleValidationResults;
    }

    public boolean valid() {
        return ruleValidationResults.isEmpty() || !ruleValidationResults.stream().anyMatch(res -> !res.valid());
    }
}