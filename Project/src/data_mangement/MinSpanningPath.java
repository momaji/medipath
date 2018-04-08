package data_mangement;

/**
 * The MinSpanningPath class contains a constructor, that when initialized will find the shortest path that hits every single node in the graph.
 * Inspiration taken from the structures of shortest path algorithms implementations in Robert Sedgewicks Algorithms 4th Edition Textbook.
 */
public class MinSpanningPath {
	//Array of weighted edges which will represent the shortest spanning path as the algorithm runs
	private WeightedEdge[] pathTo;
	//Array of boolean values indicating whether the node has been previously visited or not, will be updated as algorithm runs
	private boolean[] marked;
	
	/**
	 * Constructor takes in a weighted graph containing weighted directional edges, and a starting source node, and calls the shortest path algorithm
	 * on that node. Initializes the array which tracks the shortest path, the directed edges are stored at the index place of their destination node.
	 * The path can then be traced back from this. Also initializes the marked array which tracks whether or not the node has been visited yet.
	 * @param g - Weighted graph
	 * @param start - Source node
	 */
	public MinSpanningPath(WeightedGraph g, int start) {
		pathTo = new WeightedEdge[g.vertices()];
		marked = new boolean[g.vertices()];
		
		int len = g.vertices();
		
		shortestPath(g, start, len);
	}
	
	/**
	 * This method takes in a weighted graph, an origin node, and the length of the path by number of vertices contained. The algorithm is recursively
	 * structured and works by marking the source node, then branching out from the source node towards all other possible un-marked nodes. A min 
	 * variable is initialized to inifinity and gets compared to all the distances between the nodes. Once a minimum distance is found, the node
	 * which that edge goes to is updated in the path array, and the function is called again with that node as the source node and the total length 
	 * of the path is reduced by one.
	 * @param g
	 * @param origin
	 * @param len
	 */
	private void shortestPath(WeightedGraph g, int origin, int len) {
		if(len == 1) {
			return;
		}
		marked[origin] = true;
		double min = Double.POSITIVE_INFINITY;
		WeightedEdge e = null;

		for(WeightedEdge i : g.edges(origin)) {
			if(i.weight() < min && !marked[i.from()]) {
				min = i.weight();
				e = i;
			}
		}
		marked[e.from()] = true;
		pathTo[e.to()] = e;
		shortestPath(g, e.from(), len-1);
	}
	
	/**
	 * This method returns the shortest spanning path in the form of the array of ints which gets updated as the algorithm runs.
	 * @return Array of integers containing the shortest path which spans through all nodes in the graph
	 */
	public WeightedEdge[] getPath() {
		return pathTo;
	}
	
	//Strictly for testing purposes only, prints out the path in a formatted fashion easy to understand
	public void String() {
		for(int i = 0; i < pathTo.length; i++) {
			System.out.println(pathTo[i]);
		}
	}
}