//package ua.com.vitech.jdp.foodmood.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import UserDto;
//import User;
//import UserService;
//import ua.com.vitech.jdp.foodmood.service.impl.OrderServiceImpl;
//import UserUtils;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * REST Controller for {@link User} objects that takes requests from client side.
// */
//@Slf4j
//@RequiredArgsConstructor
//@CrossOrigin(origins = {"${ui.url}"})
//@RequestMapping("/user")
//@RestController
//public class UserController {
//
//    private final UserService userService;
//
//    private final OrderServiceImpl orderService;
//
//    /**
//     * Endpoint for creating new {@link User} object in database based on input parameters.
//     *
//     * @param user {@link User} object.
//     * @return {@link ResponseEntity} with {@link RestEntity} inside.
//     */
//    @PostMapping
//    public ResponseEntity<RestEntity> save(@RequestBody final User user) {
//        try {
//            if (UserUtils.isValid(user)) {
//                userService.create(user);
//
//                return new ResponseEntity<>(new RestEntity("User has been saved", true), HttpStatus.OK);
//            }
//
//            return new ResponseEntity<>(new RestEntity("Incorrect input data", false), HttpStatus.BAD_REQUEST);
//        } catch (RuntimeException e) {
//            log.error("Error during User save operation: " + e.getMessage());
//
//            return new ResponseEntity<>(new RestEntity(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /**
//     * Endpoint for getting {@link User} by id.
//     *
//     * @param id of {@link User}.
//     * @return {@link ResponseEntity} with {@link RestEntity} inside.
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<RestEntity> findById(@PathVariable final Integer id) {
//        try {
//            UserDto userDto = UserUtils.convertToDto(userService.findById(id));
//
//            return new ResponseEntity<>(new RestEntity("User with asked id", userDto), HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//
//            return new ResponseEntity<>(new RestEntity("User has not been found in database", e.getMessage()),
//                    HttpStatus.NOT_FOUND);
//        } catch (RuntimeException e) {
//            log.error("Error during User find operation: " + e.getMessage());
//
//            return new ResponseEntity<>(new RestEntity(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /**
//     * Endpoint for getting all {@link User}.
//     *
//     * @return {@link ResponseEntity} with {@link RestEntity} inside.
//     */
//    @GetMapping
//    public ResponseEntity<RestEntity> findAll() {
//        try {
//            final List<User> userList = userService.findAll();
//            final List<UserDto> userDtoList = new ArrayList<>();
//            if (userList != null && !userList.isEmpty()) {
//                for (User user : userList) {
//                    userDtoList.add(UserUtils.convertToDto(user));
//                }
//
//                return new ResponseEntity<>(new RestEntity("All users", userDtoList), HttpStatus.OK);
//            }
//
//            return new ResponseEntity<>(new RestEntity("There are not any user yet", false), HttpStatus.NOT_FOUND);
//        } catch (RuntimeException e) {
//            log.error("Error during User findAll operation: " + e.getMessage());
//
//            return new ResponseEntity<>(new RestEntity(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /**
//     * Returns a {@link ResponseEntity} that includes:
//     * - the message of a request;
//     * - the result of a request (a balance of a {@link User});
//     * - the status code;
//     *
//     * @return the {@link ResponseEntity} with a balance of a {@link User}
//     */
//    @GetMapping(path = "/balance")
//    public ResponseEntity<RestEntity> getUserBalance() {
//        try {
//            final String authUserEmail = orderService.getAuthUserName();
//            final BigDecimal balance = userService.getUserBalanceByUserEmail(authUserEmail);
//
//            return new ResponseEntity<>(
//                    new RestEntity(HttpStatus.OK.getReasonPhrase(), balance), HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            log.error(e.getMessage());
//
//            return new ResponseEntity<>(
//                    new RestEntity(HttpStatus.UNAUTHORIZED.getReasonPhrase(), StringUtils.EMPTY),
//                    HttpStatus.UNAUTHORIZED);
//        } catch (RuntimeException e) {
//            log.error(e.getMessage());
//
//            return new ResponseEntity<>(
//                    new RestEntity(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), StringUtils.EMPTY),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /**
//     * Endpoint for updating existing {@link User} object in database based on input parameters.
//     *
//     * @param user {@link User} object.
//     * @return {@link ResponseEntity} with {@link RestEntity} inside.
//     */
//    @PutMapping
//    public ResponseEntity<RestEntity> update(@RequestBody final User user) {
//        User newUser;
//        try {
//            final User oldUser = userService.findById(user.getId());
//            if (oldUser != null && !oldUser.equals(user)) {
//                newUser = UserUtils.copyUserData(oldUser, user);
//                if (UserUtils.isValid(newUser) && !oldUser.equals(newUser) && userService.update(newUser)) {
//                    return new ResponseEntity<>(new RestEntity("User has been updated", true), HttpStatus.OK);
//                }
//            }
//
//            return new ResponseEntity<>(new RestEntity("Incorrect input data", false), HttpStatus.BAD_REQUEST);
//        } catch (RuntimeException e) {
//            log.error("Error during User update operation: " + e.getMessage());
//
//            return new ResponseEntity<>(new RestEntity(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    /**
//     * Endpoint for deleting {@link User}.
//     *
//     * @param id of {@link User} that will be deleted.
//     * @return {@link ResponseEntity} with {@link RestEntity} inside.
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RestEntity> delete(@PathVariable final Integer id) {
//        try {
//            User user = userService.findById(id);
//            if (user != null && userService.delete(user.getId())) {
//                return new ResponseEntity<>(new RestEntity("User has been deleted successfully", true), HttpStatus.OK);
//            }
//
//            return new ResponseEntity<>(new RestEntity("Incorrect id", false), HttpStatus.BAD_REQUEST);
//        } catch (RuntimeException e) {
//            log.error("Error during User delete operation: " + e.getMessage());
//
//            return new ResponseEntity<>(new RestEntity(e.getMessage(), false), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
