package main.datastructures.algortihms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class NFA {
	private final Set<String> alphabet;
	private final ArrayList<HashMap<String, Set<Integer>>> transition;
	private final int endState;
	
	public NFA(final Set<String> alphabet, final ArrayList<HashMap<String, Set<Integer>>> transition, final int endState) {
		this.alphabet = alphabet;
		this.transition = transition;
		this.endState = endState;
	}

	
	public Set<String> getAlphabet() {
		return this.alphabet;
	}
	
	public ArrayList<HashMap<String, Set<Integer>>> getTransition(){
		return this.transition;
	}
	
	public int getEndState(){
		return this.endState;
	}

	private static void addTransition(final ArrayList<HashMap<String, Set<Integer>>> t1, final ArrayList<HashMap<String, Set<Integer>>> t2, 
			Set<String> alphabet, final int offset){
		for(int i = 0; i < t2.size(); i++) {
			HashMap<String,Set<Integer>> map = new HashMap<>();
			for(String transitionString: alphabet) {
				Set<Integer> destinationSet = new HashSet<>();
				if(t2.get(i).containsKey(transitionString)) {
					for(int destinationString: t2.get(i).get(transitionString)) {
						destinationSet.add(destinationString+offset);
					}
				}
				map.put(transitionString, destinationSet);
			}
			t1.add(map);
		}
	}
	
	public static NFA concat(final NFA nfa1, final NFA nfa2) {
		Set<String> alphabet = new HashSet<String>();
		alphabet.addAll(nfa1.getAlphabet());
		alphabet.addAll(nfa2.getAlphabet());
		alphabet.add("");
		
		ArrayList<HashMap<String, Set<Integer>>> transition = new ArrayList<HashMap<String, Set<Integer>>>();
		addTransition(transition, nfa1.getTransition(), alphabet, 0);
		addTransition(transition, nfa2.getTransition(), alphabet, nfa1.getTransition().size());
		
		if(transition.get(nfa1.getEndState()).get("") == null) {
			transition.get(nfa1.getEndState()).put("", new HashSet<Integer>());
		}
		transition.get(nfa1.getEndState()).get("").add(nfa1.getTransition().size());
		
		return new NFA(alphabet, transition, nfa2.getEndState() + nfa1.getTransition().size());
	}
	
	public static NFA disjunction(final NFA nfa1, final NFA nfa2) {
		Set<String> alphabet = new HashSet<String>();
		alphabet.addAll(nfa1.getAlphabet());
		alphabet.addAll(nfa2.getAlphabet());
		alphabet.add("");
		
		ArrayList<HashMap<String, Set<Integer>>> transition = new ArrayList<HashMap<String, Set<Integer>>>();
		
		HashMap<String, Set<Integer>> startState = new HashMap<String, Set<Integer>>();
		startState.put("", new HashSet<Integer>());
		startState.get("").add(1);
		startState.get("").add(nfa1.getTransition().size() + 1);
		transition.add(startState);
		
		addTransition(transition, nfa1.getTransition(), alphabet, 1);
		addTransition(transition, nfa2.getTransition(), alphabet, nfa1.getTransition().size() + 1);
		
		HashMap<String, Set<Integer>> endState = new HashMap<String, Set<Integer>>();
		transition.add(endState);
		
		if(transition.get(nfa1.getEndState() + 1).get("") == null) {
			transition.get(nfa1.getEndState() + 1).put("", new HashSet<Integer>());
		}
		transition.get(nfa1.getEndState() + 1).get("").add(transition.size() - 1);
		
		if(transition.get(nfa2.getEndState() + nfa1.getTransition().size() + 1).get("") == null) {
			transition.get(nfa2.getEndState() + nfa1.getTransition().size() + 1).put("", new HashSet<Integer>());
		}
		transition.get(nfa2.getEndState() + nfa1.getTransition().size() + 1).get("").add(transition.size() - 1);
		
		return new NFA(alphabet, transition, transition.size() - 1);
	}
	
	public static NFA repetition(final NFA nfa) {
		Set<String> alphabet = new HashSet<String>();
		alphabet.addAll(nfa.getAlphabet());
		alphabet.add("");
		ArrayList<HashMap<String, Set<Integer>>> transition = new ArrayList<HashMap<String, Set<Integer>>>();
		addTransition(transition, nfa.getTransition(), alphabet,  0);
		
		HashMap<String, Set<Integer>> endState = new HashMap<String, Set<Integer>>();
		endState.put("", new HashSet<Integer>());
		endState.get("").add(0);
		
		transition.add(endState);
		
		if(transition.get(nfa.getEndState()).get("") == null) {
			transition.get(nfa.getEndState()).put("", new HashSet<Integer>());
		}
		transition.get(nfa.getEndState()).get("").add(transition.size() - 1);
		
		if(transition.get(0).get("") == null) {
			transition.get(0).put("", new HashSet<Integer>());
		}
		transition.get(0).get("").add(transition.size() - 1);
		
		return new NFA(alphabet, transition, transition.size() - 1);
	}
}
