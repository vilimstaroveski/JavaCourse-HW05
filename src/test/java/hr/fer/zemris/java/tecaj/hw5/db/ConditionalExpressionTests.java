package hr.fer.zemris.java.tecaj.hw5.db;

import static org.junit.Assert.*;

import org.junit.Test;

import hr.fer.zamris.java.tecaj.hw5.operators.Equals;
import hr.fer.zemris.java.tecaj.hw5.fields.LastName;

/**
 * Tests for class {@link ConditionalExpression}.
 * @author Vilim Staroveški
 *
 */
public class ConditionalExpressionTests {


	@Test
	public void testConstructor() {
		
		ConditionalExpression expression = new ConditionalExpression(new LastName(), "Perić", new Equals());
		
		assertTrue(expression.getField() instanceof LastName);
		assertTrue(expression.getLiteral().equals("Perić"));
		assertTrue(expression.getOperator() instanceof Equals);
	}
	
	@Test
	public void testToString() {
		
		ConditionalExpression expression = new ConditionalExpression(new LastName(), "P*", new Equals());
		String expected = "lastname = P*";
		
		assertTrue(expression.toString().equals(expected));
	}
	
}
