package hr.fer.zemris.java.tecaj.hw5.db;

import hr.fer.zemris.java.tecaj.hw5.filter.QueryFilter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Program that simulates student's database. Entries are student records defined by students JMBAG, last name, first name and 
 * final grade. It supports only one command: {@code query}. User can't {@code query} trough student's final grades. 
 * Multiple conditions must always be connected by {@code and}. String literals must be written in quotes symbols. Program
 * supports following six comparison operators: >, <, >=, <=, =, !=. On the left side of a comparison operator a field name 
 * is required and on the left side string literal. If comparison = is used, string literal can contain wildcard *(it represents
 * whatever string there is) but this character, if present, can occur once in one comparison. This database is designed to work with Croatian last
 * names of the students.
 * 
 * Here are some examples of command {@code query} usage:
 * 
 * query jmbag="0000000003"
 * query lastName="B*"
 * query firstName>"A" and lastName="B*ć"
 * query firstName>"A" and firstName<"C" and lastName="B*ć" and jmbag>"0000000002"
 * 
 * @author Vilim Staroveški
 *
 */
public class StudentDB {
	
	/**
	 * Private {@link StudentDatabase} containing all students records from file.
	 */
	private static StudentDatabase studentDatabase;

	/**
	 * Method called at the start of program. It doesn't uses command line arguments, so the parameter String args[] 
	 * should be ignored.
	 * 
	 * @param args unnecessary, so therefore ignorable.
	 */
	public static void main(String[] args) {

		try {
			
			studentDatabase = readingFileDatabase("database.txt");
		
		} catch (IOException e) {
			
			System.err.println("There was an error in reading database text file! Shuting down the program...");
			System.exit(1);
		}
		
		BufferedReader readerForUserInput = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in),StandardCharsets.UTF_8));
		
		while(true) {
			
			System.out.print(">");
			
			String line = null;
			try {
				
				line = readerForUserInput.readLine().trim();
				
			} catch (IOException e) {
	
				System.err.println("There was an IO error occured! Exiting the program...");
				System.exit(1);
			}
			
			if(lineExecutor(line, studentDatabase)) {
				
				continue;
			}
			else {
				
				System.out.println("Thank you for using this database! Shuting down the program...");
				System.exit(1);
			}
			
			
		}  
		
	}

	/**
	 * Method that executes the line that user entered.
	 * @param line line that user entered.
	 * @return true if the user wants to continue to work with this program.
	 */
	public static boolean lineExecutor(String line, StudentDatabase database) {

		String expressions = null;
		String command = null;
		if(line.contains(" ")) {
			
			command = line.substring(0, line.indexOf(" ")).toLowerCase();
			expressions = line.substring(line.indexOf(" ")).trim();
		}
		else {
			
			command = line;
		}
		
		if(command.equals("query")) {
			
			return querying(expressions, database);
		}
		
		else if(command.equals("quit")) {
			
			return false;
		}
		
		else {
			
			System.err.println("Unsupported command: \""+command+"\"");
			return true;
		}
		
	}

	/**
	 * Method that prints out all student records that filter accepted.
	 * @param filterdRecords {@link Set} of all student records that filter accepted.
	 */
	public static void printTheFilteredDatabase(TreeSet<StudentRecord> filterdRecords, StudentDatabase database) {
		
		int maxLastNameLen = 0;
		int maxFirstNameLen = 0;
		
		for(StudentRecord student : filterdRecords) {
			
			if(student.getFirstName().length() > maxFirstNameLen) {
				
				maxFirstNameLen = student.getFirstName().length();
			}
			if(student.getLastName().length() > maxLastNameLen) {
				
				maxLastNameLen = student.getLastName().length();
			}
		}
		
		printBorderLine(maxLastNameLen, maxFirstNameLen);
		
		for(StudentRecord student : filterdRecords) {
			
			System.out.print("| "+student.getJMBAG()+" |");
			System.out.print(" "+student.getLastName());
			for(int i = student.getLastName().length(); i <= maxLastNameLen; i++) {
				System.out.print(" ");
			}
			System.out.print("| "+student.getFirstName());
			for(int i = student.getFirstName().length(); i <= maxFirstNameLen; i++) {
				System.out.print(" ");
			}
			System.out.print("| "+student.getFinalGrade()+" |");
			System.out.println();
		}
		
		printBorderLine(maxLastNameLen, maxFirstNameLen);
		
		System.out.println("Records selected: "+filterdRecords.size()+"\n");
	}

	/**
	 * Method that prints the bored line in this form:
	 * +============+===========+===========+===+
	 * to the standard output.
	 * @param maxLastNameLen length of the longest students first name in this database.
	 * @param maxFirstNameLen length of the longest students last name in this database.
	 */
	public static void printBorderLine(int maxLastNameLen, int maxFirstNameLen) {

		System.out.print("+============+=");
		for(int i = 0; i <= maxLastNameLen; i++) {
			System.out.print("=");
		}
		System.out.print("+=");
		for(int i = 0; i <= maxFirstNameLen; i++) {
			System.out.print("=");
		}
		System.out.println("+===+");
	}

	/**
	 * Method that reads all lines from text file database.txt and returns them added in new {@link StudentDatabase}.
	 * @return {@code StudentDatabase} containing all lines from document.
	 * @throws IOException if there is an IO exception occured.
	 */
	public static StudentDatabase readingFileDatabase(String file) throws IOException {

		String[] studentsRecords = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8).toArray(new String[0]);
		
		List<String> listOfRecords = new LinkedList<String>();
		for(String record : studentsRecords) {
			
			listOfRecords.add(record);
		}
		
		return new StudentDatabase(listOfRecords);
	}
	
	/**
	 * Queries trough database and prints out the filtered records.
	 * 
	 * @param line line from user.
	 * @return true if the program will run after quering.
	 */
	public static boolean querying(String line, StudentDatabase database) {
		
		TreeSet<StudentRecord> filterdRecords = new TreeSet<StudentRecord>();
		
		QueryFilter filter = null;
		try {
			
			filter = new QueryFilter(line);
			
		} catch(IllegalArgumentException e) {
			
			return true;
		}
		
		if(filter.getJMBAG().isPresent()) {
			
			System.out.println("Using index for record retrieval.");
			
			StudentRecord student = database.forJMBAG(filter.getJMBAG().get());
			
			if(student != null) {
				
				if(filter.accepts(student)) {
					
					filterdRecords.add(student);
				}
			}
		}
		else {

			filterdRecords.addAll(database.filter(filter));
		}
		
		printTheFilteredDatabase(filterdRecords, database);
		
		return true;
	}
}
