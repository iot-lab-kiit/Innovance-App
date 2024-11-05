package in.iot.lab.innovance.service;

import in.iot.lab.innovance.entity.Domain;
import in.iot.lab.innovance.exception.DomainNotFound;
import in.iot.lab.innovance.repository.DomainRepository;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainService {

    private final DomainRepository domainRepo;
    private final UserLevelChoiceRepository userLevelRepo;

    public Domain createDomain(Domain domain) {
        return domainRepo.save(domain);
    }

    public List<Domain> findAllDomain() {
        return domainRepo.findAll();
    }

    public Domain findByDomainId(Integer id) {
        return domainRepo
                .findById(id)
                .orElseThrow(() -> new DomainNotFound(id));
    }

    public Domain findByDomainName(String name) {
        return domainRepo
                .findByName(name)
                .orElseThrow(() -> new DomainNotFound(name));
    }

    public void deleteDomain(Integer id) {

        // Fetching the Domain
        Domain domain = domainRepo
                .findById(id)
                .orElseThrow(() -> new DomainNotFound(id));

        // Manually deleting all the entries with the levels
        domain.getLevels().forEach(level -> userLevelRepo.deleteByLevel_Id(level.getId()));
        domainRepo.deleteById(id);
    }
}