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




    @Query(nativeQuery = true,name="Product.productResult")
    List<Product> findAllProductsById(@Param("id")Long id);
}
