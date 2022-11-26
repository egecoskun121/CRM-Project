package egecoskun121.com.crm.services;


import egecoskun121.com.crm.exception.NotFoundException;
import egecoskun121.com.crm.model.DTO.ProductDTO;
import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.entity.User;
import egecoskun121.com.crm.model.mapper.ProductMapper;
import egecoskun121.com.crm.model.mapper.ProductMapperImpl;
import egecoskun121.com.crm.repositories.ProductRepository;
import egecoskun121.com.crm.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapperImpl productMapperImpl;
    private final UserRepository userRepository;

    private final UserService userService;

    public ProductService(ProductRepository productRepository, ProductMapperImpl productMapperImpl, UserRepository userRepository, UserService userService) {
        this.productRepository = productRepository;
        this.productMapperImpl = productMapperImpl;
        this.userRepository = userRepository;
        this.userService = userService;
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

    public List<Product> getAllProductsByUsername(String username){
        List<Product> list =  productRepository.findAllProductsByName(username);;
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

    public Integer getProductCategoryCountsByUsername(int categoryNumber,String username){
        return productRepository.findProductCategoryByUsername(categoryNumber,username);
    }

    public void addProductToUser(String productName,String username){
        Product product = productRepository.findProductByProductName(productName);
        User user = userService.getUserByUsername(username);
        product.setUser(user);
        List<Product> productList = user.getProducts();
        productList.add(product);
        user.setProducts(productList);
        userRepository.save(user);
    }

    public double getSumOfPrices(){
        return productRepository.getSumOfPrices();
    }

    public double getSumOfPricesWithUsername(String username){
        return productRepository.getSumOfPricesWithUsername(username);
    }

    public List<Map<Integer,Integer>> getProductCategoryCountsWithUsername(String username){
        return productRepository.getCategoryCountsWithUsername(username);
    }

    public List<Product> getAllProductsWithNullId(){
        return productRepository.getAllProductsWithIdNull();
    }

    public List<Product> getMaxCategoryListOfProduct(String username){
        return productRepository.getProductsByMaxCategory(productRepository.getMaxCountProductCategory(username));
    }

    public List<Integer> getTotalPriceOfDate(String username){
        List<Integer> dates = new ArrayList<>();
        for (int i=1;i<=12;i++){
            if(i<10){
                dates.add(productRepository.getTotalPriceOfDate("2022-0"+i,username));
            }else{
                dates.add(productRepository.getTotalPriceOfDate("2022-"+i,username));
            }
        }

        for(int i = 0;i<=11;i++){
            if(dates.get(i)==null){
                dates.set(i,0);
            }
        }
        return dates;
    }

}
