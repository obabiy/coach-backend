package ua.com.coach.service.impl;

import lombok.Builder;
import org.springframework.stereotype.Service;
import ua.com.coach.dao.ExerciseDao;
import ua.com.coach.dao.TrainingProgramDao;
import ua.com.coach.entity.Exercise;
import ua.com.coach.entity.TrainingProgram;
import ua.com.coach.service.TrainingProgramService;
//
//@Builder
//@Service
//public class TrainingProgramServiceImpl implements TrainingProgramService {
//
//    @Override
//    public Long create(final TrainingProgram trainingProgram) {
//
//        TrainingProgram buildTrainingProgram = TrainingProgram.builder().title(trainingProgram.getTitle()).exercises(g).build();
//        return TrainingProgramDao.save(buildTrainingProgram);
//    }
//
//}
