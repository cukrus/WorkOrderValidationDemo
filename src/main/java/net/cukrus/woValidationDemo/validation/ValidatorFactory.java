package net.cukrus.woValidationDemo.validation;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.Repair;
import net.cukrus.woValidationDemo.model.dto.Replacement;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import org.springframework.util.CollectionUtils;

import java.util.Date;

public class ValidatorFactory {
    public static WorkOrderValidator createValidator(WorkOrderValidationRequest request) {
        //TODO if more WorkOrder Types gets added refactor to switch by class name or enum
        WorkOrderValidator result = null;
        if (request != null && request.getWorkOrder() != null) {
            WorkOrder workOrder = request.getWorkOrder();
            result = new WorkOrderValidator(workOrder);
            result.addRules(new ValidationRule("department", "not empty and in the valid department list", true,
                    new EmptyValidator(workOrder.getDepartment()),
                    ValueInValidator.inArray(workOrder.getDepartment(),
                            "GOoD analysis department",
                            "GOoD repair department",
                            "GOoD replacement department")));
            result.addRules(new ValidationRule("start_date", "not empty and before current date", true,
                    new EmptyValidator(workOrder.getStartDate()),
                    DateValidator.before(workOrder.getStartDate(), new Date())));//TODO need to get currentDate from request process start ?
            result.addRules(new ValidationRule("end_date", "not empty and after start date", true,
                    new EmptyValidator(workOrder.getEndDate()),
                    DateValidator.after(workOrder.getEndDate(), workOrder.getStartDate())));
            result.addRules(new ValidationRule("currency", "valid ISO code (ISO 4217)", true,
                    new EmptyValidator(workOrder.getCurrency()).withErrorOverride("empty or invalid value")));
            result.addRules(new ValidationRule("cost", "greater than 0", true,
                    new CustomValidator(() -> workOrder.getCost() != null && workOrder.getCost() > 0 ? null : "invalid cost value")));
            if (workOrder instanceof Repair) {
                Repair repair = (Repair) workOrder;
                result.addRules(new ValidationRule("analysis_date", "after start date and before end date", true,
                        new EmptyValidator(repair.getAnalysisDate()),
                        DateValidator.between(repair.getAnalysisDate(), repair.getStartDate(), repair.getEndDate())));
                result.addRules(new ValidationRule("responsible_person", "not empty", true,
                        new EmptyValidator(repair.getResponsiblePerson())));
                result.addRules(new ValidationRule("test_date", "not empty, after analysis date and before end date", true,
                        new EmptyValidator(repair.getTestDate()),
                        DateValidator.between(repair.getTestDate(), repair.getAnalysisDate(), repair.getEndDate())));
                result.addRules(new ValidationRule("parts", "total count greater than 0", true,
                        new CustomValidator(() -> !CollectionUtils.isEmpty(repair.getParts()) ? null : "no parts found")));
            } else if (workOrder instanceof Replacement) {
                Replacement replacement = (Replacement) workOrder;
                result.addRules(new ValidationRule("factory_name", "not empty", true,
                        new EmptyValidator(replacement.getFactoryName())));
                result.addRules(new ValidationRule("factory_order_number", "the length is 10, first 2 characters - letters, others – numbers", true,
                        StringValidator.pattern(replacement.getFactoryOrderNumber(), "[a-zA-Z]{2}\\d{8}")));
                result.addRules(new ValidationRule("parts", "all inventory numbers are not empty", true,
                        new CustomValidator(() -> CollectionUtils.isEmpty(replacement.getParts())
                                || replacement.getParts().stream().anyMatch(p -> p.getInventoryNumber() == null
                                || (p.getInventoryNumber() == null || p.getInventoryNumber().isBlank()))
                                ? "found part(s) with blank inventory numbers" : null)));
            }
        }
        return result;
    }
}