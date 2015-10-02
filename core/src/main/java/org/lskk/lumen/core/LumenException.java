package org.lskk.lumen.core;

/**
 * @author rully
 *
 */
@SuppressWarnings("serial")
public class LumenException extends RuntimeException {

	/**
	 * 
	 */
	public LumenException() {
	}

	/**
	 * @param message
	 */
	public LumenException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public LumenException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LumenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LumenException(Throwable cause, String message, Object... args) {
		super(String.format(message, args), cause);
	}

}
