package be.intec.les111.exceptions;

import be.intec.les111.models.entities.*;
import java.util.Arrays;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public UserException notFound() {
        return new UserException("User not found");
    }

    public UserException alreadyExists() {
        return new UserException("User already exists");
    }

    public UserException requiredFields(String... fields) {
        return new UserException("Required fields: " + Arrays.toString(fields));
    }

    public UserException nullUserException() {
        return new UserException("User cannot be null");
    }

}
