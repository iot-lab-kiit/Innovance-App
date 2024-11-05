package in.iot.lab.innovance.service;


import in.iot.lab.innovance.dto.UserLevelChoiceDTO;
import in.iot.lab.innovance.entity.UserLevelChoice;
import in.iot.lab.innovance.exception.UserLevelChoiceNotFound;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLevelChoiceService {

    private final UserLevelChoiceRepository userLevelRepo;

    public UserLevelChoiceDTO createUserLevelChoice(UserLevelChoiceDTO userLevelChoice) {
        return userLevelRepo
                .save(userLevelChoice.toUserLevelChoice())
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
        return userLevelRepo
                .findByUser_Id(id)
                .stream().map(UserLevelChoice::toUserLevelChoiceDto)
                .toList();
    }

    public UserLevelChoiceDTO findUserLevelChoiceByUser_IdAndLevel_Id(Integer userId, Integer levelId) {
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