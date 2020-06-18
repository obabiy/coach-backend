package ua.com.coach.dao;

import org.springframework.stereotype.Component;
import ua.com.coach.entity.BodyMeasurements;

@Component
public interface BodyMeasurementsDao {

    BodyMeasurements findById(Long id);

    Long save(final BodyMeasurements bodyMeasurements);

    Boolean delete(final Long id);

}
