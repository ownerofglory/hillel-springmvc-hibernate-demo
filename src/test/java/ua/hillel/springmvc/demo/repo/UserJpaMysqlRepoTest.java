package ua.hillel.springmvc.demo.repo;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.hillel.springmvc.demo.model.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RepoTestConfig.class})
public class UserJpaMysqlRepoTest {
    @Autowired
    private SessionFactory sessionFactory;
    private UserRepo userRepo;

    @BeforeEach
    public void setUp() {
        userRepo = new UserJpaMysqlRepo(sessionFactory);
    }

    @Test
    public void saveTest_success() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@test.com");
        User savedUser = userRepo.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    public void findByIdTest_success() {
        Integer testId = 1;
        User userById = userRepo.findById(1);

        assertNotNull(userById);
        assertEquals(userById.getId(), testId);
    }

    @Test
    public void getAllTest_success() {
        List<User> allUsers = userRepo.getAll();

        assertNotNull(allUsers);
        assertFalse(allUsers.isEmpty());
    }
}
