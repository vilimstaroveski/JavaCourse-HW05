package hr.fer.zemris.java.tecaj.hw5.db;

import java.text.Collator;
import java.util.Locale;


/**
 * Class that represents a student record. Record is defined by: students JMBAG, student lastname and firstname and his final grade.
 * 
 * @author Vilim Staroveški
 *
 */
public class StudentRecord implements Comparable<StudentRecord>{
	
	/**
	 * Number with 10 characters, that is unique for each student. Allowed characters are nubmers 0 to 9.
	 */
	private String jmbag;
	
	/**
	 * Lastname of the student.
	 */
	private String lastName;
	
	/**
	 * Firstname of the student.
	 */
	private String firstName;
	
	/**
	 * Used for comparing students last names with Croatian letters in them.
	 */
	private Collator collator;
	
	/**
	 * Student's final grade.
	 */
	private String finalGrade;
	
	/**
	 * Constructor with 4 parameters. Creates new StudentRecord with given attributes.
	 * 
	 * @param JMBAG students JMBAG.
	 * @param lastName students last name.
	 * @param firstName students first name.
	 * @param finalGrade students final grade.
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, String finalGrade) {
		
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
		this.collator = Collator.getInstance(new Locale("hr"));
	}
	
	/**
	 * Returns students JMBAG.
	 * @return students JMBAG.
	 */
	public String getJMBAG() {
		
		return jmbag;
	}
	
	/**
	 * Returns students last name.
	 * @return students last name.
	 */
	public String getLastName() {
		
		return lastName;
	}
	
	/**
	 * Returns students first name.
	 * @return students first name.
	 */
	public String getFirstName() {
		
		return firstName;
	}
	
	/**
	 * Returns students final grade.
	 * @return students final grade.
	 */
	public String getFinalGrade() {
		
		return finalGrade;
	}
	
	@Override
	public int hashCode() {
		
		return this.jmbag.hashCode();
	}
	
	@Override
	public boolean equals(Object drugi) {
		
		if(!(drugi instanceof StudentRecord)) {
			
			return false;
		}
		
		StudentRecord drugiStudent = (StudentRecord) drugi;
		
		return this.jmbag.equalsIgnoreCase(drugiStudent.getJMBAG());	
	}
	
	@Override
	public String toString() {
		
		return this.jmbag+ " "+this.lastName+" "+this.firstName+" "+this.finalGrade; 
	}

	@Override
	public int compareTo(StudentRecord o) {
		
		return collator.compare(lastName, o.getLastName());
	}
}