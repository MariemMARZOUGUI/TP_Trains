package train;

/**
 * Représentation de la position d'un train dans le circuit. Une position
 * est caractérisée par deux valeurs :
 * <ol>
 *   <li>
 *     L'élément où se positionne le train : une gare (classe  {@link Station})
 *     ou une section de voie ferrée (classe {@link Section}).
 *   </li>
 *   <li>
 *     La direction qu'il prend (enumération {@link Direction}) : de gauche à
 *     droite ou de droite à gauche.
 *   </li>
 * </ol>
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr> Modifié par Mayte
 *         Segarra 
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 *         
 * @version 0.3
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

	public Element getElement() {
		return element;
	}
	
	
	public void turn() {
		if (direction == Direction.LR) {
			direction = Direction.RL;
		} else {
			direction = Direction.LR;
		}
	}
	
	public void goToNextElement() {
		Railway railway= element.getRailway();
		Element[] elements = railway.getElements();
		Element nextElement =this.element.nextElement(direction);
		if ( nextElement==elements[elements.length-1]&& direction == Direction.LR ) {
			this.turn();
		}
		if ( nextElement==elements[0]&& direction == Direction.RL ) {
			this.turn();
		}
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
