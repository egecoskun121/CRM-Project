package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import egecoskun121.com.crm.services.ProductInquiryService;
import egecoskun121.com.crm.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/api/v1/inquiry")
public class ProductInquiryController {

    private final ProductInquiryService productInquiryService;
    private final ProductService productService;

    public ProductInquiryController(ProductInquiryService productInquiryService, ProductService productService) {
        this.productInquiryService = productInquiryService;
        this.productService = productService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/showList")
    public ModelAndView showProductInquiryList() {
        ModelAndView mav = new ModelAndView("product-inquiry-list");
        mav.addObject("productInquiry", productInquiryService.getAllProductInquiries());
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/showAllProductInquiriesByName")
    public ModelAndView showAllProductsInquiriesByUsername(@RequestParam String username) {
        ModelAndView modelAndView = new ModelAndView("product-inquiry-list-by-username");
        modelAndView.addObject("productInquiry", productInquiryService.getAllProductInquiriesByUsername(username));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/addNewProductInquiry")
    public ModelAndView addNewProductInquiry(@RequestParam String username) {
        ModelAndView mav = new ModelAndView("product-inquiry");
        ProductInquiry productInquiry = new ProductInquiry();
        List<Product> productList = productService.getAllProductsWithNullId();
        mav.addObject("productInquiry", productInquiry);
        mav.addObject("productList", productList);
        return mav;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/saveNewProductInquiry/{username}")
    public RedirectView saveNewProductInquiry(@ModelAttribute ProductInquiryDTO productInquiryDTO, @PathVariable("username") String username) {
        productInquiryService.saveNewProductInquiry(productInquiryDTO, username);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showAllProductInquiriesByName?username=" + username);
        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/updateInquiry/{id}")
    public RedirectView updateInquiry(@PathVariable("id") Long id, @ModelAttribute ProductInquiryDTO productInquiryDTO) {

        productInquiryService.updateProductInquiryById(id, productInquiryDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showList");

        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/updateInquiryByUsername/{id}/{username}")
    public RedirectView updateInquiryByUsername(@PathVariable("id") Long id, @PathVariable("username") String username, @ModelAttribute ProductInquiryDTO productInquiryDTO) {

        productInquiryService.updateProductInquiryByIdUser(id, productInquiryDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showAllProductInquiriesByName?username=" + username);

        return redirectView;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("update-inquiry-form");
        ProductInquiry productInquiry = productInquiryService.getById(id);
        mav.addObject("productInquiry", productInquiry);
        return mav;
    }


    @RequestMapping(path = "/deleteInquiry")
    public RedirectView deleteProductInquiry(@RequestParam Long id) {

        productInquiryService.deleteProductInquiryById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showList");

        return redirectView;
    }


}
