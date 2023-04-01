package ua.hillel.springmvc.demo.model.mapper;

import org.mapstruct.Mapper;
import ua.hillel.springmvc.demo.model.dto.UserDTO;
import ua.hillel.springmvc.demo.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserDTOToUser(UserDTO userDTO);
    UserDTO UserToUserDTO(User user);
}
