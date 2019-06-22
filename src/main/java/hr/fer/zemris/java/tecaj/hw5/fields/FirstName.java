package hr.fer.zemris.java.tecaj.hw5.fields;

import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Implementation of {@link IFieldValueGetter} that gets first name of wanted {@link StudentRecord}.
 * @author Vilim Staroveški
 *
 */
public class FirstName implements IFieldValueGetter {

	/**
	 * Private String representing the name of this getter.
	 */
	private final String name;
	
	/**
	 * Constructor that sets name of this getter to "firstname"
	 */
	public FirstName() {
		
		this.name = "firstname";
	}
	
	@Override
	public String get(StudentRecord record) {

		return record.getFirstName();
	}

	@Override
	public String getName() {
		
		return name;
	}
}
