package ua.hillel.springmvc.demo.service;

import org.springframework.stereotype.Service;
import ua.hillel.springmvc.demo.exception.UserUpdateException;
import ua.hillel.springmvc.demo.model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInMemoryService implements UserService {
    List<UserDTO> userDTOS = new ArrayList<>();

    @Override
    public UserDTO addUser(UserDTO userDTO) {
         userDTOS.add(userDTO);
         return userDTO;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return userDTOS.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDTOS;
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) throws UserUpdateException {
        if (userDTO.getId() != id) {
            throw new UserUpdateException("ids do not match");
        }

        UserDTO existingUser = userDTOS.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserUpdateException("User you're trying to update does not exist"));

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());

        return existingUser;
    }
}
