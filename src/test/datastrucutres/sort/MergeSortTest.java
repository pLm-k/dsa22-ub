package test.datastrucutres.sort;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

import main.datastructures.sort.ParallelMergeSort;

public class MergeSortTest {
	private static int MAX_DEPTH = 10;
	//force duplicate values
	private static int ELEMENT_UPPER_BOUND = (int) Math.pow(2, MAX_DEPTH - 1);
	
	@Test
	public void testWorks(){
		int maxDepth = MAX_DEPTH;
		ArrayList<Integer> list2Sort = new ArrayList<Integer>();
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		for(int i = 0; i < Math.pow(2, maxDepth); i++) {
			int value2Add = (int) (Math.random() * ELEMENT_UPPER_BOUND);
			list2Sort.add(value2Add);
			sortedList.add(value2Add);
		}
		
		sortedList.sort((o1, o2) -> o1 - o2);
		ParallelMergeSort<Integer> pSort = new ParallelMergeSort<Integer>(maxDepth);
		pSort.sort(list2Sort);
		
		for(int i = 0; i < Math.pow(2, maxDepth); i++) {
			Assert.assertEquals(sortedList.get(i), list2Sort.get(i));
			//System.out.println("Expected: " + sortedList.get(i) + " Actual: " + list2Sort.get(i)); 	//for visualizing
		}
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testException(){
		int maxDepth = MAX_DEPTH;
		ArrayList<Integer> list2Sort = new ArrayList<Integer>();
		
		int size = (int) (Math.random() * Math.pow(2, maxDepth));
		//size =  (int) Math.pow(2, maxDepth); 															//shoud fail with this line
		for(int i = 0; i < size; i++) {
			list2Sort.add((int) (Math.random() * ELEMENT_UPPER_BOUND));
		}
		
		ParallelMergeSort<Integer> pSort = new ParallelMergeSort<Integer>(maxDepth);
		pSort.sort(list2Sort);
	}
}
