package ua.com.coach.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exercise {

    @ManyToMany
    private Set<TrainingProgram> trainingPrograms = new HashSet<>();

    @Id
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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Set<TrainingProgram> getTrainingPrograms() {
        return trainingPrograms;
    }

    public void setTrainingPrograms(Set<TrainingProgram> trainingPrograms) {
        this.trainingPrograms = trainingPrograms;
    }
}
