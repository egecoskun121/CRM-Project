package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.UserDTO;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public RedirectView saveUser(@ModelAttribute UserDTO userDTO){
        userService.createUser(userDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/user/main");
        return redirectView;
    }

    @GetMapping("/getAllUsers")
    public ModelAndView getAllUsers(){
       ModelAndView mav = new ModelAndView("main");
       mav.addObject("users",userService.getAllUsers());
        return mav;
    }






}
