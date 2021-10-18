package net.cukrus.woValidationDemo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "department", "start_date", "end_date", "factory_name", "factory_order_number", "currency", "cost", "parts" })
public class Replacement extends WorkOrder {
    @JsonProperty("factory_name")
    private String factoryName;
    @JsonProperty("factory_order_number")
    private String factoryOrderNumber;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryOrderNumber() {
        return factoryOrderNumber;
    }

    public void setFactoryOrderNumber(String factoryOrderNumber) {
        this.factoryOrderNumber = factoryOrderNumber;
    }
}