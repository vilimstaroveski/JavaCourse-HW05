package hr.fer.zemris.java.tecaj.hw5.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zamris.java.tecaj.hw5.operators.DifferentFrom;
import hr.fer.zamris.java.tecaj.hw5.operators.Equals;
import hr.fer.zamris.java.tecaj.hw5.operators.Greater;
import hr.fer.zamris.java.tecaj.hw5.operators.GreaterEquals;
import hr.fer.zamris.java.tecaj.hw5.operators.IComparisonOperator;
import hr.fer.zamris.java.tecaj.hw5.operators.Lesser;
import hr.fer.zamris.java.tecaj.hw5.operators.LesserEquals;
import hr.fer.zemris.java.tecaj.hw5.db.ConditionalExpression;
import hr.fer.zemris.java.tecaj.hw5.fields.FirstName;
import hr.fer.zemris.java.tecaj.hw5.fields.IFieldValueGetter;
import hr.fer.zemris.java.tecaj.hw5.fields.Jmbag;
import hr.fer.zemris.java.tecaj.hw5.fields.LastName;

/**
 * Class used for parsing the String line, validating it and making ConditionalExpressions.
 * @author Vilim Staroveški
 *
 */
public class Parser {

	/**
	 * Line given in constructor that is going to be parsed.
	 */
	private String line;
	
	/**
	 * Int value that tells wheather if the query has direct indexing and how much. 
	 */
	private int numberOfDirectIndex;
	
	/**
	 * Constructor with one parameter, line that is going to be parsed.
	 * @param line {@code String} line that is going to be parsed.
	 */
	public Parser(String line) {
		
		this.line = line;
		this.numberOfDirectIndex = 0;
	}
	
	/**
	 * Method that parses String line and creates ConditionalExpressions from it.
	 * @return ConditionalExpressions from line.
	 */
	public List<ConditionalExpression> parseLine() {
		
		lowerTheCaseOfLine();
		
		String[] expressions = this.line.split("and");
		
		List<ConditionalExpression> array = new ArrayList<ConditionalExpression>();
		
		for(String expression : expressions) {
			
			array.add(makeAConditionalExpression(expression.trim()));
		}
		array.removeAll(Collections.singleton(null));
		
		return array;
	}

	/**
	 * Method that creates {@link ConditionalExpression} from string given as parameter.
	 * @param expression String from which expression will be created.
	 * @return {@link ConditionalExpression} defined by parameter.
	 */
	private ConditionalExpression makeAConditionalExpression(String expression) {

		IFieldValueGetter field = fieldFactory(expression);
		
		String newExpression = removeFieldName(expression, field);

		IComparisonOperator operator = operatorFactory(newExpression);
		
		newExpression = removeOperatorSymbol(newExpression, operator);

		boolean canHaveWildcard = false;
		if(operator instanceof Equals) {
			
			canHaveWildcard = true;
		}
		
		String literal = literalFactory(newExpression, canHaveWildcard);
		
		return new ConditionalExpression(field, literal, operator);
	}

	/**
	 * Analyses the literal String.   
	 * @param expression line in which literal String is.
	 * @param canHaveWildcard tells if the String can have wild cards in it.
	 * @return String representing literal String.
	 * @throws ParserException if the expression is not parsable.
	 */
	private String literalFactory(String expression, boolean canHaveWildcard) {

		String literal = null;
		if(!expression.startsWith("\"") || !expression.endsWith("\"")) {
			
			throw new ParserException("Quotation marks are not correctly used for string literal!");
		}
		
		literal = expression.substring(1, expression.length()-1);
		
		if(canHaveWildcard) {
			
			if(literal.indexOf('*', literal.indexOf('*')+1) != -1) {
				
				throw new ParserException("String literal can contain only one wildcard!");
			}
			else if(!literal.contains("*")) {
				
				//we know that query wants a direct indexing
				numberOfDirectIndex++;
				
			}
		}
		else {
			
			if(expression.contains("*")) {
				
				throw new ParserException("String literal can contain wildcards only when used in equals clause.");
			}
		}
		
		return literal.trim();
	}

