package hr.fer.zemris.java.tecaj.hw5.db;

import hr.fer.zemris.java.tecaj.hw5.filter.IFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Class that represents database of students records. 
 * @author Vilim Staroveški
 *
 */
public class StudentDatabase {

	/**
	 * Private map with {@link StudentRecord}'s indexed by students jmbag.
	 */
	private HashMap<String, StudentRecord> database;
	/**
	 * Creates new {@link StudentDatabase} from data from database text document. Entries are student records 
	 * defined from given parameter.
	 * 
	 * @param linesFromDatabase {@code List<String>} with lines from database document. 
	 * @throws PatternSyntaxException  if the line from file's is invalid.
	 */
	public StudentDatabase(List<String> linesFromDatabase) {
		
		database = new HashMap<String, StudentRecord>();
		
		for(String record : linesFromDatabase) {
			
			String[] lineData = record.split("	");
			try {
				
				isValidStudentsFinalGrade(lineData[3]);
			} catch(IllegalArgumentException e) {
				
				System.exit(1);
			}
			StudentRecord student = new StudentRecord(lineData[0], lineData[1], lineData[2], lineData[3]);
			database.put(student.getJMBAG(), student);
		}
	}
	
	/**
	 * Method that checks is the given parameter valid final grade for student. Valid grade is number 1-5.
	 * @param grade student's final grade that is needed to be check.
	 * @throws IllegalArgumentException if the grade is not a string representation of in number from 1 to 5.
	 */
	public void isValidStudentsFinalGrade(String grade) {
		
		int gradeAsInt = 0;
		try {
			
			gradeAsInt = Integer.parseInt(grade);
			
		} catch(NumberFormatException e) {
			
			System.err.println("Error in reading students final grade! \""+grade+"\" is not a valid final grade. Program will shut down...");
			throw new IllegalArgumentException();
		}
		if(gradeAsInt <1 || gradeAsInt >5) {
			
			System.err.println("Error in reading students final grade! \""+grade+"\" is not a valid final grade. Program will shut down...");
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Method uses students JMBAG to obtain requested record in O(1); if record does not exists, the method returns
     * null.
     * 
	 * @param jmbag students JMBAG from record we want to get.
	 * @return {@link StudentRecord} of a requested record or null if it doesn't exists.
	 */
	public StudentRecord forJMBAG(String jmbag) {
		
		return this.database.get(jmbag);
	}
	
	/**
	 * Method filters database with filter given as parameter. Method accepts a reference to an object which is an instance of IFilter interface and returns a 
	 * {@code List<Studentrecord>} with record that pass trough filter.
	 * 
	 * @param filter instance of {@link IFilter interface}.
	 * @return {@code List<Studentrecord>} with record that pass trough given filter.
	 */
	public List<StudentRecord> filter(IFilter filter){
		
		ArrayList<StudentRecord> records = new ArrayList<StudentRecord>();
		
		Set<String> keySet = this.database.keySet();
		for(String key : keySet) {
			
			StudentRecord record = this.database.get(key);
			if (filter.accepts(record)) {
				
				records.add(record);
			}
		}
		
		return records;
	}
}
