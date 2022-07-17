package main.datastructures.sort;

import java.util.List;

public class ListSorter {

	/**
	 * selection sort
	 * 
	 * @param <T>  type of elements in list
	 * @param list to be sorted
	 */
	public static <T extends Comparable<T>> void selectionSort(List<T> list) {
		int max;
		for(int i = 0; i < list.size() - 1; i++) {
			max = i;
			for(int j = i + 1; j < list.size(); j++) 
				if(list.get(j).compareTo(list.get(max))>0) 
					max = j;
			swapElements(list, i, max);
		}
		
	}

	/**
	 * insertion sort
	 * 
	 * @param <T>  type of elements in list
	 * @param list to be sorted
	 */
	public static <T extends Comparable<T>> void insertionSort(List<T> list) {
		for(int i = 1; i < list.size(); i++)
			for(int j = i; j > 0; j--) 
				if(list.get(j).compareTo(list.get(j-1))>0) 
					swapElements(list, j, j-1);
				else 
					break;
	}

	/**
	 * bubble sort
	 * 
	 * @param <T>  type of elements in list
	 * @param list to be sorted
	 */
	public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
		for(int i = 0; i < list.size() - 1; i++) 
			for(int j = 0; j < list.size() - 1 - i; j++) 
				if(list.get(j).compareTo(list.get(j+1))<0)
					swapElements(list, j, j+1);
	}
	
	/**
	 * quicksort
	 * 
	 * @param <T> type of elements in list
	 * @param list to be sorted
	 */
	public static <T extends Comparable<T>> void quickSort(List<T> list) {
		quickSort(list,0,list.size()-1);
	}
	
	/**
	 * @param <T> type of elements in list
	 * @param list to be sorted
	 * @param lowerBound of list part
	 * @param upperBound of list part
	 */
	private static <T extends Comparable<T>> void quickSort(List<T> list, int lowerBound, int upperBound) {
		if(upperBound <= lowerBound) {
			return;
		}
		swapElements(list, (upperBound+lowerBound)/2, upperBound);
		int firstElement = lowerBound;
		for(int i = lowerBound; i <= upperBound; i++) {
			if(list.get(i).compareTo(list.get(upperBound)) > 0) {
				swapElements(list, i, firstElement);
				firstElement++;
			}
		}
		swapElements(list, firstElement, upperBound);
		quickSort(list,lowerBound,firstElement-1);
		quickSort(list,firstElement+1,upperBound);
	}
	
	/**
	 * @param <T> type of elements in list
	 * @param list list to swap elements in
	 * @param firstElement to swap
	 * @param secondElement to swap
	 */
	private static <T extends Comparable<T>> void swapElements(List<T> list, int firstElement, int secondElement) {
		T tmpElement = list.get(secondElement);
		list.set(secondElement, list.get(firstElement));
		list.set(firstElement, tmpElement);
		
	}
}
