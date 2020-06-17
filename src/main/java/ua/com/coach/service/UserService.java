package ua.com.coach.service;

import org.springframework.stereotype.Service;
import ua.com.coach.entity.User;
import java.util.List;

/**
 * Base interface with CRUD methods for implementation interaction with {@link User} entity in the database.
 */
@Service
public interface UserService {

    /**
     * Method that creates an {@link User} object.
     *
     * @param user {@link User} object that will be created.
     * @return id of {@link User} object that will be saved.
     */
    Long create(User user);

    /**
     * Method that looks for an {@link User} object by id.
     *
     * @param id {@link User} object id that client needs.
     * @return necessary {@link User} object.
     */
    User findById(Integer id);

    /**
     * Method that looks for an {@link User} object by email.
     * </p>
     * @param email of {@link User}
     * @return the {@link User}
     */
    User findByEmail(String email);

    /**
     * Method that gets All {@link User} objects.
     *
     * @return List of {@link User} objects.
     */
    List<User> findAll();

    /**
     * Method that updates an {@link User} object.
     *
     * @param user {@link User} object that will be updated.
     * @return boolean result of updating {@link User} object
     * true if object is updated.
     * false if object is not updated.
     */
    boolean update(User user);

    /**
     * Method that deletes an {@link User} object.
     *
     * @param id {@link User} object id that need to be deleted.
     * @return boolean result of deleting {@link User} object
     * true if object is deleted.
     * false if object is not deleted.
     */
    boolean delete(Integer id);

    /**
     * Returns a status of existing a {@link User} with requested email.
     * </p>
     * @param email the email of a {@link User} to be existing
     * @return      the status of existing a {@link User} with requested email
     */
    Boolean isEmailExist(final String email);

}
