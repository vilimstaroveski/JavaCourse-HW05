package hr.fer.zemris.java.tecaj.hw5.db;

import static org.junit.Assert.*;
import hr.fer.zemris.java.tecaj.hw5.filter.IFilter;
import hr.fer.zemris.java.tecaj.hw5.filter.QueryFilter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests for class {@link StudentDatabase}.
 * @author Vilim Staroveški
 *
 */
public class StudentDatabaseTests {

	@Test
	public void testConstructor() {
		
		List<String> linesFromDatabase = new ArrayList<String>();
		linesFromDatabase.add("0000000001	Akšamović	Marin	2");
		linesFromDatabase.add("0000000002	Bakamović	Petra	3");
		
		StudentDatabase database = new StudentDatabase(linesFromDatabase);
		StudentRecord marin = new StudentRecord("0000000001", "Akšamović", "Marin", "2");
		
		assertTrue(database.forJMBAG("0000000001").equals(marin));
		assertNull(database.forJMBAG("0000000004"));
		assertFalse(database.forJMBAG("0000000002").equals(marin));
		
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testConstructorException() {
		
		List<String> linesFromDatabase = new ArrayList<String>();
		linesFromDatabase.add("0000000001AkšamovićMarin2");
		
		@SuppressWarnings("unused")
		StudentDatabase database = new StudentDatabase(linesFromDatabase);
	}
	
	@Test
	public void testFilterMethod() {
		
		List<String> linesFromDatabase = new ArrayList<String>();
		linesFromDatabase.add("0000000001	Akšamović	Marin	2");
		linesFromDatabase.add("0000000002	Bakamović	Petra	3");
		
		StudentDatabase database = new StudentDatabase(linesFromDatabase);
		StudentRecord marin = new StudentRecord("0000000001", "Akšamović", "Marin", "2");
		
		IFilter filter = new QueryFilter("lastname = \"A*\"");
		
		ArrayList<StudentRecord> expectedRecords = new ArrayList<StudentRecord>();
		expectedRecords.add(marin);
		
		assertTrue(database.filter(filter).equals(expectedRecords));
		
		IFilter filter2 = new QueryFilter("firstname = \"A*\"");
		
		assertFalse(database.filter(filter2).equals(expectedRecords));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsValidGradeMethod() {
		
		List<String> linesFromDatabase = new ArrayList<String>();
		linesFromDatabase.add("0000000001	Akšamović	Marin	2");
		
		StudentDatabase database = new StudentDatabase(linesFromDatabase);
		
		database.isValidStudentsFinalGrade("22");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIsValidGradeMethod2() {
		
		List<String> linesFromDatabase = new ArrayList<String>();
		linesFromDatabase.add("0000000001	Akšamović	Marin	2");
		
		StudentDatabase database = new StudentDatabase(linesFromDatabase);
		
		database.isValidStudentsFinalGrade("A");
		
	}
}
