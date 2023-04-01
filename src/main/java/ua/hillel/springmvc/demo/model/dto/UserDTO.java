package ua.hillel.springmvc.demo.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
