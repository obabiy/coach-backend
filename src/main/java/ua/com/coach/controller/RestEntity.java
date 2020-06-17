package ua.com.coach.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class for giving response with details.
 */
@Data
@AllArgsConstructor
class RestEntity {

    // Value for getting massage in response from REST endpoint to client.
    private String message;
    // Object for getting result in response from REST endpoint to client.
    private Object result;

}
