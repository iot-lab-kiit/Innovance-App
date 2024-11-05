package in.iot.lab.innovance.service;


import in.iot.lab.innovance.dto.PostUserChoiceRequest;
import in.iot.lab.innovance.dto.UserLevelChoiceDTO;
import in.iot.lab.innovance.entity.Level;
import in.iot.lab.innovance.entity.User;
import in.iot.lab.innovance.entity.UserLevelChoice;
import in.iot.lab.innovance.exception.LevelNotFound;
import in.iot.lab.innovance.exception.UserLevelChoiceNotFound;
import in.iot.lab.innovance.exception.UserNotFound;
import in.iot.lab.innovance.repository.LevelRepository;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import in.iot.lab.innovance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLevelChoiceService {

    private final UserLevelChoiceRepository userLevelRepo;
    private final UserRepository userRepo;
    private final LevelRepository levelRepo;

    public UserLevelChoiceDTO createUserLevelChoice(PostUserChoiceRequest postUserChoiceRequest) {
        User user = userRepo
                .findById(postUserChoiceRequest.getUserId())
                .orElseThrow(() -> new UserNotFound(postUserChoiceRequest.getUserId()));

        Level level = levelRepo
                .findById(postUserChoiceRequest.getLevelId())
                .orElseThrow(() -> new LevelNotFound(postUserChoiceRequest.getLevelId()));

        UserLevelChoice newUserLevelChoice = UserLevelChoice
                .builder()
                .user(user)
                .level(level)
                .selected(postUserChoiceRequest.getSelected())
                .build();

        return userLevelRepo
                .save(newUserLevelChoice)
                .toUserLevelChoiceDto();
    }

    public List<UserLevelChoiceDTO> findAllUserLevelChoices() {
        return userLevelRepo
                .findAll()
                .stream().map(UserLevelChoice::toUserLevelChoiceDto)
                .toList();
    }

    public UserLevelChoiceDTO findUserLevelChoiceById(Integer id) {
        return userLevelRepo
                .findById(id)
                .map(UserLevelChoice::toUserLevelChoiceDto)
                .orElseThrow(() -> new UserLevelChoiceNotFound(id));
    }

    public List<UserLevelChoiceDTO> findUserLevelChoiceByUser_Id(Integer id) {
        if (!userRepo.existsById(id))
            throw new UserNotFound(id);

        return userLevelRepo
                .findByUser_Id(id)
                .stream().map(UserLevelChoice::toUserLevelChoiceDto)
                .toList();
    }

    public UserLevelChoiceDTO findUserLevelChoiceByUser_IdAndLevel_Id(Integer userId, Integer levelId) {
        if (!userRepo.existsById(userId))
            throw new UserNotFound(userId);

        if (!levelRepo.existsById(levelId))
            throw new LevelNotFound(levelId);

        return userLevelRepo
                .findByUser_IdAndLevel_Id(userId, levelId)
                .map(UserLevelChoice::toUserLevelChoiceDto)
                .orElseThrow(() -> new UserLevelChoiceNotFound(userId, levelId));
    }

    public void deleteUserLevelChoice(Integer id) {
        if (!userLevelRepo.existsById(id))
            throw new UserLevelChoiceNotFound(id);

        userLevelRepo.deleteById(id);
    }
}