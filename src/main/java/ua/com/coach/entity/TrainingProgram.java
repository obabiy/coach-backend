package ua.com.coach.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TrainingProgram {

    public enum TrainingType {
        WEIGTH_LOOSING,
        MUSCLE_MASS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @ManyToMany
    private Set<Exercise> exercises = new HashSet<>();

    @Column(columnDefinition = "enum('WEIGHT_LOOSING', 'MUSCLE_MASS')")
    private TrainingType type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

}
