package in.iot.lab.innovance.repository;

import in.iot.lab.innovance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}