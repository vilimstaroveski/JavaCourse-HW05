package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Interface for {@link IntegerStorage} observer. Its used in Observer design pattern. 
 * 
 * @author Vilim Staroveški
 *
 */
public interface IntegerStorageObserver {

	/**
	 * Method called when istorage changes its state.
	 * @param {@link IntegerStorageChange} representing the change that happened.
	 */
	public void valueChanged(IntegerStorageChange change);
}
