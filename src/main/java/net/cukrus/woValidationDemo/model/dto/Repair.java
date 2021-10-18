package net.cukrus.woValidationDemo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.cukrus.woValidationDemo.jackson.DateToStringConverter;
import net.cukrus.woValidationDemo.jackson.StringToDateConverter;

import java.util.Date;

@JsonPropertyOrder({ "department", "start_date", "end_date", "analysis_date", "test_date", "responsible_person", "currency", "cost", "parts" })
public class Repair extends WorkOrder {
    @JsonProperty("analysis_date")
    @JsonDeserialize(converter = StringToDateConverter.class)
    @JsonSerialize(converter = DateToStringConverter.class)
    private Date analysisDate;
    @JsonProperty("test_date")
    @JsonDeserialize(converter = StringToDateConverter.class)
    @JsonSerialize(converter = DateToStringConverter.class)
    private Date testDate;
    @JsonProperty("responsible_person")
    private String responsiblePerson;

    public Date getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(Date analysisDate) {
        this.analysisDate = analysisDate;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}