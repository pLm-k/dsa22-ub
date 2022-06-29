package main.datastructures.algortihms;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import main.datastructures.WeightedGraph;



public class MinimalSpanningTree{
	
	public static Set<WeightedGraph.Edge> kruskal(WeightedGraph graph){
		Set<WeightedGraph.Edge> edges = new HashSet<WeightedGraph.Edge>();
		Map<Long, Set<Long>> nodeSets = new HashMap<Long, Set<Long>> ();
		
		for(long i = 0; i < graph.numberOfNodes(); i++) {
			nodeSets.put(i, new HashSet<Long>());
			nodeSets.get(i).add(i);
		}
		
		PriorityQueue<WeightedGraph.Edge> edgeQ = new PriorityQueue<WeightedGraph.Edge>();
		
		Iterator<WeightedGraph.Edge> edgeIter = graph.edgeIterator();
		while(edgeIter.hasNext()) {
			edgeQ.add(edgeIter.next());
		}
		
		while(edges.size() < graph.numberOfNodes() - 1 && !edgeQ.isEmpty()) {
			WeightedGraph.Edge curEdge = edgeQ.poll();
			long src = curEdge.getSource();
			long dst = curEdge.getDestination();
			if(nodeSets.get(src) != nodeSets.get(dst)) {
				nodeSets.get(src).addAll(nodeSets.get(dst));
				nodeSets.put(dst, nodeSets.get(src));
				edges.add(curEdge);
			}
		}
		
		if(edges.size() < graph.numberOfNodes() - 1) {
			throw new IllegalArgumentException("The graph isnt connected!");
		}
		
		return edges;
	}
}
