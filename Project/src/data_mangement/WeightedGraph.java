package data_mangement;

import java.util.ArrayList;

/**
 * This WeightedGraph class represents the graph data structure used to represent a cluster of hospital objects. Each node is represented by an
 * integer, and the connection of nodes are represented by the WeightedEdge data type.
 * Inspiration taken from the structures of Graph implementations in Robert Sedgewicks Algorithms 4th Edition Textbook.
 */
public class WeightedGraph {
	//Number of nodes contained in the graph
	private final int V;
	//Array of arraylists of weightedEdge data types to track all the nodes and edges contained in the graph
	private ArrayList<WeightedEdge>[] adjacencyList;
	
	/**
	 * This constructor takes in an integer representing the number of nodes that will be in the graph, it then initializes an array of arraylist of edges
	 * to the number of nodes in the graph so that each index of the array will represent a node, and its corresponding array list will represent
	 * the edges that stem towards that node. So that paths can be traced backwards to a source.
	 * @param vertices - Integer representing number of nodes in the graph
	 */
	public WeightedGraph(int vertices) {
		V = vertices;
		
		adjacencyList = (ArrayList<WeightedEdge>[]) new ArrayList[V];
		
		for(int i = 0; i < V; i++) {
			adjacencyList[i] = new ArrayList();
		}
		
	}
	
	/**
	 * This method adds an edge to the graph by adding it to the global adjacency list at the index value of the node the edge stems to.
	 * @param edge - Weighted edge object
	 */
	public void addEdge(WeightedEdge edge) {
		int to = edge.to();
		
		adjacencyList[to].add(edge);
	}
	
	/**
	 * Returns all the weighted edges that stem out from a given vertex/node passed in as a parameter
	 * @param vertex - Node with which you want to get all the edges stemming from it
	 * @return Arraylist of edges
	 */
	public ArrayList<WeightedEdge> edges(int vertex){
		return adjacencyList[vertex];
	}
	
	/**
	 * Returns the number of nodes/vertices in the graph
	 * @return Integer value
	 */
	public int vertices() {
		return V;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < V; i++) {
			str = str + i + " - ";
			for(WeightedEdge j : adjacencyList[i]) {
				str = str + j.from() + ", " + j.to();
			}
			str = str + "\n";
		}
		return str;
	}
}
