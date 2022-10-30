package egecoskun121.com.crm.repositories;


import egecoskun121.com.crm.model.entity.ProductInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInquiryRepository extends JpaRepository<ProductInquiry,Long> {

    @Query(nativeQuery = true,name="ProductInquiry.productInquiryResult")
    List<ProductInquiry> findAllProductsInquiriesByName(@Param("username")String name);
}
