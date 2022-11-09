package egecoskun121.com.crm.model.DTO;

import egecoskun121.com.crm.model.entity.ProductCategory;
import egecoskun121.com.crm.model.entity.ProductInquiryAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInquiryDTO {
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @Email
    private String mail;

    @Size(min = 5,max = 100)
    private String details;

    @Size(min = 5,max = 100)
    private String answer;

    private String productName;

    private ProductInquiryAnswer productInquiryAnswer;
}
