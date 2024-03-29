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
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping(path = "/api/v1/dashboard")
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

        mav.addObject("totalPrices",productService.getTotalPriceOfDateAdmin());
        mav.addObject("categoryList",productService.getProductCategoryCounts());
        mav.addObject("productCount",productService.getAllProducts().size());
        mav.addObject("userCount",userService.getAllUsers().size());
        mav.addObject("productCategoryCount",ProductCategory.values().length);
        mav.addObject("totalPrice",productService.getSumOfPrices());
        mav.addObject("productInquiryList",productInquiryService.getAllProductInquiriesOrderedById());
        mav.addObject("userList",userService.getAllUsersOrderedById());
        mav.addObject("complaintList",complaintService.getAllComplaints());

        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/dashboardForUser")
    public ModelAndView getDashboardForUser(@RequestParam String username){
        ModelAndView mav = new ModelAndView("dashboard-for-user");

        List<String> categories=new ArrayList<>();
        for(int i=0;i<productService.getProductCategoryCountsWithUsername(username).size();i++) {
            for(int j=0;j<=1;j++){
                categories.add(String.valueOf(productService.getProductCategoryCountsWithUsername(username).get(i).values().stream().toList().get(j)));
            }
        }
        List<Complaint> complaintList= complaintService.getAllComplaintsByUsername(username);
        List<ProductInquiry> productInquiryList= productInquiryService.getAllProductInquiriesByUsername(username);
        mav.addObject("complaintCount",complaintList.size());
        mav.addObject("inquiryCount",productInquiryList.size());
        mav.addObject("totalPrices",productService.getTotalPriceOfDate(username));
        mav.addObject("categoryList",categories);
        mav.addObject("complaintList",complaintList);
        mav.addObject("productInquiryList",productInquiryList);
        mav.addObject("totalPrice",productService.getSumOfPricesWithUsername(username));
        mav.addObject("productCount",productService.getAllProductsByUsername(username).size());

        mav.addObject("user",userService.getUserByUsername(username));

        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/main")
    public ModelAndView getMainPage(){
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/isUserHappyTrue")
    public RedirectView isUserHappyTrue(@RequestParam String username){
        User user = userService.getUserByUsername(username);
        user.setIsUserHappy(true);
        user.setIsPopupShowed(true);
        userService.update(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/dashboard/dashboardForUser?username="+username);

        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/isUserHappyFalse")
    public RedirectView isUserHappyFalse(@RequestParam String username){
        User user = userService.getUserByUsername(username);
        user.setIsUserHappy(false);
        user.setIsPopupShowed(true);
        userService.update(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/dashboard/dashboardForUser?username="+username);

        return redirectView;
    }

    @RequestMapping("/chart")
    public ModelAndView chart(@RequestParam String username){
        ModelAndView mav = new ModelAndView("chart-js");

        List<Integer> totalPrices=productService.getTotalPriceOfDate(username);

        mav.addObject("totalPrices",totalPrices);


        return mav;
    }



}
