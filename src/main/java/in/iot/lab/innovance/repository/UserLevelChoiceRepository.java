package in.iot.lab.innovance.repository;

import in.iot.lab.innovance.entity.UserLevelChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLevelChoiceRepository extends JpaRepository<UserLevelChoice, Integer> {

    List<UserLevelChoice> findByUser_Id(Integer userId);

    UserLevelChoice findByUser_IdAndLevel_Id(Integer userId, Integer levelId);
}