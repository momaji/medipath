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
				
				Edge e1 = new Edge(i, j, /*Distance.getDistance(nodes.get(i), nodes.get(i+1))*/ 100 + j + i);
				Edge e2 = new Edge(j, i, /*Distance.getDistance(nodes.get(i), nodes.get(i+1))*/ 100 + j + i);
				System.out.println(e1.from() + " " + e1.to());
				map.addEdge(e1); 
				map.addEdge(e2); 
				//System.out.println(i + " " + j);
			}
			System.out.println("\n");
		}
		
		return map;
		
	}
	
}
