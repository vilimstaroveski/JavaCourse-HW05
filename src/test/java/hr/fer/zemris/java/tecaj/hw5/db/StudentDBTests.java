package hr.fer.zemris.java.tecaj.hw5.db;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Tests for class {@link StudentDB}.
 * @author Vilim Staroveški
 *
 */
public class StudentDBTests {

	@Test(expected=IOException.class)
	public void testLineExecutorWithException() throws IOException {
		
		StudentDatabase database = StudentDB.readingFileDatabase("somethin wrong");
		
	}
	
	@Test
	public void testLineExecutor() throws IOException {
		
		StudentDatabase database = StudentDB.readingFileDatabase("database.txt");
		
		StudentRecord student = new StudentRecord("0000000001", "Akšamović", "Marin", "2");
		
		assertNotNull(database);
		
		assertTrue(database.forJMBAG("0000000001").equals(student));
		
		assertTrue(StudentDB.lineExecutor("query lastname = \"Anić\"", database));
		
		assertTrue(StudentDB.lineExecutor("que lastname", database));
		
		assertTrue(StudentDB.lineExecutor("lastname = \"Anić\"", database));
		
		assertFalse(StudentDB.lineExecutor("quit", database));
		
		assertFalse(StudentDB.lineExecutor("quit lastname = \"Anić\"", database));
		
	}
	
	@Test
	public void testBorderLine() {
		
		StudentDB.printBorderLine(5, 3);
		
	}
	
	@Test
	public void testPrintDatabase() throws IOException {
		
		StudentDatabase database = StudentDB.readingFileDatabase("database.txt");
		
		TreeSet<StudentRecord> set = new TreeSet<StudentRecord>();
		
		StudentRecord student = new StudentRecord("0000000001", "Akšamović", "Marin", "2");
		
		set.add(student);
		
		StudentDB.printTheFilteredDatabase(set, database);
		
		assertNotNull(database);
		
		assertTrue(database.forJMBAG("0000000001").equals(student));
		
	}
	
	@Test
	public void testQuerying() throws IOException {
		
		StudentDatabase database = StudentDB.readingFileDatabase("database.txt");
		
		TreeSet<StudentRecord> set = new TreeSet<StudentRecord>();
		
		String line = "lastname = \"Akšamović\"";
		
		assertTrue(StudentDB.querying(line, database));
		
		line = "jmbag = \"0000000001\"";
		
		assertTrue(StudentDB.querying(line, database));
		
		line = "jm = \"0000000001\"";
		
		assertTrue(StudentDB.querying(line, database));
	}
	
	
	
}
