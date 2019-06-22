package hr.fer.zamris.java.tecaj.hw5.operators;

/**
 * Implementation of {@link IComparisonOperator} with function to tell are 2 {@code String} equal. One of the strings can contain wildcard '*' that
 * represents any combination of characters.
 * 
 * @author Vilim Staroveški
 *
 */
public class Equals implements IComparisonOperator{

	/**
	 * String value representing the unique symbol of this operator.
	 */
	private final String symbol;
	
	/**
	 * Constructor that sets private symbol to value "=".
	 */
	public Equals() {

		this.symbol = "=";
	}
	
	@Override
	public boolean satisfied(String fieldFromRecord, String stringLiteral) {
		
		if(stringLiteral.contains("*")) {
			
			boolean prefixSatisfied = false, sufixSatisfied = false;
			String prefix = stringLiteral.substring(0, stringLiteral.indexOf("*")).trim();
			String sufix = stringLiteral.substring(stringLiteral.indexOf("*")+1).trim();
			
			if(prefix != null) {

				prefixSatisfied = fieldFromRecord.startsWith(prefix);
			}
			else {

				prefixSatisfied = true;
			}
			
			if(sufix != null) {

				sufixSatisfied = fieldFromRecord.endsWith(sufix);
			}
			else {

				sufixSatisfied = true;
			}
			
			return sufixSatisfied && prefixSatisfied;
		}
		
		return fieldFromRecord.equals(stringLiteral);
	}
	
	/**
	 * Returns private unique symbol.
	 * @return {@code String} value symbol.
	 */
	public String getSymbol() {
		
		return symbol;
	}
}