	/**
	 * Method that removes operator symbol from given expression.
	 * @param expression String that represents expression.
	 * @param operator {@link IComparisonOperator} that is used in this expression.
	 * @return String without used operator.
	 * @throws ParserException if the operator is invalid.
	 */
	private String removeOperatorSymbol(String expression, IComparisonOperator operator) {
		
		String newExpression = null;
		
		if(operator instanceof Greater) {
			
			newExpression = expression.substring(operator.getSymbol().length());
		}
		else if(operator instanceof Lesser) {
			
			newExpression = expression.substring(operator.getSymbol().length());
		}
		else if(operator instanceof GreaterEquals) {
			
			newExpression = expression.substring(operator.getSymbol().length());
		}
		else if(operator instanceof LesserEquals) {
			
			newExpression = expression.substring(operator.getSymbol().length());
		}
		else if(operator instanceof Equals) {
			
			newExpression = expression.substring(operator.getSymbol().length());
		}
		else if(operator instanceof DifferentFrom) {
			
			newExpression = expression.substring(operator.getSymbol().length());
		}
		else {
			
			throw new ParserException("Operator is invalid!");
		}
		
		return newExpression.trim();
	}

	/**
	 * Method that removes field name from the expression.
	 * @param expression old expression containing field name.
	 * @param field {@link IFieldValueGetter} representing the field used in expression.
	 * @return expression without used field name.
	 * @throws ParserException if field is invalid.
	 */
	private String removeFieldName(String expression, IFieldValueGetter field) {
		
		String newExpression = null;
		if(field instanceof LastName) {
			
			newExpression = expression.substring(field.getName().length());
		}
		if(field instanceof FirstName) {
			
			newExpression = expression.substring(field.getName().length());
		}
		if(field instanceof Jmbag) {
			
			newExpression = expression.substring(field.getName().length());
		}
		
		return newExpression.trim();
	}

	/**
	 * Method that creates {@link IFieldValueGetter} from given expression.
	 * @param expression expression containing the field info.
	 * @return new {@link IFieldValueGetter} 
	 * @throws ParserException if expression is invalid. 
	 */
	private IFieldValueGetter fieldFactory(String expression) {

		if(expression.startsWith("lastname")) {
			
			return new LastName();
		}
		else if(expression.startsWith("firstname")) {
			
			return new FirstName();
		}
		else if(expression.startsWith("jmbag")) {
			
			return new Jmbag();
		}
		else {
			
			throw new ParserException("Given input is invalid!");
		}
	}

	/**
	 * Creates {@link IComparisonOperator} from given String operator.
	 * @param operator String representing the operator.
	 * @return new {@link IComparisonOperator} defined in parameter.
	 * @throws ParserException if the operator is invalid.
	 */
	private IComparisonOperator operatorFactory(String operator) {
		
		if(operator.startsWith(">=")) {
			
			return new GreaterEquals();
		}
		else if(operator.startsWith("<=")) {
				
			return new LesserEquals();
		}
		else if(operator.startsWith(">")) {
			
			return new Greater();
		}
		else if(operator.startsWith("<")) {
			
			return new Lesser();
		}
		else if(operator.startsWith("=")) {
			
			return new Equals();
		}
		else if(operator.startsWith("!=")) {
			
			return new DifferentFrom();
		}
		else {
			
			throw new ParserException("Operator is invalid!");
		}
	}

	/**
	 * Lowers the case of the line given by user. It doesn't lowers the case of literal Strings.
	 */
	private void lowerTheCaseOfLine() {
		
		String loweredLine = "";
		
		String[] partsOfLine = this.line.split("\"");
		int i=0;
		
		for(String part : partsOfLine) {
			
			if(i%2 == 0) {
				
				loweredLine += part.toLowerCase().trim();
			}
			else {
				
				loweredLine += "\""+part.trim()+"\"";
			}
			i++;
		}
		this.line = loweredLine;	
	}
	
	/**
	 * Returns the value that tells wheather if the query has direct indexing and how much. 
	 * @return value that tells wheather if the query has direct indexing and how much. 
	 */
	public int getNumberOfIndexing() {
		
		return numberOfDirectIndex;
	}
}
