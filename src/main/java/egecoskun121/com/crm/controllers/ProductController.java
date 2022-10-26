package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ProductDTO;
import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.mapper.ProductMapperImpl;
import egecoskun121.com.crm.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @RequestMapping(path = "/showAllProducts")
    public ModelAndView showProductList(){
        ModelAndView modelAndView = new ModelAndView("product-list");
        modelAndView.addObject("productList",productService.getAllProducts());
        return modelAndView;
    }

    @RequestMapping(path = "/addNewProduct")
    public ModelAndView addNewProduct(@ModelAttribute ProductDTO productDTO){
       ModelAndView mav = new ModelAndView("add-product");
        Product product = new Product();
        mav.addObject("product",product);
        return mav;
    }

    public  RedirectView saveNewProduct(@ModelAttribute ProductDTO productDTO){
        productService.saveNewProduct(productDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");
        return redirectView;
    }

    @RequestMapping(path="/updateProduct/{id}")
    public RedirectView updateInquiry(@PathVariable("id") Long id, @ModelAttribute ProductDTO productDTO){

        productService.updateProductById(id, productDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");

        return redirectView;
    }

    @RequestMapping(path = "/deleteProduct")
    public RedirectView deleteProductInquiry(@RequestParam Long id){

        productService.deleteProductById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");

        return redirectView;
    }
}
