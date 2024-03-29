package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ProductDTO;
import egecoskun121.com.crm.model.DTO.RegisterObject;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.services.ProductInquiryService;
import egecoskun121.com.crm.services.ProductService;
import egecoskun121.com.crm.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.*;



@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    private final ProductInquiryService productInquiryService;


    public ProductController(ProductService productService, UserService userService, ProductInquiryService productInquiryService) {
        this.productService = productService;
        this.userService = userService;
        this.productInquiryService = productInquiryService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/showAllProducts")
    public ModelAndView showProductList(){
        ModelAndView modelAndView = new ModelAndView("product-list");
        modelAndView.addObject("productList",productService.getAllProducts());
        return modelAndView;
    }


    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/showAllProductsByUsername")
    public ModelAndView showProductListByUsername(@RequestParam String username){
        ModelAndView modelAndView = new ModelAndView("product-list-by-username");
        modelAndView.addObject("productList",productService.getAllProductsByUsername(username));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/addNewProduct")
    public ModelAndView addNewProduct(@ModelAttribute ProductDTO productDTO){
        ModelAndView mav = new ModelAndView("add-product");
        Product product = new Product();
        mav.addObject("product",product);
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/saveNewProduct")
    public  RedirectView saveNewProduct(@ModelAttribute ProductDTO productDTO){
        productService.saveNewProduct(productDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");
        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/updateProduct/{id}")
    public RedirectView updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductDTO productDTO){

        productService.updateProductById(id, productDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");

        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("update-product-form");
        Product product = productService.getById(id);
        mav.addObject("product",product);
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/deleteProduct")
    public RedirectView deleteProduct(@RequestParam Long id){

        productService.deleteProductById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");

        return redirectView;
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @RequestMapping(path = "/categoryList")
//    public ModelAndView showCategoryList(){
//        Map<String,Integer> map = new LinkedHashMap<>();
//        int categoryCounter=0;
//        for (ProductCategory  a: ProductCategory.values()) {
//            map.put(a.toString(),productService.getProductCategoryCounts(categoryCounter++));
//        }
//
//        ModelAndView mav = new ModelAndView("product-category-list");
//        mav.addObject("categoryCountList",map);
//
//        return mav;
//    }
//
//    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
//    @RequestMapping(path ="/categoryListByUsername")
//    public ModelAndView showCategoryListByUsername(@RequestParam String username){
//        Map<String, Integer> map = new LinkedHashMap<>();
//        int categoryCounter=0;
//        for (ProductCategory  a: ProductCategory.values()) {
//            map.put(a.toString(),productService.getProductCategoryCountsByUsername(categoryCounter++,username));
//        }
//        ModelAndView mav =new ModelAndView("product-category-list-by-username");
//        mav.addObject("categoryCountList",map);
//
//        return mav;
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/addProductToUser")
    public ModelAndView addProductToUser(){
        ModelAndView mav = new ModelAndView("add-product-to-user");
        RegisterObject registerObject = new RegisterObject();
        List<User> userList = userService.getAllUsers();
        List<Product> productList = productService.getAllProductsWithNullId();
        mav.addObject("dummy", registerObject);
        mav.addObject("userList",userList);
        mav.addObject("productList",productList);
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/saveProductToUser")
    //RegisterObject holds username and product name
    //This way we can send that RegisterObject to controller
    public RedirectView saveProductToUser(@ModelAttribute RegisterObject registerObject){

        productService.addProductToUser(registerObject.getProductName(), registerObject.getUsername());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");
        return redirectView;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/addProductToUserWithInquiry/{id}/{username}")
    public ModelAndView addProductToUserWithInquiry(@PathVariable("id") Long id,@PathVariable("username") String username){
        ModelAndView mav = new ModelAndView("add-product-to-user-with-inquiry");
        RegisterObject registerObject = new RegisterObject();
        User userList = userService.getUserByUsername(username);
        ProductInquiry productInquiry = productInquiryService.getById(id);
        mav.addObject("dummy", registerObject);
        mav.addObject("userList",userList);
        mav.addObject("productList",productInquiry);
        return mav;
    }
}
