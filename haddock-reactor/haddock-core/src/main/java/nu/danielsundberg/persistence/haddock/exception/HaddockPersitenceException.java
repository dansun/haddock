package nu.danielsundberg.persistence.haddock.exception;

import javax.persistence.PersistenceException;

/**
 * Haddockwrapper for persistence exception
 */
public class HaddockPersitenceException extends PersistenceException {

    /**
     * Unique serial version
     */
	private static final long serialVersionUID = 1L;

    /**
     * Message constructor.
     * @param message Message of exception.
     */
	public HaddockPersitenceException(String message) {
		super(message);
	}

    /**
     * Message constructor propagating another exception.
     * @param message Message of exception.
     * @param e Previous exception.
     */
	public HaddockPersitenceException(String message, Exception e) {
		super(message,e);
	}

    /**
     * Propagating constructor.
     * @param e Previous exception.
     */
	public HaddockPersitenceException(Exception e) {
		super(e);
	}
	
}
