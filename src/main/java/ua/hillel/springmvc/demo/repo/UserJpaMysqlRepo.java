package ua.hillel.springmvc.demo.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.hillel.springmvc.demo.model.entity.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserJpaMysqlRepo implements UserRepo {
    private final SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(user);
            entityManager.flush();

            entityManager.getTransaction().commit();

            return user;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public User update(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            User updatedUser = entityManager.merge(user);
            entityManager.flush();

            entityManager.getTransaction().commit();

            return updatedUser;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public User remove(User user) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            entityManager.remove(user);
            entityManager.flush();

            entityManager.getTransaction().commit();

            return user;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public User findById(Integer id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            User user = entityManager.find(User.class, id);
            entityManager.flush();

            entityManager.getTransaction().commit();

            return user;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = query.getResultList();
            entityManager.flush();

            entityManager.getTransaction().commit();

            return users;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}
