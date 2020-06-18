package ua.com.coach.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.coach.dao.BodyMeasurementsDao;
import ua.com.coach.entity.BodyMeasurements;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class BodyMeasurementsDaoImpl implements BodyMeasurementsDao {

    private final SessionFactory sessionFactory;

    @Override
    public BodyMeasurements findById(Long id) {
        Validate.notNull(id);

        return sessionFactory.getCurrentSession().get(BodyMeasurements.class, id);
    }

    @Override
    public Long save(BodyMeasurements bodyMeasurements) {
        Validate.notNull(bodyMeasurements);

        return (Long) sessionFactory.getCurrentSession().save(bodyMeasurements);
    }

    @Override
    public Boolean delete(Long id) {
        if ((id != null) && (id > 0)) {
            if (sessionFactory.getCurrentSession().get(BodyMeasurements.class, id) != null) {
                final BodyMeasurements bodyMeasurements = sessionFactory.getCurrentSession().byId(BodyMeasurements.class).load(id);//+++++++++++
                sessionFactory.getCurrentSession().delete(bodyMeasurements); //++++++++++++++++++++++++++++++++++++++++
            }
        }
        return false;
    }

}
