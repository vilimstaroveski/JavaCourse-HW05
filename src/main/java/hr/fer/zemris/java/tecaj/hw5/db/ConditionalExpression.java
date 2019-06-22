package hr.fer.zemris.java.tecaj.hw5.db;

import hr.fer.zamris.java.tecaj.hw5.operators.IComparisonOperator;
import hr.fer.zemris.java.tecaj.hw5.fields.IFieldValueGetter;
/**
 * Class that represents conditional expression.
 * 
 * @author Vilim Staroveški
 *
 */
public class ConditionalExpression {

	/**
	 * Private {@link IFieldValueGetter} representing the element in expression we want to compare.
	 */
	private IFieldValueGetter field;
	
	/**
	 * Private {@link IComparisonOperator} representing the operator in expression.
	 */
	private IComparisonOperator operator;
	
	/**
	 * Private {@code String} representing the element in expression with which we compare.
	 */
	private String literal;
	
	/**
	 * Creates new {@link ConditionalExpression} from given parameters.
	 * 
	 * @param field {@link IFieldValueGetter} representing the element in expression we want to compare
	 * @param literal {@code String} representing the element in expression with which we compare
	 * @param operator {@link IComparisonOperator} representing the operator in expression
	 */
	public ConditionalExpression(IFieldValueGetter field, String literal, IComparisonOperator operator) {
		
		this.field = field;
		this.operator = operator;
		this.literal = literal;
	}

	/**
	 * Returns {@link IFieldValueGetter} representing the element in expression we want to compare
	 * @return {@link IFieldValueGetter} representing the element in expression we want to compare
	 */
	public IFieldValueGetter getField() {
		
		return field;
	}

	/**
	 * Returns {@link IComparisonOperator} representing the operator in expression
	 * @return {@link IComparisonOperator} representing the operator in expression
	 */
	public IComparisonOperator getOperator() {
		
		return operator;
	}
	
	/**
	 * Returns {@code String} representing the element in expression with which we compare
	 * @return {@code String} representing the element in expression with which we compare
	 */
	public String getLiteral() {
		
		return literal;
	}
	
	@Override
	public String toString() {
		
		return field.getName()+" "+operator.getSymbol()+" "+literal;
	}
}
