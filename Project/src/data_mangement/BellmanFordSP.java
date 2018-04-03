package data_mangement;

import java.util.*;

//Implementing a structure which takes in an edge weigted graph and constructs a shortest path tree using the bellman ford algorithm.
//Using inspiration from Robert Sedgewicks Algorithms - Fourth Edition textbook
public class BellmanFordSP {
	private Edge[] pathTo;
	private double[] distTo;
	private boolean[] onQ;
	private Queue<Integer> Q;
	
	/**
	 * Constructor, creates an object which contains all the vital components needed to ultimately construct a shortest path tree using the bellman ford algorithm.
	 * An edge weighted graph containing all of the weighted and directed edges and a source node are passed in as parameters to begin
	 * the search. Then an array of weighted edges is initialized to the number of cities in the main cities arraylist and will store
	 * the shortest path tree with the elements being edges and the index values being the node that the edge stems to. Using an array
	 * of boolean values to track what nodes have been on the queue, and a queue represented by a linked list to cycle through all 
	 * possible paths to stem out from from the source. The queue is iterated over performing relaxations on all the edges in the graph
	 * which were previously initialized to infinity until every edge has been relaxed at which point the shortest path tree has been
	 * constructed
	 * @param g - WeightedGraph object
	 * @param start - Source node integer representation
	 */
	public BellmanFordSP(WeightedGraph g, int start) {
		pathTo = new Edge[g.vertices()];
		distTo = new double[g.vertices()];
		onQ = new boolean[g.vertices()];
		Q = (Queue<Integer>) new LinkedList();
		
		for(int i = 0; i < g.vertices(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[start] = 0.0;
		onQ[start] = true;
		Q.add(start);
		
		while(!Q.isEmpty()) {
			int vertex = Q.poll();
			onQ[vertex] = false;
			edgeRelax(g, vertex);
		}

	}
	
	/**
	 * This method relaxes all edges stemming from a source node, and the idea is that every step out, the source node gets updated
	 * to the next node branching out through every call of this function. Relaxation works by taking all the edges that stem out
	 * from the source node and check their weighting which is stored in the priceTo array of double values representing meal costs
	 * then seeing if that is less than the preexisting weight set for that edge (which is initialized to infinity) then will either
	 * update the weight or not update it depending on the comparison.
	 * @param g - WeightedGraph object
	 * @param start - Source node integer representation
	 */
	private void edgeRelax(WeightedGraph g, int origin) {
		for(Edge edge : g.edges(origin)) {
			int dest = edge.to();
			System.out.println(distTo[dest]);
			if(distTo[dest] > distTo[origin] + edge.weight()) {
				distTo[dest] = distTo[origin] + edge.weight();
				pathTo[dest] = edge;
				System.out.println(pathTo[dest]);
				if(!onQ[dest]) {
					Q.add(dest);
					onQ[dest] = true;
				}
			}
		}
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