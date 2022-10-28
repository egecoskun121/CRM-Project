package egecoskun121.com.crm.services;


import egecoskun121.com.crm.exception.NotFoundException;
import egecoskun121.com.crm.model.DTO.ProductDTO;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.mapper.ProductMapper;
import egecoskun121.com.crm.model.mapper.ProductMapperImpl;
import egecoskun121.com.crm.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapperImpl productMapperImpl;

    public ProductService(ProductRepository productRepository, ProductMapperImpl productMapperImpl) {
        this.productRepository = productRepository;
        this.productMapperImpl = productMapperImpl;
    }

    public Product getById(Long productId){
        return productRepository.findById(productId).orElseThrow(NotFoundException::new);
    }

    public Product saveNewProduct(ProductDTO productDTO){
        return productRepository.save(productMapperImpl.toProduct(productDTO));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getAllProductsById(Long id){
        List<Product> list =  productRepository.findAllProductsById(id);;
        return list;
    }

    public Product updateProductById(Long productId,ProductDTO productDTO){
        Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);
        product.setProductName(productDTO.getProductName());
        product.setProductCategory(productDTO.getProductCategory());
        product.setProductCode(productDTO.getProductCode());
        product.setDetails(productDTO.getDetails());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product);
    }

    public void deleteProductById(Long productId){
        productRepository.delete(productRepository.findById(productId).orElseThrow(NotFoundException::new));
    }

    public Integer getProductCategoryCounts(int categoryNumber){
     return productRepository.findByProductCategory(categoryNumber);
    }



}
