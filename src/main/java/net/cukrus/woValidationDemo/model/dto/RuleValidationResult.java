package net.cukrus.woValidationDemo.model.dto;

import java.util.ArrayList;
import java.util.List;

public class RuleValidationResult {
    private List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public boolean valid() {
        return errors.isEmpty();
    }
}