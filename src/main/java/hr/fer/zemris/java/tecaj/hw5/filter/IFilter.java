package hr.fer.zemris.java.tecaj.hw5.filter;

import hr.fer.zemris.java.tecaj.hw5.db.StudentDatabase;
import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Interface used for filter {@link StudentDatabase} by accepting {@link StudentRecord} that satisfies filter.
 * 
 * @author Vilim Staroveški
 *
 */
public interface IFilter {
	
	/**
	 * Returns true if parameter record satisfies filter.
	 * @param record {@link StudentRecord} record that is being checked.
	 * @return true if parameter record satisfies filter. False otherwise.
	 */
	public boolean accepts(StudentRecord record);
}