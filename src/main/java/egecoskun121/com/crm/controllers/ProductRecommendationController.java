package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.services.ProductService;
import egecoskun121.com.crm.services.UserService;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/api/v1/recommendation")
public class ProductRecommendationController {
    private ProductService productService;
    private UserService userService;

    public ProductRecommendationController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "")
    public ModelAndView getRecommendations(@RequestParam String username){
        ModelAndView modelAndView = new ModelAndView("product-recommendation");
        modelAndView.addObject("product",productService.getMaxCategoryListOfProduct(username));
        return modelAndView;
    }

}
