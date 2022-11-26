package egecoskun121.com.crm.repositories;

import egecoskun121.com.crm.model.entity.Product;
import egecoskun121.com.crm.model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.apache.commons.collections4.MultiMap;


import java.util.*;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT COUNT(PRODUCT_CATEGORY) FROM PRODUCT WHERE PRODUCT_CATEGORY = :categoryNumber", nativeQuery = true)
    Integer findByProductCategory(@Param("categoryNumber") int categoryNumber );




    @Query(value = "SELECT COUNT(PRODUCT_CATEGORY) FROM PRODUCT  WHERE USER_ID= {SELECT ID FROM USERS WHERE USER_NAME= (:username)} AND PRODUCT_CATEGORY = :categoryNumber",nativeQuery = true)
    Integer findProductCategoryByUsername(@Param("categoryNumber")int categoryNumber,@Param("username")String username);

    @Query(nativeQuery = true,name="Product.productResult")
    List<Product> findAllProductsByName(@Param("username")String name);

    Product findProductByProductName(String productName);


    @Query(value = "SELECT SUM (PRICE) FROM PRODUCT",nativeQuery = true)
    Double getSumOfPrices();

    @Query(value = "SELECT SUM (PRICE) FROM PRODUCT WHERE USER_ID = {SELECT ID FROM USERS WHERE USER_NAME= (:username)}  ",nativeQuery = true)
    Double getSumOfPricesWithUsername(@Param("username")String username );


    @Query(value = "SELECT new map (productCategory, COUNT(productCategory) as pcounter) FROM Product WHERE user.id=(SELECT id FROM User WHERE userName=(:username)) GROUP BY productCategory")
    List<Map<Integer, Integer>> getCategoryCountsWithUsername(@Param("username")String username);

    @Query(value = "SELECT * FROM PRODUCT WHERE USER_ID IS NULL ",nativeQuery = true )
    List<Product> getAllProductsWithIdNull();



     @Query(value = "SELECT PRODUCT_CATEGORY FROM PRODUCT WHERE USER_ID = (SELECT ID FROM USERS WHERE USER_NAME=(:username)) GROUP BY PRODUCT_CATEGORY ORDER BY COUNT(PRODUCT_CATEGORY) DESC LIMIT 1",nativeQuery = true)
      int getMaxCountProductCategory(@Param("username")String username);

     @Query(value = "SELECT * FROM PRODUCT WHERE PRODUCT_CATEGORY = (:productCategory) AND USER_ID IS NULL LIMIT 5",nativeQuery = true)
      List<Product> getProductsByMaxCategory(@Param("productCategory") int productCategory);


     @Query(value = "SELECT SUM(PRICE) FROM PRODUCT WHERE CREATED_DATE LIKE (:date%) AND USER_ID = {SELECT ID FROM USERS WHERE USER_NAME= (:username)} ",nativeQuery = true)
     Integer getTotalPriceOfDate(@Param("date") String date,@Param("username") String username);
}
