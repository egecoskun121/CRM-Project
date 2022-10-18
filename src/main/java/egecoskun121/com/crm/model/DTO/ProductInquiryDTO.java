package egecoskun121.com.crm.model.DTO;

import egecoskun121.com.crm.model.entity.ProductCategory;
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

    private ProductCategory productCategory;

    @Size(min = 5,max = 100)
    private String details;

    @Size(min = 5,max = 100)
    private String answer;
}
