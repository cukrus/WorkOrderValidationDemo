package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.model.dto.RuleValidationResult;

/**
 * represents a field validation rule that can consist of multiple Validators
 */
public class ValidationRule {
    private final String fieldName;
    private final String ruleDescription;
    private final Boolean failFast;
    private final Validator[] validators;

    public ValidationRule(String fieldName, String ruleDescription, Boolean failFast, Validator... validators) {
        this.fieldName = fieldName;
        this.ruleDescription = ruleDescription;
        this.failFast = failFast;
        this.validators = validators;
    }

    /**
     * executes the logic of Validators held and produces a RuleValidationResult
     * @return RuleValidationResult object that holds information and errors about executed logic
     */
    public RuleValidationResult validate() {
        RuleValidationResult result = new RuleValidationResult(fieldName, ruleDescription);
        for(Validator validator : validators) {
            String error = validator.validate();
            if(error != null) {
                result.getErrors().add(error);
                if (Boolean.TRUE.equals(failFast)) {
                    break;
                }
            }
        }
        return result;
    }
}