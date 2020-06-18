package ua.com.coach.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class BasicProgress {

    @Id
    private Long Id;

    @NotNull
    private float bodyMass;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public float getBodyMass() {
        return bodyMass;
    }

    public void setBodyMass(float bodyMass) {
        this.bodyMass = bodyMass;
    }
}
