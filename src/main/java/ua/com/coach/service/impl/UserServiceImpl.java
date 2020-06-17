package ua.com.coach.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.coach.dao.UserDao;
import ua.com.coach.entity.User;
import ua.com.coach.service.UserService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class that is implementing an UserService interface for working with User DAO.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Long create(final User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User findById(final Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        Validate.notNull(email);

        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean update(final User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(final Integer id) {
        return userDao.delete(id);
    }

    @Override
    public Boolean isEmailExist(final String email) {
        return userDao.isEmailExist(email);
    }

}
