package hr.fer.zemris.java.tecaj.hw5.db;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for class {@link StudentRecord}.
 * @author Vilim Staroveški
 *
 */
public class StudentRecordTests {

	@Test
	public void TestStudentRecordConstructor() {
		
		StudentRecord student = new StudentRecord("000000002", "Test", "Imetest", "4");
		
		assertTrue(student.getJMBAG().equals("000000002"));
		assertTrue(student.getLastName().equals("Test"));
		assertTrue(student.getFirstName().equals("Imetest"));
		assertTrue(student.getFinalGrade().equals("4"));
	}
	
	@Test
	public void TestHashCode() {
		
		StudentRecord student = new StudentRecord("000000002", "Test", "Imetest", "4");
		
		String jmbag = "000000002";
		assertTrue(student.hashCode() == jmbag.hashCode());
		
	}
	
	@Test
	public void TestEquals() {
		
		StudentRecord student = new StudentRecord("000000002", "Test", "Imetest", "4");
		StudentRecord student2 = new StudentRecord("000000002", "T", "Imt", "2");
		
		assertTrue(student.equals(student2));
		assertFalse(student.equals("Test"));
	}
	
	@Test
	public void TestToString() {
		
		StudentRecord student = new StudentRecord("000000002", "Test", "Imetest", "4");
		
		String expected = "000000002 Test Imetest 4";
		assertTrue(student.toString().equals(expected));
		
	}
	
	@Test
	public void TestCompare() {
		
		StudentRecord student = new StudentRecord("000000002", "Test", "Imetest", "4");
		StudentRecord student2 = new StudentRecord("000000004", "Est", "Iest", "4");
		
		assertTrue(student.compareTo(student2) > 0);
	}
}
