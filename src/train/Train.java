package train;

/**
 * Representation of the train characterized by its name and its position
 */
public class Train implements Runnable {
	private final String name;
	private final Position pos;

	public Train(String name, Position p) throws BadPositionForTrainException {
		if (name == null || p == null)
			throw new NullPointerException();

		// a train should be first be in a station
		if (!(p.getElement() instanceof Station))
			throw new BadPositionForTrainException(name);

		this.name = name;
		this.pos = p.clone();
		// we add a train to the station in which the train is initialized
		Station s =(Station) this.pos.getElement();
		s.updateNbTrains();
	}
	
	/**
	 * Moves the train to the next element of the railway
	 */
	public synchronized void move() throws InterruptedException {
	    this.pos.goToNextElement(this);
	    // we add this condition for exercise 4
	    if (this.getPos().getElement() instanceof Station) {
	        // allow time for the other train to arrive at the intermediate station
	        Thread.sleep(1000);
	    }
	    System.out.println(this.toString());
	}

	@Override
	public void run() {
		while(true) {
			try {
				pos.getElement().leave(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.move();
				// allow time for each train to see their movement
				Thread.sleep(3000);
			} catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Returns the position of the train
	 * @return Position
	 */
	public Position getPos() {
		return pos;
	}
	
	/**
	 * Returns the name of the train
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Train[");
		result.append(this.name);
		result.append("]");
		result.append(" is on ");
		result.append(this.pos);
		return result.toString();
	}
}

