package ua.com.coach.service;

import org.springframework.stereotype.Service;
import ua.com.coach.entity.Exercise;

import java.util.List;

@Service
public interface ExerciseService {

    Long create(Exercise exercise);

    Boolean delete(Long id);

    Boolean update(Exercise exercise);

    List<Exercise> findById();

}
