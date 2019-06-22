package hr.fer.zamris.java.tecaj.hw5.operators;

/**
 * Implementation of {@link IComparisonOperator} with function to tell are 2 {@code String} different.
 * @author Vilim Staroveški
 *
 */
public class DifferentFrom implements IComparisonOperator {

	/**
	 * String value representing the unique symbol of this operator.
	 */
	private final String symbol;
	
	/**
	 * Constructor that sets private symbol to value "!=".
	 */
	public DifferentFrom() {

		this.symbol = "!=";
	}
	
	@Override
	public boolean satisfied(String fieldFromRecord, String stringLiteral) {
		
		return !fieldFromRecord.equals(stringLiteral);
	}

	/**
	 * Returns private unique symbol.
	 * @return {@code String} value symbol.
	 */
	public String getSymbol() {
		
		return symbol;
	}
}
