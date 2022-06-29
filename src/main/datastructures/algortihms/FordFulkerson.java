package main.datastructures.algortihms;

import java.util.ArrayList;

public class FordFulkerson{
	
	private ArrayList<ArrayList<Integer>> network;
	private ArrayList<ArrayList<Integer>> flow;
	
	public int calculate(ArrayList<ArrayList<Integer>> graph, int s, int t) {	
		this.network = graph;
		this.flow = new ArrayList<ArrayList<Integer>>(this.network.size());
		for(int i = 0; i < this.network.size(); i++) {
			this.flow.add(new ArrayList<Integer>(this.network.size()));
			for(int j = 0; j < this.network.size(); j++) {
				this.flow.get(i).add(0);
			}
		}
		
		int totalFlow = 0;
		int[] currPath = TreeTraversal.dfs(this.network,s);
		while(currPath[t] != -1) {
			int minFlow = Integer.MAX_VALUE;
			for(int i = t; i != s; i = currPath[i]) {
				if(this.network.get(currPath[i]).get(i) < minFlow) {
					minFlow = this.network.get(currPath[i]).get(i);
				}
			}
			for(int i = t; i != s; i = currPath[i]) {
				this.network.get(currPath[i]).set(i, this.network.get(currPath[i]).get(i) - minFlow);
				this.network.get(i).set(currPath[i], this.network.get(i).get(currPath[i]) + minFlow);
				this.flow.get(currPath[i]).set(i, this.flow.get(currPath[i]).get(i) + minFlow);
			}
			totalFlow += minFlow;
			currPath = TreeTraversal.dfs(this.network,s);
		} 
		return totalFlow;
	}

	public ArrayList<ArrayList<Integer>> flow() {
		return this.flow;
	}
	
}
