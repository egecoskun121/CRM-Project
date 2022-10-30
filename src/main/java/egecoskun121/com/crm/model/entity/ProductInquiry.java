package egecoskun121.com.crm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

@SqlResultSetMapping(name="ProductInquiry.productInquiryResult", classes = {
        @ConstructorResult(targetClass = ProductInquiry.class,
                columns = {@ColumnResult(name="ID",type = Long.class),@ColumnResult(name="ANSWER"), @ColumnResult(name="CREATED_DATE",type = String.class),@ColumnResult(name = "DETAILS") ,@ColumnResult(name = "LAST_MODIFIED_DATE",type = String.class),@ColumnResult(name = "MAIL"),
                        @ColumnResult(name = "PRODUCT_CATEGORY")})
})
@NamedNativeQuery(
        name = "ProductInquiry.productInquiryResult",
        resultClass = ProductInquiry.class,
        query = "SELECT p.ID,p.ANSWER,p.CREATED_DATE,p.DETAILS,p.LAST_MODIFIED_DATE,p.MAIL, p.PRODUCT_CATEGORY FROM PRODUCT_INQUIRY AS p INNER JOIN  USERS_PRODUCT_INQUIRIES  AS u ON u.PRODUCT_INQUIRIES_ID = p.ID WHERE u.USER_ID= {SELECT ID FROM USERS WHERE USER_NAME= (:username)}" ,
        resultSetMapping = "ProductInquiry.productInquiryResult")
@Data
@Entity
@Table(name = "productInquiry")
@NoArgsConstructor
public class ProductInquiry {

    public ProductInquiry(Long id,String answer,String createdDate,String details,String lastModifiedDate,String mail,Integer productCategory){
        this.id=id;
        this.answer=answer;
        this.createdDate=Timestamp.valueOf(createdDate);
        this.details=details;
        this.lastModifiedDate=Timestamp.valueOf(lastModifiedDate);
        this.mail=mail;
        int i=0;
        for (ProductCategory productCategory1: ProductCategory.values()) {
            if(productCategory==i){
                this.productCategory=productCategory1;
            }
            i++;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;


    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @Email
    private String mail;

    private ProductCategory productCategory;

    @Size(min = 5,max = 100)
    private String details;
    @Size(min = 5,max = 100)
    private String answer;

    private String productName;

}
