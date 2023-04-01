package ua.hillel.springmvc.demo.model.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
}
