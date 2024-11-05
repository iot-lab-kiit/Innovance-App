package in.iot.lab.innovance.controller;

import in.iot.lab.innovance.constants.UrlConstants;
import in.iot.lab.innovance.dto.UserDTO;
import in.iot.lab.innovance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(UrlConstants.CREATE_USER)
    public ResponseEntity<UserDTO> createUserHandler(@RequestBody UserDTO user) {
        UserDTO createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping(UrlConstants.FIND_ALL_USERS)
    public ResponseEntity<List<UserDTO>> findAllUserHandler(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit
    ) {
        List<UserDTO> userList = userService.findAllUsers(PageRequest.of(page, limit));
        return ResponseEntity.ok(userList);
    }

    @GetMapping(UrlConstants.FIND_USER_BY_ID)
    public ResponseEntity<UserDTO> findUserByIdHandler(@PathVariable Integer id) {
        UserDTO foundUser = userService.findUserById(id);
        return ResponseEntity.ok(foundUser);
    }


    @DeleteMapping(UrlConstants.DELETE_USER)
    public ResponseEntity<Void> deleteUserHandler(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
