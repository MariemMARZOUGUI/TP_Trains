package train;

/**
 * Representation of a train station. It is a subclass of the Element class.
 * A station is characterized by a name and a number of platforms (i.e. trains) 
 * that it is likely to host at a given time.
 */
public class Station extends Element {
	private final int size;
	private int nbTrains;

	public Station(String name, int size) {
		super(name);
		if(name == null || size <=0)
			throw new NullPointerException();
		this.size = size;
		nbTrains=0;
	}
	
	/**
	 * Updates the number of trains in the station
	 */
	public void updateNbTrains() {
		nbTrains++;
	}

	/**
	 * Process the arrival of the train
	 * @param Train
	 */
	@Override
	public synchronized void enter(Train t) throws InterruptedException {
		// prevent station entry if the station is full
		while(nbTrains==size) {
			wait();
		}
		Element[] elements = railway.getElements();
		
		// update the number of trains in each direction 
		if (this==elements[0]|| this==elements[elements.length-1]) {
			if(t.getPos().getDirection()==Direction.RL ) {
		        railway.LRTrainsOnTrack--;
		    }
			else if(t.getPos().getDirection()==Direction.LR ) {
		       railway.RLTrainsOnTrack--;
		   }
		}
		nbTrains++;	
	    System.out.println(t.getName()+" reached the station " + this.toString() + " that has now " + nbTrains + " trains");
	}

	/**
	 * Process the departure of the train
	 * @param Train
	 */
	@Override
	public synchronized void leave(Train t) throws InterruptedException {
		// prevent station exits if trains in the other direction are on the line.
		while (railway.getLRTrainsOnTrack()>0 && t.getPos().getDirection()==Direction.RL 
				|| railway.getRLTrainsOnTrack()>0 && t.getPos().getDirection()==Direction.LR) {
	        wait();
	    }
		nbTrains--;
		notifyAll();
		Element[] elements = railway.getElements();
		
		// update the number of trains in each direction 
		if (this==elements[0]|| this==elements[elements.length-1]) {
			if(t.getPos().getDirection()==Direction.RL ) {
		        railway.RLTrainsOnTrack++;
		    }
			else if(t.getPos().getDirection()==Direction.LR ) {
		        railway.LRTrainsOnTrack++;
		    }
		}
	    System.out.println(t.getName()+" left the station " + this.toString() + " that has now " + nbTrains + " trains");
	}
}



