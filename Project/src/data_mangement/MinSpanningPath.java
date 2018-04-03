package data_mangement;

public class MinSpanningPath {
	private Edge[] pathTo;
	private boolean[] marked;
	
	public MinSpanningPath(WeightedGraph g, int start) {
		pathTo = new Edge[g.vertices()];
		marked = new boolean[g.vertices()];
		
		int len = g.vertices();
		
		shortestPath(g, start, len);
	}
	
	private void shortestPath(WeightedGraph g, int origin, int len) {
		if(len == 1) {
			return;
		}
		marked[origin] = true;
		double min = Double.POSITIVE_INFINITY;
		Edge e = null;
		System.out.println(origin + ": ");
		for(Edge i : g.edges(origin)) {
			System.out.println(i);
		}
		for(Edge i : g.edges(origin)) {
			if(i.weight() < min && !marked[i.from()]) {
				min = i.weight();
				e = i;
			}
		}
		marked[e.from()] = true;
		System.out.println(e);
		pathTo[e.to()] = e;
		shortestPath(g, e.from(), len-1);
	}
	
	public Edge[] getPath() {
		return pathTo;
	}
	
	public void String() {
		for(int i = 0; i < pathTo.length; i++) {
			System.out.println(pathTo[i]);
		}
	}
}