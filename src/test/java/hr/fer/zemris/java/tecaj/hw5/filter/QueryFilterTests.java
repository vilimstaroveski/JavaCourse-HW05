package hr.fer.zemris.java.tecaj.hw5.filter;

import static org.junit.Assert.*;
import hr.fer.zemris.java.tecaj.hw5.db.StudentDB;
import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

import org.junit.Test;

/**
 * Tests for class {@link QueryFilter}.
 * @author Vilim Staroveški
 *
 */
public class QueryFilterTests {

	
	@Test
	public void testQueryFilter() {
		
		String userInput = "jmbag  =\"0000000001\"";
		
		QueryFilter filter = new QueryFilter(userInput);
		
		assertTrue(filter.getJMBAG().get().equals("0000000001"));
	}
	
	@Test
	public void testQueryFilter2() {
		
		String userInput = "jmbag  >\"0000000001\"";
		
		QueryFilter filter = new QueryFilter(userInput);
		
		assertFalse(filter.getJMBAG().isPresent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testQueryFilterException() {
		
		String userInput = "nije valjan unos";
		
		@SuppressWarnings("unused")
		QueryFilter filter = new QueryFilter(userInput);
		
	}
	
	@Test
	public void testQueryFilterAcepted() {
		
		String userInput = "jmbag  = \"0000000001\"";
		
		StudentRecord marin = new StudentRecord("0000000001", "Akšamović", "Marin", "2");
		
		QueryFilter filter = new QueryFilter(userInput);
		
		assertTrue(filter.getJMBAG().isPresent());
		
		assertTrue(filter.accepts(marin));
	}
	
	@Test
	public void testQueryFilterNotAcepted() {
		
		String userInput = "jmbag= \"*1\"and    firstname  = \"A*n\"";
		
		StudentRecord marin = new StudentRecord("0000000001", "Akšamović", "Marin", "2");
		
		QueryFilter filter = new QueryFilter(userInput);
		
		assertFalse(filter.getJMBAG().isPresent());
		
		assertFalse(filter.accepts(marin));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
