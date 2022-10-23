package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.mapper.ProductInquiryMapperImpl;
import egecoskun121.com.crm.services.ProductInquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/inquiry")
public class ProductInquiryController {

    private final ProductInquiryService productInquiryService;
    private ProductInquiryMapperImpl productInquiryMapper;

    public ProductInquiryController(ProductInquiryService productInquiryService, ProductInquiryMapperImpl productInquiryMapper) {
        this.productInquiryService = productInquiryService;
        this.productInquiryMapper = productInquiryMapper;
    }

    @GetMapping("/showList")
    public ModelAndView showProductInquiryList(){
        ModelAndView mav = new ModelAndView("productInquiryList");
        mav.addObject("productInquiry",productInquiryService.getAllProductInquiries());
        return mav;
    }


}
