package net.cukrus.woValidationDemo.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "department", "start_date", "end_date", "currency", "cost", "parts" })
public class Analysis extends WorkOrder {
}
