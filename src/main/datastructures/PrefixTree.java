package main.datastructures;

import java.util.HashMap;
import java.util.Set;

public final class PrefixTree{

	private PrefixTreeNode head;
	private int size;

	public void insert(final String word) {
		if(this.head == null) {
			this.head = new PrefixTreeNode(word);
		}
		else {
			this.insert(this.head, word);
		}
		this.size++;
	}


	private void insert(final PrefixTreeNode node, final String word) {
		String currentPrefix = node.getPrefix();
		int indexOfDifference = longestCommonPrefix(currentPrefix, word);

		//whole prefix checked
		if (indexOfDifference == currentPrefix.length()) {
			//whole word checked
			if(indexOfDifference == word.length()) {
				return;
			}
			String edgeString = word.substring(indexOfDifference, indexOfDifference + 1);
			PrefixTreeNode nextNode = node.getNode(edgeString);
			//edgeString is not contained as key
			if (nextNode == null) {
				node.setNext(edgeString, new PrefixTreeNode(word.substring(indexOfDifference + 1)));
			}
			else {
				this.insert(nextNode, word.substring(indexOfDifference + 1));
			}
		}
		//split node
		else {
			PrefixTreeNode restOfPrefixNode = new PrefixTreeNode(currentPrefix.substring(indexOfDifference + 1));
			PrefixTreeNode restOfWordNode = new PrefixTreeNode(word.substring(indexOfDifference + 1));

			//add children to restOfPrefixNode
			for (String edgeString: node.getNext()) {
				restOfPrefixNode.setNext(edgeString, node.getNode(edgeString));
			}

			//update current node
			node.removeChildren();
			node.setNext(word.substring(indexOfDifference, indexOfDifference + 1), restOfWordNode);
			node.setNext(currentPrefix.substring(indexOfDifference, indexOfDifference + 1), restOfPrefixNode);
			node.setPrefix(currentPrefix.substring(0, indexOfDifference));
		}
	}

	public int size() {
		return this.size;
	}

	/**
	 * @param str1
	 * @param str2
	 * @return index off first difference between str1 and str2 (length of common prefix)
	 */
	private static int longestCommonPrefix(final String str1, final String str2) {
		int i = 0;
		for(; i < str1.length() && i < str2.length() && str1.charAt(i) == str2.charAt(i); i++);
		return i;
	}
	
	private final class PrefixTreeNode{
		
		private String prefix;
		private HashMap<String,PrefixTreeNode> edges = new HashMap<String, PrefixTreeNode>();
		
		public PrefixTreeNode(final String prefix) {
			this.prefix = prefix;
		}
		
		public void setPrefix(final String prefix) {
			this.prefix = prefix;
		}

		public String getPrefix() {
			return this.prefix;
		}

		public Set<String> getNext() {
			return this.edges.keySet();
		}
		
		public void setNext(final String string ,final PrefixTreeNode node) {
			this.edges.put(string, node);
		}

		public PrefixTreeNode getNode(final String string) {
			return this.edges.get(string);
		}

		public void removeChildren() {
			this.edges.clear();
		}
	}
}
