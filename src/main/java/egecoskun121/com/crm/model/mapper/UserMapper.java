package egecoskun121.com.crm.model.mapper;

import egecoskun121.com.crm.model.DTO.UserDTO;
import egecoskun121.com.crm.model.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}
