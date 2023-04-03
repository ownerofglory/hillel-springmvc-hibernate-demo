package ua.hillel.springmvc.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.hillel.springmvc.demo.exception.UserNotFoundException;
import ua.hillel.springmvc.demo.exception.UserUpdateException;
import ua.hillel.springmvc.demo.model.dto.UserDTO;
import ua.hillel.springmvc.demo.model.entity.User;
import ua.hillel.springmvc.demo.model.mapper.UserMapper;
import ua.hillel.springmvc.demo.repo.UserRepo;

import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = userMapper.UserDTOToUser(userDTO);
        User savedUser = userRepo.save(user);

        return userMapper.UserToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Integer id) throws UserNotFoundException {
        User user = userRepo.findById(id);
        if (user == null) {
            throw new UserNotFoundException("Unable to find user with id: " + id);
        }
        return userMapper.UserToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = userRepo.getAll()
                .stream()
                .map(userMapper::UserToUserDTO)
                .toList();

        return userDTOs;
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) throws UserUpdateException {
        if (userDTO.getId() != id) {
            throw new UserUpdateException("ids do not match");
        }

        User user = userMapper.UserDTOToUser(userDTO);

        User updatedUser = userRepo.update(user);
        return userMapper.UserToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) throws UserNotFoundException {
        User existingUser = userRepo.findById(id);
        if (existingUser == null) {
            throw new UserNotFoundException("User you're trying to delete doesn't exist");
        }

        userRepo.remove(existingUser);
    }
}
