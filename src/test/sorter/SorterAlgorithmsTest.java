package test.sorter;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import main.Sorter.Sorter;

import java.util.LinkedList;

public class SorterAlgorithmsTest {
	/**
	 * some simple test for List
	 * 
	 * NOTE: These tests are in no way complete!
	 * 
	 * @author plmil
	 *
	 */
	public class ListTest{
		LinkedList<Integer> testList;
		LinkedList<Integer> sortedTestList;
		
		@BeforeEach
		public void setupList() {
			testList = new LinkedList<Integer>();
			testList.add(12);
			testList.add(1);
			testList.add(8);
			testList.add(122);
			testList.add(14);
			testList.add(352);
			testList.add(19);
			testList.add(1);
			testList.add(77);
			testList.add(-123);
			testList.add(0);
			
			LinkedList<Integer> sortedTestList = new LinkedList<Integer>();
			sortedTestList.addAll(testList);
			sortedTestList.sort((i1,i2) -> {return i2-i1;});
		}
		
		@Test
		public void bubbleSortTest() {
			Sorter.bubbleSort(testList);
			for(int i = 0; i <testList.size(); i++) {
				Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
			}
		}
		
		@Test
		public void insertionSortTest() {
			Sorter.insertionSort(testList);
			for(int i = 0; i <testList.size(); i++) {
				Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
			}
		}
		
		@Test
		public void selectionSortTest() {
			Sorter.selectionSort(testList);
			for(int i = 0; i <testList.size(); i++) {
				Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
			}
		}
		
		@Test
		public void quickSortTest() {
			Sorter.quickSort(testList);
			for(int i = 0; i <testList.size(); i++) {
				Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
			}
		}
	}
}
