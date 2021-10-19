package net.cukrus.woValidationDemo.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import net.cukrus.woValidationDemo.model.dto.WorkOrderValidationResult;
import net.cukrus.woValidationDemo.service.WorkOrderValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    private static final ObjectMapper JACKSON = new ObjectMapper();

    @Autowired
    private WorkOrderValidationService validationService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        if (model.getAttribute("validationRequest") == null) {
            model.addAttribute("validationRequest", new ValidationRequest());
        }
        return "index";
    }

    @PostMapping({"/", "/index"})
    public RedirectView validateRequest(@ModelAttribute("validationRequest") ValidationRequest validationRequest,
                                        RedirectAttributes redirectAttributes, HttpServletRequest request) {
        final RedirectView redirectView = new RedirectView(request.getServletPath(), true);
        redirectAttributes.addFlashAttribute("requestProcessed", true);
        redirectAttributes.addFlashAttribute("validationRequest", validationRequest);
        try {
            WorkOrder workOrder = JACKSON.readValue(validationRequest.request, WorkOrder.class);
            WorkOrderValidationRequest wovRequest = new WorkOrderValidationRequest(workOrder);
            WorkOrderValidationResult result = validationService.validateWorkOrderRequest(wovRequest);
            redirectAttributes.addFlashAttribute("responseStr", JACKSON.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            redirectAttributes.addFlashAttribute("responseStr", "ERROR: unable to process malformed JSON");
        }
        return redirectView;
    }

    @GetMapping("/history")
    public String history(Model model) {
        List<WorkOrderValidationRequest> validations = validationService.fetchValidationHistory();
        model.addAttribute("validations", validations);
        model.addAttribute("validationsEmpty", validations.isEmpty());
        return "history";
    }

    public class ValidationRequest {
        private String request;

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }
    }
}
