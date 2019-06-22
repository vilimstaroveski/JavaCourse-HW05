package hr.fer.zemris.java.tecaj.hw5.parser;

/**
 * When error is encountered during parsing, this exception should be thrown.
 * @author Vilim Staroveški
 *
 */
public class ParserException extends RuntimeException {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	public ParserException() {
		super();
	}
	
	public ParserException(String message) {
		super(message);
	}

}
