package net.cukrus.woValidationDemo.model.dto;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleValidationResult {
    private static final String ERROR_SEPARATOR = System.lineSeparator() + "; ";
    private String fieldName;
    private String ruleDescription;
    private List<String> errors = new ArrayList<>();

    public RuleValidationResult() {}

    public RuleValidationResult(String fieldName, String ruleDescription) {
        this.fieldName = fieldName;
        this.ruleDescription = ruleDescription;
    }

    public boolean valid() {
        return CollectionUtils.isEmpty(errors);
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "Rule '" + fieldName + " " + ruleDescription + "' validation "
                + (valid() ? "succeeded" : "failed: " + errors.stream().collect(Collectors.joining(ERROR_SEPARATOR)));
    }
}