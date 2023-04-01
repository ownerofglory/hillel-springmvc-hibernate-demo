package ua.hillel.springmvc.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.hillel.springmvc.demo.exception.UserNotFoundException;
import ua.hillel.springmvc.demo.exception.UserUpdateException;
import ua.hillel.springmvc.demo.model.dto.UserDTO;
import ua.hillel.springmvc.demo.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ControllerTestConfig.class})
public class UserControllerTest {
    @Mock
    private UserService userServiceMock;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        UserController userController = new UserController(userServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsersTest_returnsOk() throws Exception {
        when(userServiceMock.getAllUsers()).thenReturn(List.of(new UserDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getUserByIdTest_returnsOk() throws Exception {
        when(userServiceMock.getUserById(anyInt())).thenReturn(new UserDTO());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addUserTest_returnsOk() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setEmail("test@test.com");

        String requestBody = objectMapper.writeValueAsString(userDTO);

        when(userServiceMock.addUser(any())).thenReturn(userDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(requestBody)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        byte[] responseBytes = mvcResult.getResponse().getContentAsByteArray();
        UserDTO userResponse = objectMapper.readValue(responseBytes, UserDTO.class);

        assertNotNull(userResponse);
        assertEquals(userResponse.getFirstName(), userDTO.getFirstName());
    }

    @Test
    public void updateUserTest_ok() throws Exception {
        Integer testId = 1;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(testId);
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setEmail("test@test.com");

        String requestBody = objectMapper.writeValueAsString(userDTO);

        when(userServiceMock.updateUser(anyInt(), any())).thenReturn(userDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/users/" + testId)
                        .content(requestBody)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        byte[] contentBytes = mvcResult.getResponse().getContentAsByteArray();
        UserDTO updatedUser = objectMapper.readValue(contentBytes, UserDTO.class);

        assertEquals(userDTO, updatedUser);
    }
}
