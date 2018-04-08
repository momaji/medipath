package data_mangement;

import java.util.ArrayList;

/**
 * This GraphData class contains all of the methods necessary for filling out the weighted graph with nodes and edges from the objects taken from
 * the data file.
 */
public class GraphData {
	
	/**
	 * This method initializes a weighted graph and then passes it to the setEdges function to fill the graph with edges, the nodes arraylist is
	 * passed as well because each edge consists of two hospital locations which will be represented by integers that correspond to the hospitals
	 * index value in the arraylist.
	 * @param nodes - Arraylist of hospital data types
	 * @return Returns a map filled in with directed weighted edges.
	 */
	public static WeightedGraph fillGraph(ArrayList<ProviderDataObject> nodes) {
		
		WeightedGraph map = new WeightedGraph(nodes.size());
		
		setEdges(map, nodes);
		
		return map;
	}
	
	/**
	 * This method initializes a weighted graph and fills it with direced weighted edges and returns it. The nodes it is filled with correspond to the nodes arraylist
	 * passed in. This arraylist contains a number of objects representing hospitals, and each object will be represented by an integer which 
	 * corresponds to its index value in the arraylist. As the method iterates through each possible combination of nodes such that there is no 
	 * connection looked at twice. The method will then use the get distance function from the distance class to find the distance between, and 
	 * create two directed edges one going each direction from the hospitals.
	 * @param nodes - Arraylist of hospital data types
	 * @param map - Weighted graph
	 * @return Returns the updated map filled in with directed weighted edges.
	 */
	private static WeightedGraph setEdges(WeightedGraph map, ArrayList<ProviderDataObject> nodes){
		
		for(int i = 0; i < nodes.size(); i++) {
			//System.out.println(i);
			for(int j = 0; j < i; j++) {
				////////////////WHEN TESTING CHANGE THE GET DISTANCE FUNCTION TO A HARDCODED NUMBER
				//double distance = Distance.getDistance(nodes.get(i), nodes.get(j));
				double distance = 100 + i + j;
				WeightedEdge e1 = new WeightedEdge(i, j, distance);
				WeightedEdge e2 = new WeightedEdge(j, i, distance);
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
