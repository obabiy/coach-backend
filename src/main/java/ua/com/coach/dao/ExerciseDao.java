package ua.com.coach.dao;

import org.springframework.stereotype.Component;
import ua.com.coach.entity.Exercise;

import java.util.List;

@Component
public interface ExerciseDao {

    Long save(final Exercise exercise);

    Boolean update(final Exercise exercise);

    Boolean delete(final Long id);

    Exercise findById(final Long id);

    List<Exercise> findAll();

}
