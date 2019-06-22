package hr.fer.zamris.java.tecaj.hw5.operators;

/**
 * Interface for operators used in comparison expressions. Comparison expression operators are: >, <, >=, <=, =, !=.
 * 
 * @author Vilim Staroveški
 *
 */
public interface IComparisonOperator {

	/**
	 * Returns true if string value1 satisfies the relation in which it is with value2.
	 * 
	 * @param value1 first string in comparison.
	 * @param value2 second string in comparison.
	 * @return true if string value1 satisfies the relation in which it is with value2. False otherwise.
	 */
	public boolean satisfied(String value1, String value2);
	
	/**
	 * Returns unique symbol representation as String value.
	 * @return unique symbol representation as String value
	 */
	public String getSymbol();
}
