package data_mangement;

import java.util.ArrayList;

public class WeightedGraph {
	private final int V;
	private int E;
	private ArrayList<Edge>[] adjacencyList;
	
	public WeightedGraph(int vertices) {
		V = vertices;
		
		adjacencyList = (ArrayList<Edge>[]) new ArrayList[V];
		
		for(int i = 0; i < V; i++) {
			adjacencyList[i] = new ArrayList();
		}
		
	}
	
	public void addEdge(Edge edge) {
		int to = edge.to();
		int from = edge.from();
		
		adjacencyList[to].add(edge);
		adjacencyList[from].add(edge);
		E++;
	}
	
	public ArrayList<Edge> edges(int vertex){
		return adjacencyList[vertex];
	}
	
	public int vertices() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < V; i++) {
			str = str + i + " - ";
			for(int j = 0; j < adjacencyList[i].size(); j++) {
				str = str + j + ", ";
			}
			str = str + "\n";
		}
		return str;
	}
}
