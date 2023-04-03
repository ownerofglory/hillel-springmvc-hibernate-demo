package ua.hillel.springmvc.demo.repo;

import ua.hillel.springmvc.demo.model.entity.User;

import java.util.List;

public interface UserRepo {
    User save(User user);
    User update(User user);
    User remove(User user);
    User findById(Integer id);
    List<User> getAll();
}
