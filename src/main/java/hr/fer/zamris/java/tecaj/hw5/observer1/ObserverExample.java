package hr.fer.zamris.java.tecaj.hw5.observer1;

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
		IntegerStorageObserver observer = new SquareValue();
		istorage.addObserver(observer);
		istorage.setValue(5);
		istorage.setValue(2);
		istorage.setValue(25);
		istorage.removeObserver(observer);
		istorage.addObserver(new ChangeCounter());
		istorage.addObserver(new DoubleValue());
		istorage.setValue(13);
		istorage.setValue(22);
		istorage.setValue(15);
		
	}
}
