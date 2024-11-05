package in.iot.lab.innovance.repository;

import in.iot.lab.innovance.entity.UserLevelChoice;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface UserLevelChoiceRepository extends JpaRepository<UserLevelChoice, Integer> {

    List<UserLevelChoice> findByUser_Id(Integer userId);

    Optional<UserLevelChoice> findByUser_IdAndLevel_Id(Integer userId, Integer levelId);

    @Transactional
    @Modifying
    void deleteByUser_Id(Integer userId);

    @Transactional
    @Modifying
    void deleteByLevel_Id(Integer levelId);
}