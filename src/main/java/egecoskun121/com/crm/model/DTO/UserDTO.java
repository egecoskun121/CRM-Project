package egecoskun121.com.crm.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Pattern(regexp = "^[a-zA-Z\\s]*$ ", message = "Wrong format of phone number")
    private String phoneNumber;
    @Size(min = 5, max = 25, message = "Username length should be between 5 and 25 characters")
    private String userName;
    @NotNull
    private String email;
    @Size(min = 5, message = "Minimum password length: 5 characters")
    private String password;
}
