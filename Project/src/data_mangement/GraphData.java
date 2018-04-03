package data_mangement;

import java.util.ArrayList;

public class GraphData {
	
	public static WeightedGraph fillGraph(ArrayList<ProviderDataObject> nodes) {
		
		WeightedGraph map = new WeightedGraph(nodes.size());
		
		setEdges(map, nodes);
		
		return map;
	}
	
	private static WeightedGraph setEdges(WeightedGraph map, ArrayList<ProviderDataObject> nodes){
		
		for(int i = 0; i < nodes.size(); i++) {
			//System.out.println(i);
			for(int j = 0; j < i; j++) {
				////////////////WHEN TESTING CHANGE THE GET DISTANCE FUNCTION TO A HARDCODED NUMBER
				//double distance = Distance.getDistance(nodes.get(i), nodes.get(j));
				double distance = 100 + i + j;
				Edge e1 = new Edge(i, j, distance);
				Edge e2 = new Edge(j, i, distance);
				//System.out.println(e1.from() + " " + e1.to());
				map.addEdge(e1); 
				map.addEdge(e2); 
				//System.out.println(i + " " + j);
			}
			//System.out.println("\n");
		}
		
		return map;
		
	}
	
}
