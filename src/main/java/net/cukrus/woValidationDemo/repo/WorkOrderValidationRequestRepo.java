package net.cukrus.woValidationDemo.repo;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import org.springframework.data.repository.CrudRepository;

public interface WorkOrderValidationRequestRepo extends CrudRepository<WorkOrderValidationRequest, Long> {
}