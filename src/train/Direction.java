package train;

/**
 * Representation of the direction of a train : from right to left
 * or from left to right
 */
public enum Direction {
	LR {
		@Override
		public String toString() {
			return "from left to right.";
		}
	},
	RL {
		@Override
		public String toString() {
			return "from right to left.";
		}
	}
}
