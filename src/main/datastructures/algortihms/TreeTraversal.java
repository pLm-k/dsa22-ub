package main.datastructures.algortihms;

import java.util.ArrayList;

public class TreeTraversal {
	
	private static int NOT_VISITED = -1;
	private static int START_NODE = -2;
	
	public static int[] dfs(ArrayList<ArrayList<Integer>> weights, int s) throws IllegalArgumentException{
		int[] pi = new int[weights.size()];
		
		for(int i = 0; i < pi.length; i++) {
			pi[i] = NOT_VISITED;
		}
		
		pi[s] = START_NODE;
		
		visit(weights, s, pi);
		
		pi[s] = NOT_VISITED;
		
		return pi;
	}
	
	private static void visit(ArrayList<ArrayList<Integer>> weights, int s, int[] pi) {
		for(int i = 0; i < weights.get(s).size(); i++) {
			if(pi[i] != START_NODE && pi[i] == NOT_VISITED && weights.get(s).get(i) > 0) {
				pi[i] = s;
				TreeTraversal.visit(weights, i, pi);
			}
		}
	}
}
