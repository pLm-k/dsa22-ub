package main.datastructures;

public class AVLTree<T extends Comparable<T>>{

	private volatile AVLNode<T> root;
	private boolean rebalance = false;

	public void insert(T t) {
		if(this.root == null) {
			this.root = new AVLNode<T>(t);
			return;
		}
		this.root = this.insert(this.root, t);
	}

	private AVLNode<T> insert(AVLNode<T> node, T t) {
		//Value already present
		if(node.getValue().compareTo(t) == 0) {
			this.rebalance = false;
			return node;
		} else if(node.getValue().compareTo(t) < 0) {
			return this.insertRight(node, t);
		} else {
			return this.insertLeft(node, t);
		}
	}
	
	private AVLNode<T> insertRight(AVLNode<T> node, T t) {
		AVLNode<T> tmpNode;
		if(node.getRightChild() != null) {
			node.setRightChild(this.insert(node.getRightChild(), t));
			if(this.rebalance) {
				switch(node.getBalance()) {
					case -1: if(node.getRightChild().getBalance() == -1) {
								tmpNode = this.rotateLeft(node);
								tmpNode.getLeftChild().setBalance(0);
							} else {
								int balance = node.getRightChild().getLeftChild().getBalance();
								node.setRightChild(this.rotateRight(node.getRightChild()));
								tmpNode = this.rotateLeft(node);
								tmpNode.getRightChild().setBalance((balance == 1) ? -1: 0);
								tmpNode.getLeftChild().setBalance((balance == -1) ? 1: 0);
							}
							tmpNode.setBalance(0);
							this.rebalance = false;
							return tmpNode;
					case 0:	node.setBalance(-1);
							return node;
					case 1: node.setBalance(0);
							this.rebalance = false;
							return node;
				}
			} else {
				return node;
			}
		} else {
			AVLNode<T> newNode = new AVLNode<T>(t);
			node.setRightChild(newNode);
			node.setBalance(node.getBalance() - 1);
			this.rebalance = node.getBalance() <= -1;
			return node;
		}
		//this will never be reached
		return null;
	}
	
	private AVLNode<T> insertLeft(AVLNode<T> node, T t) {
		AVLNode<T> tmpNode;
		if(node.getLeftChild() != null) {
			node.setLeftChild(this.insert(node.getLeftChild(), t));
			if(this.rebalance) {
				switch(node.getBalance()) {
					case 1: if(node.getLeftChild().getBalance() == 1) {
								tmpNode = this.rotateRight(node);
								tmpNode.getRightChild().setBalance(0); //?
							} else {
								int balance = node.getLeftChild().getRightChild().getBalance();
								node.setLeftChild(this.rotateLeft(node.getLeftChild()));
								tmpNode = this.rotateRight(node);
								tmpNode.getRightChild().setBalance((balance == -1) ? -1: 0);
								tmpNode.getLeftChild().setBalance((balance == 1) ? 1: 0);
							}
							tmpNode.setBalance(0);
							this.rebalance = false;
							return tmpNode;
					case 0:	node.setBalance(1);
							return node;
					case -1: node.setBalance(0);
							this.rebalance = false;
							return node;
				}
			} else {
				return node;
			}
		} else {
			AVLNode<T> newNode = new AVLNode<T>(t);
			node.setLeftChild(newNode);
			node.setBalance(node.getBalance() + 1);
			this.rebalance = node.getBalance() >= 1;
			return node;
		}
		//this will never be reached
		return null;
	}
	
	private AVLNode<T> rotateRight(AVLNode<T> node) {
		AVLNode<T> tmpNode = node.getLeftChild();
		node.setLeftChild(node.getLeftChild().getRightChild());
		tmpNode.setRightChild(node);
		return tmpNode;
	}

	private AVLNode<T> rotateLeft(AVLNode<T> node) {
		AVLNode<T> tmpNode = node.getRightChild();
		node.setRightChild(node.getRightChild().getLeftChild());
		tmpNode.setLeftChild(node);
		return tmpNode;
	}



	
	public AVLNode<T> getRootNode() {
		return this.root;
	}

	private class AVLNode<G extends Comparable<G>>{

		private AVLNode<G> leftChild , rightChild;
		private int balance;
		final private G value;
		
		
		public AVLNode(final G value) {
			this.value = value;
		}

		public G getValue() {
			return this.value;

		}

		public void setLeftChild(final AVLNode<G> left) {
			this.leftChild = left;
		}

		public AVLNode<G> getLeftChild() {
			return this.leftChild;

		}
		
		public void setRightChild(final AVLNode<G> right) {
			this.rightChild =  right;
		}

		public AVLNode<G> getRightChild() {
			return this.rightChild;
		}

		public void setBalance(final int balance) {
			this.balance = balance;
		}

		public int getBalance() {
			return this.balance;
		}
	}
}
