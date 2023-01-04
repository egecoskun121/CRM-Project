package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.PasswordDTO;
import egecoskun121.com.crm.model.DTO.UserDTO;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.repositories.UserRepository;
import egecoskun121.com.crm.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/saveUser")
    public ModelAndView saveUser(){
       ModelAndView mav = new ModelAndView("register");
       User user = new User();
       mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/addUser")
    public RedirectView addUser(@ModelAttribute UserDTO userDTO){
        RedirectView redirectView = new RedirectView();
        boolean canBeCreated=true;
        for (String username: userRepository.getAllUsernames()) {
            if(userDTO.getUserName().equals(username)){
                canBeCreated=false;
            }
        }
        if(canBeCreated){
            userService.createUser(userDTO);
            redirectView.setUrl("http://localhost:8093/");
        }else{
            redirectView.setUrl("http://localhost:8093/saveUser");
        }

        return  redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping("/showUserDetails")
    public ModelAndView getUserDetails(@RequestParam String username){
        ModelAndView mav = new ModelAndView("my-profile");
        User user = userService.getUserByUsername(username);
        mav.addObject("user",user);
        return mav;
    }

    @GetMapping("/getAllUsers")
    public ModelAndView getAllUsers(){
       ModelAndView mav = new ModelAndView("main");
       mav.addObject("users",userService.getAllUsers());
        return mav;
    }


    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam String username){
        ModelAndView mav = new ModelAndView("change-password");
        User user = userService.getUserByUsername(username);
        PasswordDTO passwordDTO = new PasswordDTO();
        mav.addObject("passwordDTO",passwordDTO);
        mav.addObject("user",user);
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping("/submitPassword/{username}")
    public RedirectView submitChangedPassword(@PathVariable("username") String username, @ModelAttribute PasswordDTO passwordDTO){
        userService.changePassword(username, passwordDTO.getOldPassword(), passwordDTO.getNewPassword());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/dashboard/dashboardForUser?username="+username);
        return redirectView;
    }

}
