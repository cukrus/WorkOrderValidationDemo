package net.cukrus.woValidationDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@JsonIgnoreProperties({ "workOrder" })
public class WorkOrderValidationRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date requestDate;
    private String workOrderType;
    private String department;
    private Boolean valid;
    @Transient
    private WorkOrder workOrder;

    public WorkOrderValidationRequest() {}

    public WorkOrderValidationRequest(WorkOrder workOrder) {
        this.requestDate = new Date();
        this.workOrder = workOrder;
        if(workOrder != null) {
            this.workOrderType = workOrder.getClass().getSimpleName();
            this.department = workOrder.getDepartment();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(String workOrderType) {
        this.workOrderType = workOrderType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }
}
