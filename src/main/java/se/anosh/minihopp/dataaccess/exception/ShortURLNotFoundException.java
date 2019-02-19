package se.anosh.minihopp.dataaccess.exception;

/**
 *
 * @author Anosh D. Ullenius <anosh@anosh.se>
 */
public class ShortURLNotFoundException extends Exception {
    
    
    public ShortURLNotFoundException() {
    }
    
    public ShortURLNotFoundException(final String message) {
        super(message);
    }
    
    public ShortURLNotFoundException(final String message, Throwable cause) {
        super(message,cause);
    }
    
    
}
