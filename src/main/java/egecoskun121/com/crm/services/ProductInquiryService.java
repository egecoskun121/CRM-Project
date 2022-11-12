package egecoskun121.com.crm.services;

import egecoskun121.com.crm.exception.NotFoundException;
import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import egecoskun121.com.crm.model.entity.ProductInquiryAnswer;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.model.mapper.ProductInquiryMapper;
import egecoskun121.com.crm.model.mapper.ProductMapper;
import egecoskun121.com.crm.repositories.ProductInquiryRepository;
import egecoskun121.com.crm.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductInquiryService {

    private final ProductInquiryRepository productInquiryRepository;

    private final UserService userService;
    private final ProductInquiryMapper productInquiryMapper;

    public ProductInquiryService(ProductInquiryRepository productInquiryRepository, UserService userService, ProductInquiryMapper productInquiryMapper) {
        this.productInquiryRepository = productInquiryRepository;
        this.userService = userService;
        this.productInquiryMapper = productInquiryMapper;
    }

    public ProductInquiry getById(Long id){
        return productInquiryRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    public ProductInquiry saveNewProductInquiry(ProductInquiryDTO productInquiryDTO,String username){
        productInquiryDTO.setProductInquiryAnswer(ProductInquiryAnswer.WAITING);
        User user = userService.getUserByUsername(username);
        ProductInquiry productInquiry = productInquiryMapper.toProductInquiry(productInquiryDTO);
        user.getProductInquiries().add(productInquiry);
        productInquiry.setUser(user);

        return productInquiryRepository.save(productInquiry);
    }
    public ProductInquiry updateProductInquiryById(Long productInquiryId,ProductInquiryDTO productInquiryDTO){
        ProductInquiry productInquiry = productInquiryRepository.findById(productInquiryId).orElseThrow(NotFoundException::new);

        productInquiry.setDetails(productInquiryDTO.getDetails());
        productInquiry.setMail(productInquiryDTO.getMail());

        return productInquiryRepository.save(productInquiry);
    }

    public List<ProductInquiry> getAllProductInquiriesByUsername(String username){
        List<ProductInquiry> list =  productInquiryRepository.findAllProductsInquiriesByName(username);;
        return list;
    }

    public List<ProductInquiry> getAllProductInquiries(){
        return productInquiryRepository.findAll();
    }
    public void deleteProductInquiryById(Long id){
        productInquiryRepository.delete(productInquiryRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public List<ProductInquiry> getAllProductInquiriesOrderedById(){
        return productInquiryRepository.findAllProductInquiriesOrderedById();
    }

}
