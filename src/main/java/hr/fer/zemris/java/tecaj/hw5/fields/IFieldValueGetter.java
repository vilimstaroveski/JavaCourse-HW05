package hr.fer.zemris.java.tecaj.hw5.fields;

import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Interface used for getting attributes of {@link StudentRecord}'s.
 * @author Vilim Staroveški
 *
 */
public interface IFieldValueGetter {

	/**
	 * Returns attribute of the {@link StudentRecord} as String value.
	 * @param record record in which we looking for attribute.
	 * @return attribute of the {@link StudentRecord} as String value.
	 */
	public String get(StudentRecord record);
	
	/**
	 * Returns name of getter as String value.
	 * @return name of getter as String value.
	 */
	public String getName();
}
