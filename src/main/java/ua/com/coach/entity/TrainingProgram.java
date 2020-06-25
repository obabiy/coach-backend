package ua.com.coach.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
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

    @NotNull
    @NotBlank
    private String title;


}
