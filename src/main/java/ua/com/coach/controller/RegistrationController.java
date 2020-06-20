package ua.com.coach.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.com.coach.entity.User;
import ua.com.coach.service.UserService;

import javax.validation.Valid;

/**
 * The controller for sign up a {@link User}.
 */


@Slf4j
@RequiredArgsConstructor
//@CrossOrigin(origins = {"${ui.url}"})
@Controller
public class RegistrationController {

       private final UserService userService;

//    private final PasswordEncoder passwordEncoder; // +++++++++++++++++

    /**
     * The message of successful sign up process.
     */
    private static final String MESSAGE_SUCCESS = "You have successfully created your account.";

    /**
     * The message of existing an email.
     */
    private static final String MESSAGE_EMAIL_EXIST = "Your email address is already exist. Choose a different email address.";

    /**
     * The message of Internal Server Error.
     */
    private static final String MESSAGE_RUNTIME_EXCEPTION = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

    /**
     * Returns a status of authentication.
     * </p>
     * @return the status of authentication
     */
    @GetMapping("/authentication")
    public ResponseEntity<RestEntity> authentication() {
        try {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            return new ResponseEntity<>(
                    new RestEntity(authentication.getName(), authentication.getAuthorities().toString()),
                    HttpStatus.OK
            );
        } catch (final RuntimeException e) {
            log.error(e.getMessage());

            return new ResponseEntity<>(
                    new RestEntity(MESSAGE_RUNTIME_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<RestEntity> registration(@RequestBody @Valid final User user) {
        try {
            if (userService.isEmailExist(user.getEmail().toLowerCase())) {
                return new ResponseEntity<>(
                        new RestEntity(MESSAGE_EMAIL_EXIST, HttpStatus.BAD_REQUEST.getReasonPhrase()),
                        HttpStatus.BAD_REQUEST);
            }

            userService.create(user);

            return new ResponseEntity<>(
                    new RestEntity(MESSAGE_SUCCESS, HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
        } catch (final RuntimeException e) {
            log.error(e.getMessage());

            return new ResponseEntity<>(
                    new RestEntity(MESSAGE_RUNTIME_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    private User getValidUserToBeRegistered(final UserRegistrationDto user) {
//        return User.builder()
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .email(user.getEmail().toLowerCase())
//                .password(passwordEncoder.encode(user.getPassword()))
//                .role(User.Role.CUSTOMER) // The role of a user is customer by default.
//                .company(companyService.findById(1)) // The company where user is an employee is VITech by default.
//                .status(User.Status.ACTIVE)
//                .active(true)
//                .balance(BigDecimal.ZERO)
//                .build();
//    }

}
