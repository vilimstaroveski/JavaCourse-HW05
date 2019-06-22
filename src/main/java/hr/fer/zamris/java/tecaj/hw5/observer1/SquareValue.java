package hr.fer.zamris.java.tecaj.hw5.observer1;

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
	public void valueChanged(IntegerStorage istorage) {

		System.out.println("Provided new value: "+istorage.getValue()+", square is "+Math.pow(istorage.getValue(), 2));
	}

}
