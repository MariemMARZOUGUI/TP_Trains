package train;

/**
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
public class Main {
	public static void main(String[] args) {
		Station A = new Station("GareA", 3);
		Station D = new Station("GareD", 3);
		Section AB = new Section("AB");
		Section BC = new Section("BC");
		Section CD = new Section("CD");
		Railway r = new Railway(new Element[] { A, AB, BC, CD, D });
		System.out.println("The railway is:");
		System.out.println("\t" + r);
		Position p = new Position(A, Direction.LR);
		Position p1 = new Position(D, Direction.RL);
		try {
			Train train1 = new Train("Train1", p);
			Train train2 = new Train("Train2", p);
			Train train3 = new Train("Train3", p1);

			// Create and start threads for trains
			Thread thread1 = new Thread(train1);
			Thread thread2 = new Thread(train2);
			Thread thread3 = new Thread(train3);

			thread1.start();
			thread2.start();
			thread3.start();
		} catch (BadPositionForTrainException e) {
			System.out.println("Le train " + e.getMessage());
		}

	}
}