package ua.com.coach.dao;

import org.springframework.stereotype.Component;
import ua.com.coach.entity.User;

import java.util.List;

/**
 * Interface for CRUD operations with {@link User} entity in the database.
 */
@Component
public interface UserDao {

    Long save(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    boolean update(User user);

    boolean delete(Long id);

    Boolean isEmailExist(final String email);

}
