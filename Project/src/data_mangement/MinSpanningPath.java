package data_mangement;

import java.util.*;

//Implementing a structure which takes in an edge weigted graph and constructs a shortest path tree using the bellman ford algorithm.
//Using inspiration from Robert Sedgewicks Algorithms - Fourth Edition textbook
public class MinSpanningPath {
	private Edge[] pathTo;
	private double[] distTo;
	private boolean[] marked;
	
	public MinSpanningPath(WeightedGraph g, int start) {
		pathTo = new Edge[g.vertices()];
		distTo = new double[g.vertices()];
		marked = new boolean[g.vertices()];
		
		int len = g.vertices();
		
		shortestPath(g, start, len);

	}
	
	private void shortestPath(WeightedGraph g, int origin, int len) {
		if(len == 1) {
			return;
		}
		double min = Double.POSITIVE_INFINITY;
		Edge e = null;
		for(Edge i : g.edges(origin)) {
			if(i.weight() < min && !marked[i.to()]) {
				min = i.weight();
				e = i;
				marked[i.from()] = true;
				marked[i.to()] = true;
			}
		}
		pathTo[e.to()] = e;
		shortestPath(g, e.to(), len-1);
	}
	
	/**
	 * This method returns the shortest path tree
	 * @return Returns an array of weighted edges representing the shortest path tree
	 */
	public Edge[] getPath() {
		return pathTo;
	}
	
	public void String() {
		for(int i = 0; i < pathTo.length; i++) {
			System.out.println(pathTo[i]);
		}
	}
}