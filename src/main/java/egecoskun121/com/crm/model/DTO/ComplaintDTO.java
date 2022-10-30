package egecoskun121.com.crm.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {
    @Size(min = 5,max = 100)
    private String complaintSubject;
    @Size(min = 5,max = 100)
    private String details;
    @Size(min = 5,max = 100)
    private String answer;
    private String productName;
}
