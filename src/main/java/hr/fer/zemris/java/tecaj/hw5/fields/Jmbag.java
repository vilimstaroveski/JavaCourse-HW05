package hr.fer.zemris.java.tecaj.hw5.fields;

import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Implementation of {@link IFieldValueGetter} that gets jmbag of wanted {@link StudentRecord}.
 * @author Vilim Staroveški
 *
 */
public class Jmbag implements IFieldValueGetter {

	/**
	 * Private String representing the name of this getter.
	 */
	private final String name;
	
	/**
	 * Constructor that sets name of this getter to "jmbag"
	 */
	public Jmbag() {
		
		this.name = "jmbag";
	}
	
	@Override
	public String get(StudentRecord record) {

		return record.getJMBAG();
	}

	public String getName() {
		
		return name;
	}
}
