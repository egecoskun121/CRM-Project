package egecoskun121.com.crm.services;

import egecoskun121.com.crm.exception.NotFoundException;
import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import egecoskun121.com.crm.model.mapper.ProductInquiryMapper;
import egecoskun121.com.crm.model.mapper.ProductMapper;
import egecoskun121.com.crm.repositories.ProductInquiryRepository;
import egecoskun121.com.crm.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductInquiryService {

    private final ProductInquiryRepository productInquiryRepository;
    private final ProductInquiryMapper productInquiryMapper;

    public ProductInquiryService(ProductInquiryRepository productInquiryRepository, ProductInquiryMapper productInquiryMapper) {
        this.productInquiryRepository = productInquiryRepository;
        this.productInquiryMapper = productInquiryMapper;
    }

    public ProductInquiry getById(Long id){
        return productInquiryRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    public ProductInquiry saveNewProductInquiry(ProductInquiryDTO productInquiryDTO){
        return productInquiryRepository.save(productInquiryMapper.toProductInquiry(productInquiryDTO));
    }
    public ProductInquiry updateProductInquiryById(Long productInquiryId,ProductInquiryDTO productInquiryDTO){
        ProductInquiry productInquiry = productInquiryRepository.findById(productInquiryId).orElseThrow(NotFoundException::new);
        productInquiry.setProductCategory(productInquiryDTO.getProductCategory());
        productInquiry.setDetails(productInquiryDTO.getDetails());
        productInquiry.setAnswer(productInquiryDTO.getAnswer());
        productInquiry.setMail(productInquiryDTO.getMail());
        return productInquiryRepository.save(productInquiry);
    }
    public void deleteProductInquiryById(Long id){
        productInquiryRepository.delete(productInquiryRepository.findById(id).orElseThrow(NotFoundException::new));
    }

}
