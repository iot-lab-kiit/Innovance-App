package in.iot.lab.innovance.controller;


import in.iot.lab.innovance.constants.UrlConstants;
import in.iot.lab.innovance.entity.Domain;
import in.iot.lab.innovance.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DomainController {

    private final DomainService domainService;

    @PostMapping(UrlConstants.CREATE_DOMAIN)
    public ResponseEntity<Domain> createDomainHandler(@RequestBody Domain domain) {
        Domain createdDomain = domainService.createDomain(domain);
        return ResponseEntity.ok(createdDomain);
    }

    @GetMapping(UrlConstants.FIND_ALL_DOMAIN)
    public ResponseEntity<List<Domain>> findAllDomainHandler() {
        List<Domain> domainList = domainService.findAllDomain();
        return ResponseEntity.ok(domainList);
    }

    @GetMapping(UrlConstants.FIND_DOMAIN_BY_ID)
    public ResponseEntity<Domain> findDomainByIdHandler(@PathVariable Integer id) {
        Domain foundDomain = domainService.findByDomainId(id);
        return ResponseEntity.ok(foundDomain);
    }

    @GetMapping(UrlConstants.FIND_DOMAIN_BY_NAME)
    public ResponseEntity<Domain> findDomainByNameHandler(@PathVariable String name) {
        Domain foundDomain = domainService.findByDomainName(name);
        return ResponseEntity.ok(foundDomain);
    }

    @DeleteMapping(UrlConstants.DELETE_DOMAIN)
    public ResponseEntity<Void> deleteDomainHandler(@PathVariable Integer id) {
        domainService.deleteDomain(id);
        return ResponseEntity.ok().build();
    }
}