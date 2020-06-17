package ua.com.coach.dto;

import lombok.Builder;
import lombok.Getter;
import ua.com.coach.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The DTO (Data Transfer Object) for creating a {@link User}.
 */
@Builder
@Getter
public class UserRegistrationDto {

    /**
     * The username of a user.
     */

    @NotNull
    @NotBlank
    @Email(message = "Enter a valid email address.")
    private String username;

    /**
     * The email of a user.
     */
    @NotNull
    @NotBlank
    @Email(message = "Enter a valid email address.")
    private String email;

    /**
     * The password of a user.
     */
    @NotNull
    @NotBlank
    @Size(min = 8, message = "Password must have 8 or more characters.")
    private String password;

}
