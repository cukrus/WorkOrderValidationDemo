package net.cukrus.woValidationDemo.rest;

import net.cukrus.woValidationDemo.model.WorkOrderValidationRequest;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import net.cukrus.woValidationDemo.service.WorkOrderValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @Autowired
    private WorkOrderValidationService validationService;

    @PostMapping("/validate")
    public Object validate(@RequestBody WorkOrder workOrder) {
        WorkOrderValidationRequest request = new WorkOrderValidationRequest(workOrder);
        Object result = validationService.validateWorkOrderRequest(request);
//        ApiResult result = new ApiResult();
//        result.setStarted(new Date());
//
//        Favorite found = favoriteRepo.findByUserIdAndArtistId(userId, artist.getArtistId());
//        if (found == null) {
//            try {
//                artistRepo.save(artist);
//                favoriteRepo.save(new Favorite(userId, artist.getArtistId()));
//                result.setStatus(ApiResultStatus.SUCCESS);
//            } catch (Exception e) {
//                result.setStatus(ApiResultStatus.ERROR);
//                result.setError(e.getMessage());
//            }
//        } else {
//            result.setStatus(ApiResultStatus.SUCCESS);
//            result.setError("Artist already in favorites");
//        }
//
//        result.setEnded(new Date());
//        result.setTook(result.getEnded().getTime() - result.getStarted().getTime());
        return result;
    }
}
