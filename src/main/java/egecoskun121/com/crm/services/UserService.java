package egecoskun121.com.crm.services;
import java.util.*;

import egecoskun121.com.crm.model.DTO.UserDTO;
import egecoskun121.com.crm.model.entity.Role;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.model.mapper.UserMapperImpl;
import egecoskun121.com.crm.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapperImpl userMapperImpl;

    public UserService(UserRepository userRepository, UserMapperImpl userMapperImpl) {
        this.userRepository = userRepository;
        this.userMapperImpl = userMapperImpl;
    }


    public List<User> getAllUsers(){
        List<User> all = userRepository.findAll();
        return all;
    }

    public User createUser(UserDTO userDTO){
        User user = userMapperImpl.toUser(userDTO);
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }


    public void deleteUserById(Long id){
        userRepository.deleteById(id );
    }



}
