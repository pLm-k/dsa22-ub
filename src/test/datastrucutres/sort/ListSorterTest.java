package test.datastrucutres.sort;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import main.datastructures.sort.ListSorter;

import java.util.LinkedList;

/**
 * some simple test for sorting algorithms
 * 
 * NOTE: These tests are in no way complete!
 * 
 * @author plmil
 *
 */
public class ListSorterTest {
	
	LinkedList<Integer> testList;
	LinkedList<Integer> sortedTestList;

	@Before
	public void setupLists() {
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

		sortedTestList = new LinkedList<Integer>();
		sortedTestList.addAll(testList);
		sortedTestList.sort((i1,i2) -> {return i2-i1;});
	}

	@Test
	public void bubbleSortTest() {
		ListSorter.bubbleSort(testList);
		for(int i = 0; i <testList.size(); i++) {
			Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
		}
	}

	@Test
	public void insertionSortTest() {
		ListSorter.insertionSort(testList);
		for(int i = 0; i <testList.size(); i++) {
			Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
		}
	}

	@Test
	public void selectionSortTest() {
		ListSorter.selectionSort(testList);
		for(int i = 0; i <testList.size(); i++) {
			Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
		}
	}

	@Test
	public void quickSortTest() {
		ListSorter.quickSort(testList);
		for(int i = 0; i <testList.size(); i++) {
			Assertions.assertEquals(sortedTestList.get(i), testList.get(i));
		}
	}
}
