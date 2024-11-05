package in.iot.lab.innovance.repository;

import in.iot.lab.innovance.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
    Optional<Domain> findByName(String name);
}