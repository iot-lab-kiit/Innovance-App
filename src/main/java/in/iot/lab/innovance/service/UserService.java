package in.iot.lab.innovance.service;


import in.iot.lab.innovance.dto.UserDTO;
import in.iot.lab.innovance.entity.User;
import in.iot.lab.innovance.exception.UserNotFound;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import in.iot.lab.innovance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserLevelChoiceRepository userLevelRepo;

    public UserDTO createUser(UserDTO user) {
        return userRepo
                .save(user.toUser())
                .toUserDTO();
    }

    public List<UserDTO> findAllUsers(Pageable pageable) {
        return userRepo
                .findAll(pageable)
                .stream().map(User::toUserDTO)
                .toList();
    }

    public UserDTO findUserById(Integer id) {
        return userRepo
                .findById(id)
                .map(User::toUserDTO)
                .orElseThrow(() -> new UserNotFound(id));
    }

    public void deleteUser(Integer id) {
        if (!userRepo.existsById(id))
            throw new UserNotFound(id);

        userLevelRepo.deleteByUser_Id(id);
        userRepo.deleteById(id);
    }
}