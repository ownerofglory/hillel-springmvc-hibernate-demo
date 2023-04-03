package ua.hillel.springmvc.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.springmvc.demo.exception.UserNotFoundException;
import ua.hillel.springmvc.demo.exception.UserUpdateException;
import ua.hillel.springmvc.demo.model.dto.UserDTO;
import ua.hillel.springmvc.demo.service.UserService;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) throws UserNotFoundException {
        UserDTO userById = userService.getUserById(id);

        return ResponseEntity.ok(userById);
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO addedUser = userService.addUser(userDTO);
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) throws UserUpdateException {
        UserDTO upodatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(upodatedUser);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity<Void> deleteUserById(@PathVariable("id") Integer id) throws UserNotFoundException {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
