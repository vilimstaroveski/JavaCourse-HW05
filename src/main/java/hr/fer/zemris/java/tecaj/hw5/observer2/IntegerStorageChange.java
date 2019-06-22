package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Class that represents a state change of a {@link IntegerStorage}.
 * @author Vilim Staroveški
 *
 */
public class IntegerStorageChange {

	/**
	 * {@link IntegerStorage} reference for a storage that had a change of state.
	 */
	private IntegerStorage storage;
	
	/**
	 * Int value that was before change happened.
	 */
	private int previusValue;
	
	/**
	 * New int value of the storage.
	 */
	private int currentValue;

	/**
	 * Creates new instance of {@link IntegerStorage}.
	 * 
	 * @param storage {@link IntegerStorage} that had a state change.
	 * @param previusValue value that was in storage before the change.
	 * @param currentValue value that is in storage after the change.
	 */
	public IntegerStorageChange(IntegerStorage storage, int previusValue, int currentValue) {
		
		super();
		this.storage = storage;
		this.previusValue = previusValue;
		this.currentValue = currentValue;
	}

	/**
	 * Returns {@link IntegerStorage} of this class.
	 * @return {@link IntegerStorage} of this class.
	 */
	public IntegerStorage getStorage() {
		return storage;
	}

	/**
	 * Returns value that was in storage before the change.
	 * @return value that was in storage before the change.
	 */
	public int getPreviusValue() {
		return previusValue;
	}

	/**
	 * Returns value that is in storage after the change.
	 * @return value that is in storage after the change.
	 */
	public int getCurrentValue() {
		return currentValue;
	}
	
}
