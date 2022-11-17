package egecoskun121.com.crm.services;
import java.util.*;

import egecoskun121.com.crm.exception.IncorrectPasswordException;
import egecoskun121.com.crm.model.DTO.UserDTO;
import egecoskun121.com.crm.model.entity.Role;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.model.mapper.UserMapperImpl;
import egecoskun121.com.crm.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapperImpl userMapperImpl;

    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserMapperImpl userMapperImpl, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapperImpl = userMapperImpl;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        List<User> all = userRepository.findAll();
        return all;
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUserName(username);
    }

    public User createUser(UserDTO userDTO){
        User user = userMapperImpl.toUser(userDTO);
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User changePassword(String username,String oldPassword,String newPassword){
        User user = userRepository.getUserByUserName(username);

        if(passwordEncoder.matches(oldPassword,user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
        }else{
            throw new IncorrectPasswordException("Your password is not correct !");
        }

        return userRepository.save(user);
    }


    public void deleteUserById(Long id){
        userRepository.deleteById(id );
    }


    public List<User> getAllUsersOrderedById() {
        return userRepository.getAllUsersOrderedById();
    }

    public List<String> getAllEmails(){
        return userRepository.getAllEmails();
    }

    public User update(User user){
        return userRepository.save(user);
    }
}
