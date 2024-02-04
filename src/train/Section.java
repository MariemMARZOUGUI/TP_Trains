package train;

/**
 * Representation of a section of railway
 */
public class Section extends Element {
	private boolean busy; // specifies the state of the section: true if a train is going on it, false otherwise
	private int nbTrains;
	public Section(String name) {
		super(name);
		busy=false; // initially, the section isn't busy
		nbTrains=0; // takes 0 or 1 during the entire program
	}

	/**
	 * Process the arrival of the train
	 * @param Train
	 */
	@Override
	public synchronized void enter(Train t) throws InterruptedException {
		// the train can't enter the section if another train is in it 
		// or if the direction of this train is different from the direction of the other trains
	    while (busy 
	    		|| railway.getLRTrainsOnTrack()>0 && t.getPos().getDirection()==Direction.RL 
	    		|| railway.getRLTrainsOnTrack()>0 && t.getPos().getDirection()==Direction.LR) {
	        wait();
	    }
	    // update the state of the section 
	    busy = true;
	    nbTrains++;
	    System.out.println(t.getName()+" reached the section " + this.toString() + " that has now " + nbTrains + " trains");
	}

	/**
	 * Process the departure of the train
	 * @param Train
	 */
	@Override
	public synchronized void leave(Train t) {
	    busy = false;
	    notifyAll();
	    nbTrains--;
	    System.out.println(t.getName()+" left the section " + this.toString() + " that has now " + nbTrains + " trains");
	}
}


