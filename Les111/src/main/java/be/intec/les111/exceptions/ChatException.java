package be.intec.les111.exceptions;

import be.intec.les111.models.entities.*;
import java.util.Arrays;

public class ChatException extends RuntimeException {

    public ChatException(String message) {
        super(message);
    }

    public ChatException notFound() {
        return new ChatException("Chat not found");
    }

    public ChatException alreadyExists() {
        return new ChatException("Chat already exists");
    }

    public ChatException requiredFields(String... fields) {
        return new ChatException("Required fields: " + Arrays.toString(fields));
    }

    public ChatException nullChatException() {
        return new ChatException("Chat cannot be null");
    }

}
