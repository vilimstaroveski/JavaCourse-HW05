package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Class writes to the standard output double value of the current 
 * value which is stored in subject, but only first two times since its registation with subject.
 * @author Vilim Staroveški
 *
 */
public class DoubleValue implements IntegerStorageObserver {

	/**
	 * Int value that tells how many times this class was used.
	 */
	private int numberOfTimesUsed;

	/**
	 * Creates new {@link DoubleValue} with initial number of usage set to 0;
	 */
	public DoubleValue() {
		
		super();
		this.numberOfTimesUsed = 0;
	}

	@Override
	public void valueChanged(IntegerStorageChange change) {

		if(numberOfTimesUsed < 2) {
			
			numberOfTimesUsed++;
			System.out.println("Double value: "+(change.getCurrentValue()*2));
		}
		else {
			
			change.getStorage().removeObserver(this);
		}
	}


}
