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
	public synchronized void enter() throws InterruptedException {
		while(nbTrains==size) {
			wait();
		}
		nbTrains++;	
		System.out.println("arrivée à la gare "+this.toString()+" qui possède à présent "+nbTrains+" trains");

	}

	@Override
	public synchronized void leave() {
		nbTrains--;
		notifyAll();
		System.out.println("depart de la gare "+this.toString()+" qui possède à présent "+nbTrains+" trains");
	}
}



