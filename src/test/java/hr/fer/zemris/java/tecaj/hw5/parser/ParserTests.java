package hr.fer.zemris.java.tecaj.hw5.parser;

import static org.junit.Assert.*;

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

import org.junit.Test;

/**
 * Tests for {@link Parser} class. {@link Parser} is tested with valid and invalid inputs.
 * @author Vilim Staroveški
 *
 */
public class ParserTests {

	@Test
	public void testConstructor() {
		
		IFieldValueGetter lastname = new LastName();
		IComparisonOperator equal = new Equals();
		IFieldValueGetter firstname = new FirstName();
		IComparisonOperator gE = new GreaterEquals();
		IFieldValueGetter jmbag = new Jmbag();
		IComparisonOperator lE = new LesserEquals();
		IComparisonOperator diff = new DifferentFrom();
		IComparisonOperator less = new Lesser();
		@SuppressWarnings("unused")
		IComparisonOperator great = new Greater();
		
		Parser parser = new Parser("firstname   <\"I\"and firstname >=      \"C\"   ");
		List<ConditionalExpression> expressions = parser.parseLine();
		
		assertTrue(expressions.get(0).getField().getName().equals(firstname.getName()));
		assertTrue(expressions.get(0).getOperator().getSymbol().equals(less.getSymbol()));
		assertTrue(expressions.get(0).getLiteral().equals("I"));
		assertTrue(expressions.get(1).getField().getName().equals(firstname.getName()));
		assertTrue(expressions.get(1).getOperator().getSymbol().equals(gE.getSymbol()));
		assertTrue(expressions.get(1).getLiteral().equals("C"));
		
		Parser parser2 = new Parser("lastname!= \"Ivić\"and   firstname <=      \"Ceo\"   and jmbag=\"0000000002\"");
		List<ConditionalExpression> expressions2 = parser2.parseLine();
		
		assertTrue(expressions2.get(0).getField().getName().equals(lastname.getName()));
		assertTrue(expressions2.get(0).getOperator().getSymbol().equals(diff.getSymbol()));
		assertTrue(expressions2.get(0).getLiteral().equals("Ivić"));
		assertTrue(expressions2.get(1).getField().getName().equals(firstname.getName()));
		assertTrue(expressions2.get(1).getOperator().getSymbol().equals(lE.getSymbol()));
		assertTrue(expressions2.get(1).getLiteral().equals("Ceo"));
		assertTrue(expressions2.get(2).getField().getName().equals(jmbag.getName()));
		assertTrue(expressions2.get(2).getOperator().getSymbol().equals(equal.getSymbol()));
		assertTrue(expressions2.get(2).getLiteral().equals("0000000002"));
	}
	
	@Test(expected=ParserException.class)
	public void testConstructorWithWrongFieldName() {
		
		Parser parser = new Parser("finame   <\"I\"and firstname >=      \"C\"   ");
		@SuppressWarnings("unused")
		List<ConditionalExpression> expressions = parser.parseLine();
	
	}
	
	@Test(expected=ParserException.class)
	public void testConstructorWithWrongOperator() {
		
		Parser parser = new Parser("firstname !  \"I\"");
		@SuppressWarnings("unused")
		List<ConditionalExpression> expressions = parser.parseLine();
	
	}
	
	@Test(expected=ParserException.class)
	public void testConstructorWithWrongLiteral() {
		
		Parser parser = new Parser("firstname   <\"I  *\"and firstname >=      \"C\"   ");
		@SuppressWarnings("unused")
		List<ConditionalExpression> expressions = parser.parseLine();
	
	}
	
	@Test(expected=ParserException.class)
	public void testConstructorWithRandomString() {
		
		Parser parser = new Parser("first \"C\"   ");
		@SuppressWarnings("unused")
		List<ConditionalExpression> expressions = parser.parseLine();
	
	}
	
	@Test(expected=ParserException.class)
	public void testConstructorWithWrongAndUse() {
		
		Parser parser = new Parser("and firstname   <\"I\"and firstname >=      \"C\"   ");
		@SuppressWarnings("unused")
		List<ConditionalExpression> expressions = parser.parseLine();
	
	}
	
	@Test(expected=ParserException.class)
	public void testConstructorWithTwoWildCards() {
		
		Parser parser = new Parser("firstname = \"*a*\" ");
		@SuppressWarnings("unused")
		List<ConditionalExpression> expressions = parser.parseLine();
	
	}
}
