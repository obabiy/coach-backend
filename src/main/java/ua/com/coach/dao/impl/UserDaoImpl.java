package ua.com.coach.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.coach.entity.User;
import ua.com.coach.dao.UserDao;

import java.util.List;

/**
 * Class that is implementing CRUD operation {@link UserDao} with User entity in database.
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Override
    public Long save(final User user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(final Integer id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(final String email) {
        Validate.notNull(email);

        return (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User user WHERE user.email = :parameter").setParameter("parameter", email)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User user").list();
    }

    @Override
    public boolean update(final User user) {
        sessionFactory.getCurrentSession().update(user);

        return true;
    }

    @Override
    public boolean delete(final Integer id) {
        sessionFactory.getCurrentSession().delete(findById(id));

        return true;
    }

    @Override
    public Boolean isEmailExist(final String email) {
        try {
            final List<String> list = sessionFactory.getCurrentSession()
                    .createQuery(String.format("SELECT email FROM User WHERE email = '%s'", email)).list();

            return list.contains(email);
        } catch (final HibernateException e) {
            log.error(e.getMessage());
        }

        return false;
    }

}
