package dk.stork.requestHandling.communicationObjects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Johannes Ernstsen
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotLoggedInException extends RuntimeException{
    public NotLoggedInException(String message) {
        super(message);
    }
}
