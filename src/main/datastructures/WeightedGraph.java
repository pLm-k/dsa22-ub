package main.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WeightedGraph{

	private int numNodes;
	private int numEdges;
	private final HashMap<Integer ,ArrayList<Edge>> adjacencyList;

	public WeightedGraph() {
		this.numNodes = 0;
		this.numEdges = 0;
		this.adjacencyList = new HashMap<>();
	}
	
	public HashMap<Integer ,ArrayList<Edge>> getAdjacencyList(){
		return this.adjacencyList;
	}

	public void createFromEdgeList(final ArrayList<Integer> list) throws UnsupportedOperationException{
		if(list == null || list.size() == 0 || list.size() != list.get(1) * 2 + 2 ) {
			throw new IllegalArgumentException();
		}

		if(!this.adjacencyList.isEmpty()) {
			throw new UnsupportedOperationException();
		}

		for(int i = 0; i < list.get(0); i++) {
			this.addNode(i);
		}

		for(int i = 2; i < list.size() - 1; i+=2) {
			this.addEdge(list.get(i), list.get(i+1), 1);
		}
	}


	public ArrayList<Integer> toEdgeList(){
		ArrayList<Integer> edgeList = new ArrayList<Integer>();
		edgeList.add(this.numNodes);
		edgeList.add(this.numEdges);

		for(Iterator<Edge> edgeIterator = this.edgeIterator(); edgeIterator.hasNext();) {
			Edge currentEdge = edgeIterator.next();
			edgeList.add(currentEdge.getSource());
			edgeList.add(currentEdge.getDestination());
		}
		return edgeList;
	}

	public void createFromNodeList(final ArrayList<Integer> list) throws UnsupportedOperationException{
		if(list == null || list.size() == 0 || list.size() != list.get(0) + list.get(1) + 2) {
			throw new IllegalArgumentException();
		}

		if(!this.adjacencyList.isEmpty()) {
			throw new UnsupportedOperationException();
		}

		for(int i = 0; i < list.get(0); i++) {
			this.addNode(i);
		}

		int currentSource = 0;
		Iterator<Integer> listIterator = list.iterator();
		listIterator.next();
		listIterator.next();
		while(listIterator.hasNext()) {
			for(int i = listIterator.next(); i > 0; i--) {
				this.addEdge(currentSource, listIterator.next(), 1);
			}
			currentSource++;
		}
	}

	public ArrayList<Integer> toNodeList(){
		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		nodeList.add(this.numNodes);
		nodeList.add(this.numEdges);

		for(int i = 0; i < this.numNodes; i++) {
			ArrayList<Edge> outgoingEdges = this.adjacencyList.get(i);
			nodeList.add(outgoingEdges.size());
			for(Edge currentEdge: outgoingEdges) {
				nodeList.add(currentEdge.getDestination());
			}
		}
		return nodeList;
	}

	public int numberOfNodes() {
		return this.numNodes;
	}

	public int numberOfEdges() {
		return this.numEdges;
	}

	public int addNode(final int nodeID){
		this.adjacencyList.put(nodeID, new ArrayList<>(1));
		return this.numNodes++;
	}

	public Edge addEdge(final int src, final int dst, final double weight){
		Edge toAdd = new Edge(src, dst, weight);
		this.addEdge(toAdd);
		return toAdd;
	}

	public void addEdge(final Edge edge) {
		int source = edge.getSource();
		this.adjacencyList.get(source).add(edge);
		this.numEdges++;
	}

	public Iterator<Edge> edgeIterator() {
		ArrayList<Edge> edgeList = new ArrayList<Edge>(this.numEdges);
		for (int i = 0; i < this.numNodes; i++) {
			edgeList.addAll(this.adjacencyList.get(i));
		}
		return edgeList.iterator();
	}

	public static class Edge{
		private final int source;
		private final int destination;
		private final double weight;

		public Edge(final int source, final int dest, final double weight) {
			this.source = source;
			this.destination = dest;
			this.weight = weight;
		}

		public int getSource() {
			return this.source;
		}

		public int getDestination() {
			return this.destination;
		}

		public double getWeight() {
			return this.weight;
		}
	}
}
