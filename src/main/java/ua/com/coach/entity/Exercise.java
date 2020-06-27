package ua.com.coach.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Exercise {

    @ManyToMany(mappedBy = "exercises")
    private Set<TrainingProgram> trainingPrograms = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotBlank
    @NotNull
    private String description;

    @Lob  //largeObject
    @Column(columnDefinition = "BLOB") //будемо зберігати байтовий масив binaryLargeObject
    private byte[] photo;

}
