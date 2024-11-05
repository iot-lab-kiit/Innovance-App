package in.iot.lab.innovance.repository;

import in.iot.lab.innovance.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
    Domain findByName(String name);
}