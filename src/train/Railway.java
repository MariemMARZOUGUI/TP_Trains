package train;


/**
 * Représentation d'un circuit constitué d'éléments de voie ferrée : gare ou
 * section de voie
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 */
public class Railway {
	private final Element[] elements;
	private boolean oppositeDirectionTrainOnTrack;

	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
	}
	
	public Element[] getElements() {
		return elements;
	}
	
	public synchronized void setOppositeDirectionTrainOnTrack(boolean value) {
	    oppositeDirectionTrainOnTrack = value;
	}

	public synchronized boolean isOppositeDirectionTrainOnTrack() {
	    return oppositeDirectionTrainOnTrack;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Element e : this.elements) {
			if (first)
				first = false;
			else
				result.append("--");
			result.append(e);
		}
		return result.toString();
	}
}

