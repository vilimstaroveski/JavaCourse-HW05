package hr.fer.zemris.java.tecaj.hw5.fields;

import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Implementation of {@link IFieldValueGetter} that gets last name of wanted {@link StudentRecord}.
 * @author Vilim Staroveški
 *
 */
public class LastName implements IFieldValueGetter {

	/**
	 * Private String representing the name of this getter.
	 */
	private final String name;
	
	/**
	 * Constructor that sets name of this getter to "lastname"
	 */
	public LastName() {

		this.name = "lastname";
	}
	
	@Override
	public String get(StudentRecord record) {

		return record.getLastName();
	}

	public String getName() {
		
		return name;
	}
}
