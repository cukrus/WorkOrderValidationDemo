package net.cukrus.woValidationDemo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.cukrus.woValidationDemo.jackson.DateToStringConverter;
import net.cukrus.woValidationDemo.jackson.StringToCurrencyConverter;
import net.cukrus.woValidationDemo.jackson.StringToDateConverter;

import java.util.Currency;
import java.util.Date;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Analysis.class, name = "ANALYSIS"),
        @JsonSubTypes.Type(value = Repair.class, name = "REPAIR"),
        @JsonSubTypes.Type(value = Replacement.class, name = "REPLACEMENT")
})
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class WorkOrder {
    private String department;
    @JsonProperty("start_date")
    @JsonDeserialize(converter = StringToDateConverter.class)
    @JsonSerialize(converter = DateToStringConverter.class)
    private Date startDate;
    @JsonProperty("end_date")
    @JsonDeserialize(converter = StringToDateConverter.class)
    @JsonSerialize(converter = DateToStringConverter.class)
    private Date endDate;
    @JsonDeserialize(converter = StringToCurrencyConverter.class)
    private Currency currency;
    private Double cost;
    private List<Part> parts;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}