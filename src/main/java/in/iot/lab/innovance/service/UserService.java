package in.iot.lab.innovance.service;


import in.iot.lab.innovance.dto.UserDTO;
import in.iot.lab.innovance.entity.Domain;
import in.iot.lab.innovance.entity.User;
import in.iot.lab.innovance.exception.UserNotFound;
import in.iot.lab.innovance.repository.DomainRepository;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import in.iot.lab.innovance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserLevelChoiceRepository userLevelRepo;
    private final DomainRepository domainRepository;
    
    public UserDTO createUser(UserDTO userDTO) {
        Domain domain = domainRepository.findById(userDTO.getDomainId())
                .orElseThrow(() -> new RuntimeException("Domain not found"));
        
        User user = User.builder()
                .name(userDTO.getName())
                .rollNo(userDTO.getRollNo())
                .domain(domain)
                .build();
        
        user = userRepo.save(user);

        return user.toUserDTO();
    }
    
    
    public List<UserDTO> findAllUsers(Pageable pageable) {
        return userRepo
                .findAll(pageable)
                .stream().map(User::toUserDTO)
                .toList();
    }

    public UserDTO findUserById(Integer id) {
        return userRepo
                .findById(id)
                .map(User::toUserDTO)
                .orElseThrow(() -> new UserNotFound(id));
    }

    public void deleteUser(Integer id) {
        if (!userRepo.existsById(id))
            throw new UserNotFound(id);

        userLevelRepo.deleteByUser_Id(id);
        userRepo.deleteById(id);
    }
}