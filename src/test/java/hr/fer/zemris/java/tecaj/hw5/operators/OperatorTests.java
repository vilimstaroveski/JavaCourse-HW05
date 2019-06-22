package hr.fer.zemris.java.tecaj.hw5.operators;

import static org.junit.Assert.*;
import hr.fer.zamris.java.tecaj.hw5.operators.DifferentFrom;
import hr.fer.zamris.java.tecaj.hw5.operators.Equals;
import hr.fer.zamris.java.tecaj.hw5.operators.Greater;
import hr.fer.zamris.java.tecaj.hw5.operators.GreaterEquals;
import hr.fer.zamris.java.tecaj.hw5.operators.IComparisonOperator;
import hr.fer.zamris.java.tecaj.hw5.operators.Lesser;
import hr.fer.zamris.java.tecaj.hw5.operators.LesserEquals;
import hr.fer.zemris.java.tecaj.hw5.db.StudentDB;

import org.junit.Test;

/**
 * Tests for classes that implements {@link IComparisonOperator} interface.
 * @author Vilim Staroveški
 *
 */
public class OperatorTests {

	/**
	 * Test for {@link DifferentFrom} class.
	 */
	@Test
	public void testDifferentFrom() {
		
		DifferentFrom oper = new DifferentFrom();
		
		assertTrue(oper.getSymbol().equals("!="));
		
		assertTrue(oper.satisfied("Marin", "Ivan"));
		
		assertFalse(oper.satisfied("Marin", "Marin"));
	}
	
	/**
	 * Test for {@link Equals} class.
	 */
	@Test
	public void testEquals() {
		
		Equals oper = new Equals();
		
		assertTrue(oper.getSymbol().equals("="));
		
		assertTrue(oper.satisfied("Marin", "Marin"));
		
		assertFalse(oper.satisfied("Marin", "Ivan"));
	}
	
	/**
	 * Test for {@link Lesser} class.
	 */
	@Test
	public void testLesser() {
		
		Lesser oper = new Lesser();
		
		assertTrue(oper.getSymbol().equals("<"));
		
		assertTrue(oper.satisfied("Mario", "Mavro"));
		
		assertFalse(oper.satisfied("Anja", "Ana"));
		
		assertFalse(oper.satisfied("Ivan", "Ivan"));
	}
	
	/**
	 * Test for {@link Greater} class.
	 */
	@Test
	public void testGreater() {
		
		Greater oper = new Greater();
		
		assertTrue(oper.getSymbol().equals(">"));
		
		assertFalse(oper.satisfied("Mario", "Mavro"));
		
		assertTrue(oper.satisfied("Anja", "Ana"));
		
		assertFalse(oper.satisfied("Ivan", "Ivan"));
		
		assertFalse(oper.satisfied("Čačak", "Zebra"));
	}
	
	/**
	 * Test for {@link LesserEquals} class.
	 */
	@Test
	public void testLesserEquals() {
		
		LesserEquals oper = new LesserEquals();
		
		assertTrue(oper.getSymbol().equals("<="));
		
		assertTrue(oper.satisfied("Mario", "Mavro"));
		
		assertFalse(oper.satisfied("Anja", "Ana"));
		
		assertTrue(oper.satisfied("Mario", "Mario"));
		
	}
	
	/**
	 * Test for {@link GreaterEquals} class.
	 */
	@Test
	public void testGreaterEquals() {
		
		GreaterEquals oper = new GreaterEquals();
		
		assertTrue(oper.getSymbol().equals(">="));
		
		assertFalse(oper.satisfied("Mario", "Mavro"));
		
		assertTrue(oper.satisfied("Anja", "Ana"));
		
		assertTrue(oper.satisfied("Mario", "Mario"));
		
		assertFalse(oper.satisfied("Ana", "Mario"));
		
	}
}
