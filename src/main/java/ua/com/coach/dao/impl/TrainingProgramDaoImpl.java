package ua.com.coach.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.coach.dao.TrainingProgramDao;
import ua.com.coach.entity.TrainingProgram;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class TrainingProgramDaoImpl implements TrainingProgramDao {

    private final SessionFactory sessionFactory;

    private static final String HQL_GET_ALL_TRAINING_PROGRAMS = "FROM TrainingProgram trainingProgram"; //hibernate query language query

    @Override
    public Long save(final TrainingProgram trainingProgram){
        Validate.notNull(trainingProgram);
        return (Long) sessionFactory.getCurrentSession().save(trainingProgram);
    }

    @Override
    public Boolean delete(Long id) {
        if((id != null) && (id > 0)){
            if(sessionFactory.getCurrentSession().get(TrainingProgram.class, id) != null){
                final TrainingProgram trainingProgram = sessionFactory.getCurrentSession().byId(TrainingProgram.class).load(id);//+++++++++++
                sessionFactory.getCurrentSession().delete(trainingProgram); //++++++++++++++++++++++++++++++++++++++++
            }
        }

        return false;
    }

    @Override
    public Boolean update(TrainingProgram trainingProgram) {
        Validate.notNull(trainingProgram);

        sessionFactory.getCurrentSession().update(trainingProgram);
        return true;
    }

    @Override
    public TrainingProgram findById(Long id) {
        Validate.notNull(id);

        return sessionFactory.getCurrentSession().get(TrainingProgram.class, id);
    }

    @Override
    public List<TrainingProgram> findAll() {
        return sessionFactory.getCurrentSession().createQuery(HQL_GET_ALL_TRAINING_PROGRAMS).list();
    }
}
