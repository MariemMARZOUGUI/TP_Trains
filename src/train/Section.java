package train;

/**
 * Représentation d'une section de voie ferrée. C'est une sous-classe de la
 * classe {@link Element}.
 *
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Section extends Element {
	private boolean busy;
	private int nbTrains;
	public Section(String name) {
		super(name);
		busy=false;
		nbTrains=0;
	}

	@Override
	public synchronized void enter(Train t) throws InterruptedException {
	    while (busy || railway.getLRDirectionTrainOnTrack()>0 && t.getPos().getDirection()==Direction.RL || railway.getRLDirectionTrainOnTrack()>0 && t.getPos().getDirection()==Direction.LR) {
	        wait();
	    }
	    busy = true;
	    nbTrains++;
	    System.out.println(t.getName()+" reached the section " + this.toString() + " that has now " + nbTrains + " trains");
	}

	@Override
	public synchronized void leave(Train t) {
	    busy = false;
	    notifyAll();
	    nbTrains--;
	    System.out.println(t.getName()+" left the section " + this.toString() + " that has now " + nbTrains + " trains");
	}
}


