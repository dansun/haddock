package nu.danielsundberg.persistence.haddock.exception;

import javax.persistence.PersistenceException;

public class HaddockPersitenceException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public HaddockPersitenceException(String message) {
		super(message);
	}
	
	public HaddockPersitenceException(String message, Exception e) {
		super(message,e);
	}
	
	public HaddockPersitenceException(Exception e) {
		super(e);
	}
	
}
