package egecoskun121.com.crm.security;

import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("Could not find the User");
        }

        return new MyUserDetails(user);
    }
}
