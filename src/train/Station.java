package train;

/**
 * Représentation d'une gare. C'est une sous-classe de la classe {@link Element}.
 * Une gare est caractérisée par un nom et un nombre de quais (donc de trains
 * qu'elle est susceptible d'accueillir à un instant donné).
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
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
	
	public void updateNbTrains() {
		nbTrains++;
	}

	@Override
	public synchronized void enter(Train t) throws InterruptedException {
		Element[] elements = railway.getElements();
		if (this==elements[0]  || this==elements[elements.length-1] ) {
	    	if(t.getPos().getDirection()==Direction.RL ) {
		        railway.LRTrainOnTrack--;
		    }
			else if(t.getPos().getDirection()==Direction.LR ) {
		        railway.RLTrainOnTrack--;
		    }
	    }
		
		while(nbTrains==size) {
			wait();
		}
		nbTrains++;	
		
	    System.out.println(t.getName()+" reached the station " + this.toString() + " that has now " + nbTrains + " trains");

	}

	@Override
	public synchronized void leave(Train t) throws InterruptedException {
		while (railway.getLRDirectionTrainOnTrack()>0 && t.getPos().getDirection()==Direction.RL || railway.getRLDirectionTrainOnTrack()>0 && t.getPos().getDirection()==Direction.LR) {
	        wait();
	    }
		nbTrains--;
		notifyAll();
		if(t.getPos().getDirection()==Direction.RL ) {
	        railway.RLTrainOnTrack++;
	    }
		else if(t.getPos().getDirection()==Direction.LR ) {
	        railway.LRTrainOnTrack++;
	    }
	    System.out.println(t.getName()+" left the station " + this.toString() + " that has now " + nbTrains + " trains");
	}
}



