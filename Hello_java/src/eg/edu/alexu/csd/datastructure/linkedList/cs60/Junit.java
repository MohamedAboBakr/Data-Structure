package eg.edu.alexu.csd.datastructure.linkedList.cs60;

import java.util.Random;
import static org.junit.Assert.*;

import org.junit.Test;

public class Junit {

	@Test
	public void test1() {
		
		SingleLinkedList x = new SingleLinkedList();
		
		//Test Insertion in the start
		x.add(5.4);
	    assertEquals("ERROR in add method", 5.4, x.get(0));
	    x.remove(0);
	    
	    //Test insertion in the end
	    x.add(0,5.4);
	    assertEquals("ERROR in add method", 5.4, x.get(0));
	    x.remove(0);
	    
	    //Test For add && size
	    Random rand = new Random();
	    for(int i=0;i<100;i++)
	    {
	    	int randomNum = rand.nextInt((i -0) + 1) + 0;
	    	x.add(randomNum,i);
	    }
	    assertEquals("ERROR in size/add method", 100, x.size());
	    x.remove(0);
	    assertEquals("ERROR in size method", 99, x.size());
	    x.remove(98);
	    assertEquals("ERROR in size method", 98, x.size());
	    x.remove(50);
	    assertEquals("ERROR in size method", 97, x.size());
	    System.out.println(x.size());
	    
	    //Test remove && size
	    for(int i=96;i> -1;i--)
	    {
	    	int randomNum = rand.nextInt((i -0) + 1) + 0;
	    	x.remove(randomNum);
		    assertEquals("ERROR in size method", i, x.size());

	    }
	    
	    //GET
	    for(int i =0;i<10;i++)
	    	x.add(i);
	    for(int i = 0;i<10;i++)
	    	assertEquals("ERROR in get/add method", i, x.get(i)); 
	    
	    //SET
	    for(int i = 0;i<10;i++)
	    	x.set(i,9-i);
	    for(int i = 0;i<10;i++)
	    	assertEquals("ERROR in set/get method", 9-i,(int) x.get(i)); 
	    
	    //CONTAINS
    	for(int i =0;i<10;i++)
    		assertEquals("ERROR in Contains method", true,x.contains(i)); 
    	for(int i=-10;i < 0 ;i++)
    		assertEquals("ERROR in Contains method", false,x.contains(i)); 
    	for(int i=10;i < 20 ;i++)
    		assertEquals("ERROR in Contains method", false,x.contains(i)); 

    	//Sublist
    	SingleLinkedList y = (SingleLinkedList) x.sublist(0,9);
        for(int i = 0;i<10;i++)
   	    	assertEquals("ERROR in sublist method", 9-i,(int) y.get(i)); 
        
        y = (SingleLinkedList) x.sublist(0,4);
        for(int i = 0;i<4;i++)
   	    	assertEquals("ERROR in sublist method", 9-i,(int) y.get(i)); 

        y = (SingleLinkedList) x.sublist(4,9);
        for(int i = 4;i<10;i++)
        	assertEquals("ERROR in sublist method", 9-i,(int) y.get(i-4));
        
        //CLEAR
        y.clear();
    	assertEquals("ERROR in clear method", 0,y.size);
    	
    	//Sublist on one element
    	y.add(1);
    	SingleLinkedList m = (SingleLinkedList) y.sublist(0, 0);
    	assertEquals("ERROR in Sublist method", 1,m.size);
    	assertEquals("ERROR in Sublist method", 1,m.get(0));

	}
	
	@Test(expected=RuntimeException.class)
	public void test2() {
		SingleLinkedList x = new SingleLinkedList();
		x.get(0);
	}
	@Test(expected=RuntimeException.class)
	public void test3() {
		SingleLinkedList x = new SingleLinkedList();
		x.remove(0);
	}
	@Test(expected=RuntimeException.class)
	public void test4() {
		SingleLinkedList x = new SingleLinkedList();
		x.set(0,2);
	}
	@Test(expected=RuntimeException.class)
	public void test5() {
		SingleLinkedList x = new SingleLinkedList();
		x.add(1,0);
	}
	@Test(expected=RuntimeException.class)
	public void test6() {
		SingleLinkedList x = new SingleLinkedList();
		x.sublist(0, 0);
	}
	@Test(expected=RuntimeException.class)
	public void test7() {
		SingleLinkedList x = new SingleLinkedList();
		x.add(1);
		x.get(1);
	}
	@Test(expected=RuntimeException.class)
	public void test8() {
		SingleLinkedList x = new SingleLinkedList();
		x.add(1);
		x.remove(1);
	}
	@Test(expected=RuntimeException.class)
	public void test9() {
		SingleLinkedList x = new SingleLinkedList();
		x.add(1);
		x.set(1,2);
	}
	@Test(expected=RuntimeException.class)
	public void test10() {
		SingleLinkedList x = new SingleLinkedList();
		x.add(1);
		x.sublist(1, 1);	
	}
}