package ua.hillel.springmvc.demo.service;

import ua.hillel.springmvc.demo.exception.UserNotFoundException;
import ua.hillel.springmvc.demo.exception.UserUpdateException;
import ua.hillel.springmvc.demo.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO getUserById(Integer id) throws UserNotFoundException;
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Integer id, UserDTO userDTO) throws UserUpdateException;
}
