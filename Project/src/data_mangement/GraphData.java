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
			for(int j = 0; j < i; j++) {
				Edge e = new Edge(i, j, /*Distance.getDistance(nodes.get(i), nodes.get(i+1))*/ 100 + j + i);
				map.addEdge(e); 
				//System.out.println(i + " " + j);
			}
		}
		
		return map;
		
	}
	
}
