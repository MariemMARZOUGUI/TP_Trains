package train;


/**
 * Representation of a circuit made up of railway track elements: station or track section
 */
public class Railway {
	private final Element[] elements;
	public int RLTrainsOnTrack; // specifies the number of trains that are going from right to left
	public int LRTrainsOnTrack; // specifies the number of trains that are going from left to right

	public Railway(Element[] elements) {
		if(elements == null)
			throw new NullPointerException();
		
		this.elements = elements;
		for (Element e : elements)
			e.setRailway(this);
		RLTrainsOnTrack=0;
		LRTrainsOnTrack=0;
	}
	
	/**
	 * Return the elements of the train
	 * @return Element[]
	 */
	public Element[] getElements() {
		return elements;
	}

	/**
	 * Returns the number of the trains that are going from right to left
	 * @return RLTrainOnTrack
	 */
	public int getRLTrainsOnTrack() {
	    return RLTrainsOnTrack;
	}
	
	/**
	 * Returns the number of the trains that are going from left to right
	 * @return LRTrainOnTrack
	 */
	public int getLRTrainsOnTrack() {
	    return LRTrainsOnTrack;
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

