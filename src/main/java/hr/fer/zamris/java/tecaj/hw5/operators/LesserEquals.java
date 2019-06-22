package hr.fer.zamris.java.tecaj.hw5.operators;

import java.text.Collator;
import java.util.Locale;

/**
 * Implementation of {@link IComparisonOperator} with function to tell in what relation are 2 {@code String}. This class uses lesser-equal relation.
 * @author Vilim Staroveški
 *
 */
public class LesserEquals implements IComparisonOperator{

	/**
	 * String value representing the unique symbol of this operator.
	 */
	private final String symbol;
	
	/**
	 * Constructor that sets private symbol to value "<=".
	 */
	public LesserEquals() {

		this.symbol = "<=";
	}
	
	@Override
	public boolean satisfied(String fieldFromRecord, String stringLiteral) {

		Collator col = Collator.getInstance(new Locale("hr"));
		
		if(col.compare(fieldFromRecord, stringLiteral) <= 0) {
			
			return true;
		}
		else {
			
			return false;
		}
	}

	/**
	 * Returns private unique symbol.
	 * @return {@code String} value symbol.
	 */
	public String getSymbol() {
		
		return symbol;
	}
}
