package ua.com.coach.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    public enum Role {
        ADMIN,
        USER
    }

    private Boolean active;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    @NotNull
    @NotBlank
    @Email(message = "Enter a valid email address.")
    private String email;

    @Size(min=4, message = "Your username must have 4 or more characters")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 8, message = "Password must have 8 or more characters.")
    private String password;


    @NotNull
    @Column(columnDefinition = "enum('ADMIN', 'USER')")
    @Enumerated(EnumType.STRING)
    private Role role;

//    public enum Status {
//
//        /**
//         * The status of active user.
//         */
//        ACTIVE,
//
//        /**
//         * The status of blocked user.
//         */
//        BLOCKED,
//
//        /**
//         * The status of removed user.
//         */
//        REMOVED
//
//    }

//
//    @NotNull
//    @NotBlank
//    @Size(min = 2, message = "First name must have 2 or more characters.")
//    @Size(max = 100, message = "First name must have 100 or less characters.")
//    @Name(message = "First name must have only letters.")
//    private String firstName;
//
//    @NotNull
//    @NotBlank
//    @Size(min = 2, message = "Last name must have 2 or more characters.")
//    @Size(max = 100, message = "Last name must have 100 or less characters.")
//    @Name(message = "Last name must have only letters.")
//    private String lastName;


//    @Column(columnDefinition = "enum('ACTIVE', 'BLOCKED', 'REMOVED')")
//    @Enumerated(EnumType.STRING)
//    private Status status;


//    private Boolean active;

//    @NotNull
//    private BigDecimal balance;

}
