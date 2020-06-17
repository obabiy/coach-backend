package ua.com.coach.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.coach.entity.User;

/**
 * Class that is data transfer object (DTO) for {@link User} plain old java object (POJO).
 */
@Data
@Builder
public class UserDto {

    private Integer id;

    private String username;

    private String email;

}
