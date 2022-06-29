package main.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import main.datastructures.WeightedGraph.Edge;

public class ShortestPath {
	private final int[] parent;
	private final double[] distance;
	private final WeightedGraph graph;
	
	public ShortestPath(final WeightedGraph graph,final int startNode) {
		if(startNode >= graph.numberOfNodes() || startNode < 0) {
			throw new IllegalArgumentException("Startnode not in graph!");
		}
		if(ShortestPath.hasNegativeWeight(graph)) {
			throw new IllegalArgumentException("Graph contains negative edge!");
		}
		this.parent = new int[graph.numberOfNodes()];
		this.distance = new double[graph.numberOfNodes()];
		this.graph = graph;
		this.dijkstra(startNode);
	}
	
	private void dijkstra(final int startNode) {
		PriorityQueue<Integer> prioQ = new PriorityQueue<Integer>((node1, node2) -> {return 
				this.distance[node1] - this.distance[node2] < 0 ? -1: (int) Math.ceil(this.distance[node1] - this.distance[node2]);});
		for(int i = 0; i < this.graph.numberOfNodes(); i++) {
			this.parent[i] = -1;
			this.distance[i] = Double.POSITIVE_INFINITY;
			prioQ.add(i);
		}
		this.distance[startNode] = 0;
		prioQ.remove(startNode);
		prioQ.add(startNode);
		
		while(!prioQ.isEmpty()) {
			int currentSource = prioQ.poll();
			for(Edge currentEdge: this.graph.getAdjacencyList().get(currentSource)) {
				int currentDestination = currentEdge.getDestination();
				if(this.distance[currentSource] + currentEdge.getWeight() < this.distance[currentDestination]) {
					this.distance[currentDestination] = this.distance[currentSource] + currentEdge.getWeight();
					prioQ.remove(currentDestination);
					prioQ.add(currentDestination);
					this.parent[currentDestination] = currentSource;
				}
			}
		}
	}
	
	public double distanceTo(final int node) {
		return this.distance[node];
	}
	
	public boolean existsPathTo(final int node) {
		return this.distance[node] != Double.POSITIVE_INFINITY;
	}
	
	public ArrayList<Edge> getPathTo(final int destination){
		if(!this.existsPathTo(destination)) {
			throw new IllegalArgumentException("There is no path to chosen node!");
		}
		ArrayList<Edge> path = new ArrayList<Edge>();
		for(int currentDestination = destination; this.parent[currentDestination] != -1; currentDestination = this.parent[currentDestination]) {
			Edge correctEdge = new Edge(-1,-1,Double.POSITIVE_INFINITY);
			for(Edge currentEdge: this.graph.getAdjacencyList().get(this.parent[currentDestination])) {
				if(currentEdge.getDestination() == currentDestination && currentEdge.getWeight() < correctEdge.getWeight()) {
					correctEdge = currentEdge;
				}
			}
			path.add(correctEdge);
		}
		return path;
	}
	
	private static boolean hasNegativeWeight(final WeightedGraph graph) {
		Iterator<Edge> edges = graph.edgeIterator();
		while(edges.hasNext()) {
			if(edges.next().getWeight() < 0) {
				return true;
			}
		}
		return false;
	}
}
