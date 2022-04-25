package test.datastructures;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import main.datastructures.List;

/**
 * some simple test for List
 * 
 * NOTE: These tests are in no way complete!
 * 
 * @author plmil
 *
 */
public class ListTest{
	List<Integer> testList;
	
	@Before
	public void setupList() {
		testList = new List<Integer>();
		for(int i = 10; i < 20; i++) {
			testList.append(i);
		}
		for(int i = 9; i >= 0; i--) {
			testList.prepend(i);
		}
	}
	
	@Test
	public void positionTest() {
		int value = 0;
		for(int listPosition = 0; listPosition < 20; listPosition++) {
			Assertions.assertEquals(value ,testList.getElementAt(listPosition));
			value++;
		}
	}
	
	@Test
	public void removeTest() {
		Assertions.assertEquals(10, testList.removeElementAt(10));
		Assertions.assertEquals(19, testList.getSize());
		int value = 0;
		for(int listPosition = 0; listPosition < 19; listPosition++) {
			if(listPosition == 10) {
				value++;
			}
			Assertions.assertEquals(value ,testList.getElementAt(listPosition));
			value++;
		}
	}
}
