package hr.fer.zemris.java.tecaj.hw5.observer2;

/**
 * Demonstration of Observer design with {@link IntegerStorage} as subject and {@link IntegerStorageObserver} as observer.
 * 
 * @author Vilim Staroveški
 *
 */
public class ObserverExample {

	/**
	 * Method called when program starts.
	 * @param args arguments from command line.
	 */
	public static void main(String[] args) {
		
		IntegerStorage istorage = new IntegerStorage(20);
		istorage.addObserver(new ChangeCounter());
		istorage.addObserver(new DoubleValue());
		istorage.addObserver(new SquareValue());
		istorage.setValue(50);
		istorage.setValue(50);
		istorage.setValue(20);
		istorage.setValue(5);
	}
}
