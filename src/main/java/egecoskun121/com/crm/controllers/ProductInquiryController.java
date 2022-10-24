package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import egecoskun121.com.crm.model.mapper.ProductInquiryMapperImpl;
import egecoskun121.com.crm.services.ProductInquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/v1/inquiry")
public class ProductInquiryController {

    private final ProductInquiryService productInquiryService;
    private ProductInquiryMapperImpl productInquiryMapper;

    public ProductInquiryController(ProductInquiryService productInquiryService, ProductInquiryMapperImpl productInquiryMapper) {
        this.productInquiryService = productInquiryService;
        this.productInquiryMapper = productInquiryMapper;
    }

    @RequestMapping(path = "/showList")
    public ModelAndView showProductInquiryList(){
        ModelAndView mav = new ModelAndView("productInquiryList");
        mav.addObject("productInquiry",productInquiryService.getAllProductInquiries());
        return mav;
    }

    @RequestMapping(path="/updateInquiry")
    public RedirectView updateInquiry(@RequestParam Long id,@ModelAttribute ProductInquiryDTO productInquiryDTO){

        productInquiryService.updateProductInquiryById(id, productInquiryDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showList");

        return redirectView;
    }



    @RequestMapping(path = "/deleteInquiry")
    public RedirectView deleteProductInquiry(@RequestParam Long id){

        productInquiryService.deleteProductInquiryById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showList");

        return redirectView;
    }







}
