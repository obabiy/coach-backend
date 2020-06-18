package ua.com.coach.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class AdvancedProgress extends BasicProgress{

    @NotNull
    private int height;

    @NotNull
    private float chestVolume;

    @NotNull
    private float waist;

    @NotNull
    private float neckCircumference;
}
