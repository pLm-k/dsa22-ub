package main.datastructures.sort;

import java.util.ArrayList;

public final class ParallelMergeSort<T extends Comparable<T>>{
	
	private static final int DEFAULT_MAX_DEPTH = 3;
	private final int maxDepth;
	
	public ParallelMergeSort(final int maxDepth){
		this.maxDepth = maxDepth;
	}
	
	public ParallelMergeSort() {
		this(DEFAULT_MAX_DEPTH);
	}

	public final void sort(final ArrayList<T> array) throws IllegalArgumentException {
		if(array.size() % (int) Math.pow(2, this.maxDepth) != 0) {
			throw new IllegalArgumentException("Arraysize does not match maxDepth!");
		}
		SortThread thread = new SortThread(array, 0, array.size() - 1);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	};
	
	private final void sort(final ArrayList<T> array, final int lowerBound, final int upperBound){
		if(lowerBound >= upperBound) {
			return;
		}
		
		int splitIndex = (upperBound - lowerBound)/2 + lowerBound;
		SortThread thread1 = new SortThread(array, lowerBound, splitIndex);
		SortThread thread2 = new SortThread(array, splitIndex + 1, upperBound);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		
		this.merge(array, lowerBound, splitIndex, upperBound);
	}
	
	private final void merge(final ArrayList<T> array, final int lowerBound, final int middle, final int upperBound) {
		int leftSize = middle - lowerBound + 1;
		int rightSize = upperBound - middle;
		
		ArrayList<T> left = new ArrayList<T>(leftSize);
		ArrayList<T> right = new ArrayList<T>(rightSize);
		
		for(int i = 0; i < leftSize; i++) {
			left.add(array.get(lowerBound + i));
		}
		for(int i = 0; i < rightSize; i++) {
			right.add(array.get(middle + 1 + i));
		}
		
		int curIndLeft = 0;
		int curIndRight = 0;
		int curIndArr = lowerBound;
		
		while(curIndLeft < leftSize && curIndRight < rightSize) {
			if(left.get(curIndLeft).compareTo(right.get(curIndRight)) <= 0) {
				array.set(curIndArr++, left.get(curIndLeft++));
			} else {
				array.set(curIndArr++, right.get(curIndRight++));
			}
		}


		for(int i = curIndLeft; i < leftSize; i++) {
			array.set(curIndArr++, left.get(i));
		}

		for(int i = curIndRight; i < rightSize; i++) {
			array.set(curIndArr++, right.get(i));
		}
	}
	
	private final class SortThread extends Thread{
		
		private final ArrayList<T> array; 
		private final int lowerBound; 
		private final int upperBound;
		
		public SortThread(final ArrayList<T> array, final int lowerBound, final int upperBound) {
			this.array = array;
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
		}
		
		@Override
		public final void run() {
			ParallelMergeSort.this.sort(this.array, this.lowerBound, this.upperBound);
		}
	}
}
