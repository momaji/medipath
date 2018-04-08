package data_mangement;

public class WeightedEdge {
	//Integer representing node the edge stems towards
	private final int to;
	//Integer representing node the edge stems from
	private final int from;
	//Double value representing distance
	private final double weight;
	
	/**
	 * This constructor takes in two integers representing nodes on either end of the edge, and a double value representing distance.  And initializes
	 * the state variables to the corresponding parameters.
	 * @param to - Integer representing node the edge stems towards
	 * @param from - Integer representing node the edge stems from
	 * @param distance - Double value representing distance
	 */
	public WeightedEdge(int to, int from, double distance) {
		weight = distance;
		this.to = to;
		this.from = from;
	}
	
	/**
	 * Returns the weight value of an edge which corresponds to the distance between the nodes
	 * @return Double value
	 */
	public double weight() {
		return weight;
	}
	
	/**
	 * Returns the node that the edge stems towards
	 * @return Integer value
	 */
	public int to() {
		return to;
	}
	
	/**
	 * Returns the node that the edge stems from
	 * @return Integer value
	 */
	public int from() {
		return from;
	}
	
	public String toString() {
		return "From " + from + " to " + to;
	}
	
}
