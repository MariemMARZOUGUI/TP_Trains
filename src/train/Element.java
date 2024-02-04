package train;

/**
 * Abstract class that represents different types of elements in a railway that could be :
 * - a station
 * - a section
 */
public abstract class Element {
	private final String name;
	protected Railway railway;

	protected Element(String name) {
		if(name == null)
			throw new NullPointerException();
		this.name = name;
	}

	/**
	 * Change the railway of the element
	 * @param Railway
	 */
	public void setRailway(Railway r) {
		if(r == null)
			throw new NullPointerException();
		
		this.railway = r;
	}
	
	/**
	 * Return the railway in which the element is set
	 * @return Railway
	 */
	public Railway getRailway() {
		return railway;
	};
	
	/**
	 * Return the next element for a given direction
	 * @return Element
	 * @param Direction
	 */
	public Element nextElement(Direction d) {
		Element[] elements=railway.getElements();
		Element nextElement = this;
		// find the position of the current element in the railway
		for (int i=0; i<elements.length; i++) {
			if (elements[i]==this  && i!=0  && i!=elements.length-1) {
				// return the element after or before the current element depending on the direction
				if (d == Direction.LR ) {
						nextElement = elements[i+1];
		    	}
				else {
					nextElement = elements[i-1];
				}
	    	}
			// if the current element is the initial point we always return the second point regardless of the direction
			else if (elements[i]==this  && i==0 ) {
				nextElement = elements[1];
	    	}
			// if the current element is the ending point we always return the element before regardless of the direction
			else if (elements[i]==this  && i==elements.length-1 ) {
				nextElement = elements[elements.length-2];
	    	}
		}
		return nextElement;		
	}
	
	/**
	 * Abstract methods that process the departure and the arrival of a train
	 */
	abstract void enter(Train t) throws InterruptedException;
	abstract void leave(Train t) throws InterruptedException;

	@Override
	public String toString() {
		return this.name;
	}

}
