package dk.stork.exceptions;

/**
 * @author Johannes Ernstsen
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
