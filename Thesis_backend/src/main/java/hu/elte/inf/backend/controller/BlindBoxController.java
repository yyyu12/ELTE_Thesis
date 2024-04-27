package hu.elte.inf.backend.controller;

import hu.elte.inf.backend.controller.request.BlindBoxRequest;
import hu.elte.inf.backend.controller.response.ArtworkResponse;
import hu.elte.inf.backend.controller.response.BlindBoxResponse;
import hu.elte.inf.backend.common.Result;
import hu.elte.inf.backend.service.impl.BlindBoxServiceImpl;
import hu.elte.inf.backend.sqlEntity.BlindBox;
import hu.elte.inf.backend.sqlEntity.BlindBoxDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/blindBox")
@CrossOrigin(origins = "*")
public class BlindBoxController {

    @Autowired
    private BlindBoxServiceImpl blindBoxService;

    /**
     * Get all blind box details by user id
     * @param user_id
     * @return
     */
    @GetMapping("/user/{user_id}/details")
    public ResponseEntity<List<BlindBoxDetail>> getBlindBoxDetailsByUserId(@PathVariable Long user_id) {
        List<BlindBoxDetail> blindBoxDetails = blindBoxService.getAllBlindBoxDetailsByUserId(user_id);

        return ResponseEntity.ok(blindBoxDetails);
    }

    @PostMapping("/addBlindBox")
    public ResponseEntity<Result> addBlindBox(@Validated @RequestBody BlindBoxRequest blindBoxRequest) {
        BlindBox blindBox = new BlindBox();
        BeanUtils.copyProperties(blindBoxRequest, blindBox);

        boolean added = blindBoxService.addBlindBox(blindBox);

        if (added) {
            BlindBoxResponse blindBoxResponse = new BlindBoxResponse();
            BeanUtils.copyProperties(blindBox, blindBoxResponse);
            return ResponseEntity.ok(Result.ok("Blind box added successfully").put("info", blindBoxResponse));
        } else {
            return ResponseEntity.ok(Result.error("Blind box add failed"));
        }
    }
    

    /**
     * Get all blind boxes by user id
     * @param user_id
     * @return
     */
    @GetMapping("/getBlindBoxesByUserId/{user_id}")
    public ResponseEntity<List<BlindBox>> getBlindBoxesByUserId(@PathVariable Long user_id) {
        List<BlindBox> blindBoxes = blindBoxService.getBlindBoxesByUserId(user_id);
        return ResponseEntity.ok(blindBoxes);
    }
}
