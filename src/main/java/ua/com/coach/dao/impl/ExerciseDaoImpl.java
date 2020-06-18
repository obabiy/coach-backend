package ua.com.coach.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.coach.dao.ExerciseDao;
import ua.com.coach.entity.Exercise;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class ExerciseDaoImpl implements ExerciseDao {

    private final SessionFactory sessionFactory;

    private static final String HQL_GET_ALL_EXERCISES = "FROM Exercise exercise"; //hibernate query language query

    @Override
    public Long save(Exercise exercise) {
        Validate.notNull(exercise);
        return (Long) sessionFactory.getCurrentSession().save(exercise);
    }

    @Override
    public Boolean update(Exercise exercise) {
        Validate.notNull(exercise);
        sessionFactory.getCurrentSession().update(exercise);

        return true;
    }

    @Override
    public Boolean delete(Long id) {
        if((id != null) && (id > 0)){
            if(sessionFactory.getCurrentSession().get(Exercise.class, id) != null){
                final Exercise exercise = sessionFactory.getCurrentSession().byId(Exercise.class).load(id);//+++++++++++
                sessionFactory.getCurrentSession().delete(exercise); //++++++++++++++++++++++++++++++++++++++++
            }
        }

        return false;
    }

    @Override
    public Exercise findById(Long id) {
        Validate.notNull(id);

        return sessionFactory.getCurrentSession().get(Exercise.class, id);
    }

    @Override
    public List<Exercise> findAll() {
        return sessionFactory.getCurrentSession().createQuery(HQL_GET_ALL_EXERCISES).list();
    }
}
