package hr.fer.zamris.java.tecaj.hw5.observer1;

/**
 * Interface for {@link IntegerStorage} observer. Its used in Observer design pattern. 
 * 
 * @author Vilim Staroveški
 *
 */
public interface IntegerStorageObserver {

	/**
	 * Method called when istorage changes its state.
	 * @param istorage subject we are observing.
	 */
	public void valueChanged(IntegerStorage istorage);
}
