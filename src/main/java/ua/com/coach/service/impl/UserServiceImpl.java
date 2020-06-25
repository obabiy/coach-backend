package ua.com.coach.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.coach.dao.UserDao;
import ua.com.coach.dto.UserDto;
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

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long create(final User user) {
//  UserDto build = UserDto.builder().email().username().build();
        User regUser = user;
        regUser.setPassword(passwordEncoder.encode(user.getPassword()));
        regUser.setActive(true);
        regUser.setRole(User.Role.USER);

//        User buildUser = User.builder().password(passwordEncoder.encode(user.getPassword())).active(true).role(User.Role.USER).build();
//        return UserDao.save(buildUser);

        return userDao.save(regUser);
    }

    @Override
    public User findById(final Long id) {
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
    public boolean delete(final Long id) {
        return userDao.delete(id);
    }

    @Override
    public Boolean isEmailExist(final String email) {
        return userDao.isEmailExist(email);
    }

}
