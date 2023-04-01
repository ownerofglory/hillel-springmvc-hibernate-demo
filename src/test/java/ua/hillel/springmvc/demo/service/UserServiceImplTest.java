package ua.hillel.springmvc.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.hillel.springmvc.demo.exception.UserNotFoundException;
import ua.hillel.springmvc.demo.exception.UserUpdateException;
import ua.hillel.springmvc.demo.model.dto.UserDTO;
import ua.hillel.springmvc.demo.model.entity.User;
import ua.hillel.springmvc.demo.model.mapper.UserMapper;
import ua.hillel.springmvc.demo.repo.UserRepo;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    private UserRepo userRepoMock;
    @Mock
    private UserMapper userMapperMock;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepoMock, userMapperMock);
    }

    @Test
    public void getUserByIdTest_success() throws UserNotFoundException {
        when(userRepoMock.findById(anyInt())).thenReturn(new User());
        when(userMapperMock.UserToUserDTO(any())).thenReturn(new UserDTO());

        UserDTO userById = userService.getUserById(100);
        assertNotNull(userById);
    }

    @Test
    public void getUserByIdTest_throwsException() {
        when(userRepoMock.findById(anyInt())).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            UserDTO userById = userService.getUserById(100);
        });
    }
    @Test
    public void addUserTest_success() {
        when(userMapperMock.UserToUserDTO(any())).thenReturn(new UserDTO());
        when(userMapperMock.UserDTOToUser(any())).thenReturn(new User());
        when(userRepoMock.save(any())).thenReturn(new User());

        UserDTO savedUser = userService.addUser(new UserDTO());
        assertNotNull(savedUser);
    }
    @Test
    public void updateTest_throwsException() {
        UserDTO userDTO = new UserDTO();
        Integer testId = 100;

        assertThrows(UserUpdateException.class, () -> {
            userService.updateUser(testId, userDTO);
        });
    }

    @Test
    public void updateTest_success() throws UserUpdateException {
        UserDTO userDTO = new UserDTO();
        Integer testId = 100;
        userDTO.setId(testId);

        when(userMapperMock.UserDTOToUser(any())).thenReturn(new User());
        when(userRepoMock.save(any())).thenReturn(new User());

        userService.updateUser(testId, userDTO);
    }
    @Test
    public void getAllUsersTest_success() {
        when(userRepoMock.getAll()).thenReturn(new LinkedList<>() {{
            add(new User());
        }});
        when(userMapperMock.UserToUserDTO(any())).thenReturn(new UserDTO());

        List<UserDTO> allUsers = userService.getAllUsers();
        assertNotNull(allUsers);
        assertFalse(allUsers.isEmpty());
    }
}
