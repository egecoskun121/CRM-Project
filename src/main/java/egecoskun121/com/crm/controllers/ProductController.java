package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ProductDTO;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.entity.ProductCategory;
import egecoskun121.com.crm.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.*;



@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @RequestMapping(path = "/showAllProducts")
    public ModelAndView showProductList(){
        ModelAndView modelAndView = new ModelAndView("showAllProducts");
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


    @RequestMapping(path = "/saveNewProduct")
    public  RedirectView saveNewProduct(@ModelAttribute ProductDTO productDTO){
        productService.saveNewProduct(productDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");
        return redirectView;
    }

    @RequestMapping(path="/updateProduct/{id}")
    public RedirectView updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductDTO productDTO){

        productService.updateProductById(id, productDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");

        return redirectView;
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("update-Product-Form");
        Product product = productService.getById(id);
        mav.addObject("product",product);
        return mav;
    }

    @RequestMapping(path = "/deleteProduct")
    public RedirectView deleteProduct(@RequestParam Long id){

        productService.deleteProductById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/product/showAllProducts");

        return redirectView;
    }

    @RequestMapping(path = "/categoryList")
    public ModelAndView showCategoryList(){
        Map<String,Integer> map = new LinkedHashMap<>();
        int i=0;
        for (ProductCategory  a: ProductCategory.values()) {
            map.put(a.toString(),productService.getProductCategoryCounts(i++));
        }

        ModelAndView mav = new ModelAndView("product-category-list");
        mav.addObject("categoryCountList",map);

        return mav;
    }


}
