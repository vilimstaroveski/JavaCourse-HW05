package hr.fer.zemris.java.tecaj.hw5.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for storage int values. 
 * 
 * @author Vilim Staroveški
 *
 */
public class IntegerStorage {

	/**
	 * Stored int value.
	 */
	private int value;
	
	/**
	 * List of observers added to this class.
	 */
	private List<IntegerStorageObserver> observers;

	/**
	 * List of observers that is going to be removed from the list on next state change.
	 */
	private List<IntegerStorageObserver> onNextChangeRemove;
	
	/**
	 * List of observers that is going to be added to the list on next state change.
	 */
	private List<IntegerStorageObserver> onNextChangeAdd;
	
	/**
	 * Creates new {@link IntegerStorage} with initial value as parameter.
	 * 
	 * @param initialValue initial int value that is putted in to storage.
	 */
	public IntegerStorage(int initialValue) {
		
		this.value = initialValue;
		this.observers = new ArrayList<IntegerStorageObserver>();
		this.onNextChangeAdd = new ArrayList<IntegerStorageObserver>();
		this.onNextChangeRemove  = new ArrayList<IntegerStorageObserver>();
	}

	/**
	 * Adds an {@link IntegerStorageObserver} to private list observers of this class if not already there.
	 * @param observer {@link IntegerStorageObserver} we want to add.
	 */
	public void addObserver(IntegerStorageObserver observer) {
		// add the observer in observers if not already there ...
		if(!observers.contains(observer)) {
			
			onNextChangeAdd.add(observer);
		}
	}

	/**
	 * Removes wanted {@link IntegerStorageObserver} from private list observers of this class if present.
	 * @param observer {@link IntegerStorageObserver} we want to remove.
	 */
	public void removeObserver(IntegerStorageObserver observer) {
		// remove the observer from observers if present ...
		if(observers.contains(observer)) {
			
			onNextChangeRemove.add(observer);
		}
	}

	/**
	 * Removes all {@link IntegerStorageObserver}'s from this class.
	 */
	public void clearObservers() {
		// remove all observers from observers list ...
		observers.clear();
	}

	/**
	 * Returns int value stored in this {@link IntegerStorage}
	 * @return int value stored in this {@link IntegerStorage}
	 */
	public int getValue() {
		
		return value;
	}

	/**
	 * Sets new value in the storage.
	 * 
	 * @param value value we want to put in storage.
	 */
	public void setValue(int value) {
		// Only if new value is different than the current value:
		if (this.value != value) {
			
			IntegerStorageChange change = new IntegerStorageChange(this, this.value, value);
			// Update current value
			this.value = value;
			// Notify all registered observers
			if(!onNextChangeAdd.isEmpty()) {
				
				observers.addAll(onNextChangeAdd);
				onNextChangeAdd.clear();
			}
			
			if(!onNextChangeRemove.isEmpty()) {
				
				observers.removeAll(onNextChangeRemove);
				onNextChangeRemove.clear();
			}
			
			if (observers != null) {
				
				for (IntegerStorageObserver observer : observers) {
					
					observer.valueChanged(change);
				}
			}
		}
	}
}