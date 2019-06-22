package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * This class counts and writes to the standard output, the number of times value stored integer 
 * has been changed since the registration.
 * @author Vilim Staroveški
 *
 */
public class ChangeCounter implements IntegerStorageObserver {

	/**
	 * Int value that tells how many times value has changed during this {@link ChangeCounter} was 
	 * registered to subject {@link IntegerStorage}
	 */
	private int counterOfChanges;
	
	/**
	 * Creates new {@link ChangeCounter} with initial counterOfChanges set to 0;
	 */
	public ChangeCounter() {
		
		super();
		this.counterOfChanges = 0;
	}

	@Override
	public void valueChanged(IntegerStorageChange change) {

		counterOfChanges++;
		System.out.println("Number of value changes since tracking: "+counterOfChanges);
	}


}
