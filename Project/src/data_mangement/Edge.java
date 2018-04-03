package data_mangement;

public class Edge {
	
	private final int to;
	private final int from;
	private final double weight;
	
	public Edge(int to, int from, double distance) {
		weight = distance;
		this.to = to;
		this.from = from;
	}
	
	public double weight() {
		return weight;
	}
	
	public int to() {
		return to;
	}
	
	public int from() {
		return from;
	}
	
	public String toString() {
		return "From " + from + " to " + to;
	}
	
}
