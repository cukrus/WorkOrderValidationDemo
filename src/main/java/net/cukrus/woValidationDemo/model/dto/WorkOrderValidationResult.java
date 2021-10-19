package net.cukrus.woValidationDemo.model.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "workOrder" })
@JsonPropertyOrder({ "validationResult", "validationErrors" })
public class WorkOrderValidationResult {
    private final WorkOrder workOrder;
    @JsonProperty("validationErrors")
    private List<String> errors = new ArrayList<>();

    public WorkOrderValidationResult(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public boolean valid() {
        return CollectionUtils.isEmpty(errors);
    }

    public List<String> getErrors() {
        return errors;
    }

    @JsonGetter("validationResult")
    public String getValidationResult() {
        return valid() ? "SUCCESS" : "FAILURE";
    }
}