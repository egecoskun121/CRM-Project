package egecoskun121.com.crm.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;


@SqlResultSetMapping(name = "Product.productResult", classes = {
        @ConstructorResult(targetClass = Product.class,
                columns = {@ColumnResult(name = "ID", type = Long.class), @ColumnResult(name = "CREATED_DATE", type = String.class), @ColumnResult(name = "LAST_MODIFIED_DATE", type = String.class), @ColumnResult(name = "IMAGEURL"),
                        @ColumnResult(name = "PRODUCT_CODE", type = Long.class), @ColumnResult(name = "PRODUCT_NAME")
                        , @ColumnResult(name = "DETAILS"), @ColumnResult(name = "PRICE", type = BigDecimal.class), @ColumnResult(name = "PRODUCT_CATEGORY")})
})
@NamedNativeQuery(
        name = "Product.productResult",
        resultClass = Product.class,
        query = "SELECT ID,CREATED_DATE,LAST_MODIFIED_DATE,IMAGEURL,PRODUCT_CODE,PRODUCT_NAME, DETAILS, PRICE, PRODUCT_CATEGORY FROM PRODUCT  WHERE USER_ID= {SELECT ID FROM USERS WHERE USER_NAME= (:username)}",
        resultSetMapping = "Product.productResult")
@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    private String imageURL;
    private Long productCode;
    @Size(min = 3, max = 100)
    private String productName;
    @Size(min = 5, max = 100)
    private String details;
    private BigDecimal price;
    private ProductCategory productCategory;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Product(Long id, String createdDate, String lastModifiedDate, String imageURL, Long productCode, String productName, String details, BigDecimal price, Integer productCategory) {
        this.id = id;
        this.createdDate = Timestamp.valueOf(createdDate);
        this.lastModifiedDate = Timestamp.valueOf(lastModifiedDate);
        this.imageURL = imageURL;
        this.productCode = productCode;
        this.productName = productName;
        this.details = details;
        this.price = price;
        int i = 0;
        for (ProductCategory productCategory1 : ProductCategory.values()) {
            if (productCategory == i) {
                this.productCategory = productCategory1;
            }
            i++;
        }
    }


}
