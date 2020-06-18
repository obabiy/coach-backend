package ua.com.coach.dao;

import org.springframework.stereotype.Component;
import ua.com.coach.entity.TrainingProgram;

import java.util.List;

@Component
public interface TrainingProgramDao {

    Long save(final TrainingProgram trainingProgram);

    Boolean delete(final Long id);

    Boolean update(final TrainingProgram trainingProgram);

    TrainingProgram findById(final Long id);

    List<TrainingProgram> findAll();

}
