package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.UserDTO;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        userService.createUser(userDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/");
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


    @RequestMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam String username){
        ModelAndView mav = new ModelAndView("change-password");
        User user = userService.getUserByUsername(username);
        mav.addObject("user",user);
        return mav;
    }

}
