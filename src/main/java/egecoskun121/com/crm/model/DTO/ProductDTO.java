package egecoskun121.com.crm.model.DTO;

import egecoskun121.com.crm.model.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String imageURL;

    private Long productCode;

    @Size(min = 3,max = 100)
    private String productName;

    @Size(min = 5,max = 100)
    private String details;

    private BigDecimal price;

    private ProductCategory productCategory;
}
