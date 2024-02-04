package train;

/**
 * Representation of the position of a train in the circuit. One position
 * is characterized by two values:
 * - The element where the train is positioned: a station or a section of track.
 * - The direction it is taking: from left to right or right to left.
 */
public class Position implements Cloneable {
	private Direction direction;
	private Element element;

	public Position(Element elt, Direction d) {
		if (elt == null || d == null)
			throw new NullPointerException();
		this.element = elt;
		this.direction = d;
	}

	@Override
	public Position clone() {
		try {
			return (Position) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get the element of the train
	 * @return Element
	 */
	public Element getElement() {
		return element;
	}
	
	/**
	 * Get the direction of the train
	 * @return Direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Change the direction of the train
	 */
	public void turn() {
		if (direction == Direction.LR) {
			direction = Direction.RL;
		} else {
			direction = Direction.LR;
		}
	}
	
	/**
	 * Move the train to the next element on the railway
	 * @param Train 
	 */
	public synchronized void goToNextElement(Train t) throws InterruptedException {
		// determine the next element that the train has to go to
		Railway railway= element.getRailway();
		Element[] elements = railway.getElements();
		Element nextElement =this.element.nextElement(direction);
		
		// change the direction of the train if is reaching the initial or the last station
		if ( nextElement==elements[elements.length-1] && direction == Direction.LR ) {
			this.turn();
		}
		if ( nextElement==elements[0] && direction == Direction.RL ) {
			this.turn();
		}
		
		// enter the next element
		nextElement.enter(t);
		this.element=nextElement;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(this.element.toString());
		result.append(" going ");
		result.append(this.direction);
		return result.toString();
	}
}
