package in.iot.lab.innovance.controller;


import in.iot.lab.innovance.constants.UrlConstants;
import in.iot.lab.innovance.dto.PostUserChoiceRequest;
import in.iot.lab.innovance.dto.UserLevelChoiceDTO;
import in.iot.lab.innovance.service.UserLevelChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserLevelChoiceController {

    private final UserLevelChoiceService service;

    @PostMapping(UrlConstants.CREATE_USER_LEVEL_CHOICE)
    public ResponseEntity<UserLevelChoiceDTO> createUserLevelChoiceHandler(
            @RequestBody PostUserChoiceRequest postUserChoiceRequest
    ) {
        UserLevelChoiceDTO created = service.createUserLevelChoice(postUserChoiceRequest);
        return ResponseEntity.ok(created);
    }

    @GetMapping(UrlConstants.FIND_ALL_USER_LEVEL_CHOICE)
    public ResponseEntity<List<UserLevelChoiceDTO>> findAllUserLevelChoiceHandler() {
        List<UserLevelChoiceDTO> userLevelChoiceDTOList = service.findAllUserLevelChoices();
        return ResponseEntity.ok(userLevelChoiceDTOList);
    }

    @GetMapping(UrlConstants.FIND_USER_LEVEL_CHOICE_BY_ID)
    public ResponseEntity<UserLevelChoiceDTO> findUserLevelChoiceByIdHandler(@PathVariable Integer id) {
        UserLevelChoiceDTO foundUser = service.findUserLevelChoiceById(id);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping(UrlConstants.FIND_USER_LEVEL_CHOICE_BY_USER_ID)
    public ResponseEntity<List<UserLevelChoiceDTO>> findUserLevelChoiceByUser_IdHandler(
            @PathVariable Integer id
    ) {
        List<UserLevelChoiceDTO> userLevelChoiceDTOList = service.findUserLevelChoiceByUser_Id(id);
        return ResponseEntity.ok(userLevelChoiceDTOList);
    }

    @GetMapping(UrlConstants.FIND_USER_LEVEL_CHOICE_BY_USER_AND_LEVEL_ID)
    public ResponseEntity<UserLevelChoiceDTO> findUserLevelChoiceByUser_IdAndLevel_IdHandler(
            @PathVariable Integer userId,
            @PathVariable Integer levelId
    ) {
        UserLevelChoiceDTO foundUser = service.findUserLevelChoiceByUser_IdAndLevel_Id(userId, levelId);
        return ResponseEntity.ok(foundUser);
    }

    @DeleteMapping(UrlConstants.DELETE_USER_LEVEL_CHOICE)
    public ResponseEntity<Void> deleteUserLevelChoiceHandler(@PathVariable Integer id) {
        service.deleteUserLevelChoice(id);
        return ResponseEntity.ok().build();
    }
}