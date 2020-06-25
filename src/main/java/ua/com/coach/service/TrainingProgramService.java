package ua.com.coach.service;

import org.springframework.stereotype.Service;
import ua.com.coach.entity.TrainingProgram;

import java.util.List;

@Service
public interface TrainingProgramService {

    Long create(TrainingProgram trainingProgram);

    Boolean delete(Long id);

    Boolean update(TrainingProgram trainingProgram);

    List<TrainingProgram> findByType();


}
