package egecoskun121.com.crm.repositories;

import egecoskun121.com.crm.model.entity.Product;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.*;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT COUNT(PRODUCT_CATEGORY) FROM PRODUCT WHERE PRODUCT_CATEGORY = :categoryNumber", nativeQuery = true)
    Integer findByProductCategory(@Param("categoryNumber") int categoryNumber );


    @Query(value = "SELECT COUNT(PRODUCT_CATEGORY) FROM (SELECT p.PRODUCT_CATEGORY FROM PRODUCT AS p INNER JOIN  USERS_PRODUCTS AS u ON u.PRODUCTS_ID = p.ID WHERE u.USER_ID= {SELECT ID FROM USERS WHERE USER_NAME= (:username)}) WHERE PRODUCT_CATEGORY = :categoryNumber",nativeQuery = true)
    Integer findProductCategoryByUsername(@Param("categoryNumber")int categoryNumber,@Param("username")String username);

    @Query(nativeQuery = true,name="Product.productResult")
    List<Product> findAllProductsByName(@Param("username")String name);
}
