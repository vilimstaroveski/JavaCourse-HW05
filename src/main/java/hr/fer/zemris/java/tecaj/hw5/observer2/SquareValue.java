package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Class writes a square of the integer stored in the {@link IntegerStorage} to the standard output.
 * @author Vilim Staroveški
 *
 */
public class SquareValue implements IntegerStorageObserver{

	/**
	 * Creates new {@link SquareValue}.
	 */
	public SquareValue() {
		
		super();
	}

	@Override
	public void valueChanged(IntegerStorageChange change) {

		System.out.println("Provided new value: "+change.getCurrentValue()+", square is "+Math.pow(change.getCurrentValue(), 2));
	}

}
