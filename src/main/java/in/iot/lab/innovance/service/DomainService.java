package in.iot.lab.innovance.service;

import in.iot.lab.innovance.entity.Domain;
import in.iot.lab.innovance.exception.DomainNotFound;
import in.iot.lab.innovance.repository.DomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainService {

    private final DomainRepository domainRepo;

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
        if (!domainRepo.existsById(id))
            throw new DomainNotFound(id);

        domainRepo.deleteById(id);
    }
}