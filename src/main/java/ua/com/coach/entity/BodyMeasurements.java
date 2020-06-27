package ua.com.coach.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class BodyMeasurements {

    public enum Gender{
        Man,
        Woman
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Basic param
    @NotNull
    private int weight;


    // Advanced params

    @NotNull
    private Gender gender;

    @NotNull
    private int height;

    private int waist;  //обхват талії

    private int neckСircumference; // обхват шиї

}
