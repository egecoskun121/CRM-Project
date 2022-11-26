package egecoskun121.com.crm.controllers;


import egecoskun121.com.crm.model.entity.Complaint;
import egecoskun121.com.crm.model.entity.ProductCategory;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.services.ComplaintService;
import egecoskun121.com.crm.services.ProductInquiryService;
import egecoskun121.com.crm.services.ProductService;
import egecoskun121.com.crm.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
@RequestMapping(path = "/dashboard")
public class DashboardController {

    private final ProductService productService;
    private final ProductInquiryService productInquiryService;
    private final UserService userService;

    private final ComplaintService complaintService;


    public DashboardController(ProductService productService, ProductInquiryService productInquiryService, UserService userService, ComplaintService complaintService) {
        this.productService = productService;
        this.productInquiryService = productInquiryService;
        this.userService = userService;
        this.complaintService = complaintService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "")
    public ModelAndView getDashboard(){
        ModelAndView mav = new ModelAndView("dashboard");
        int productCount=productService.getAllProducts().size();
        int userCount=userService.getAllUsers().size();
        int productCategoryCount= ProductCategory.values().length;
        double totalPrice=productService.getSumOfPrices();
        List<ProductInquiry> productInquiryList = productInquiryService.getAllProductInquiriesOrderedById();
        List<User> userList = userService.getAllUsersOrderedById();

        mav.addObject("productCount",productCount);
        mav.addObject("userCount",userCount);
        mav.addObject("productCategoryCount",productCategoryCount);
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("productInquiryList",productInquiryList);
        mav.addObject("userList",userList);
        User user = userService.getUserByUsername("burak123");

        List<String> categories=new ArrayList<>();
        for(int i=0;i<productService.getProductCategoryCountsWithUsername("egecoskun").size();i++) {
            for(int j=0;j<=1;j++){
                categories.add(String.valueOf(productService.getProductCategoryCountsWithUsername("egecoskun").get(i).values().stream().toList().get(j)));
            }
        }
        mav.addObject("categoryList",categories);
        mav.addObject("user",user);

        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/dashboardForUser")
    public ModelAndView getDashboardForUser(@RequestParam String username){
        ModelAndView mav = new ModelAndView("dashboard-for-user");
        int productCount=productService.getAllProductsByUsername(username).size();
        double totalPrice=productService.getSumOfPricesWithUsername(username);
        List<ProductInquiry> productInquiryList = productInquiryService.getAllProductInquiriesByUsername(username);
        List<Complaint> complaintList = complaintService.getAllComplaintsByUsername(username);

        mav.addObject("complaintList",complaintList);
        mav.addObject("productInquiryList",productInquiryList);
        mav.addObject("totalPrice",totalPrice);
        mav.addObject("productCount",productCount);
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/main")
    public ModelAndView getMainPage(){
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }

    @RequestMapping(path = "/isUserHappyTrue")
    public void isUserHappyTrue(@RequestParam String username){
        User user = userService.getUserByUsername(username);
        user.setIsUserHappy(true);
        user.setIsPopupShowed(true);
        userService.update(user);
    }

    @RequestMapping(path = "/isUserHappyFalse")
    public void isUserHappyFalse(@RequestParam String username){
        User user = userService.getUserByUsername(username);
        user.setIsUserHappy(false);
        user.setIsPopupShowed(true);
        userService.update(user);
    }

    @RequestMapping("/chart")
    public ModelAndView chart(@RequestParam String username){
        ModelAndView mav = new ModelAndView("chart-js");

        List<String> categories=new ArrayList<>();
        for(int i=0;i<productService.getProductCategoryCountsWithUsername(username).size();i++) {
           for(int j=0;j<=1;j++){
                   categories.add(String.valueOf(productService.getProductCategoryCountsWithUsername(username).get(i).values().stream().toList().get(j)));
           }
        }
        mav.addObject("categoryList",categories);

        return mav;
    }

}
