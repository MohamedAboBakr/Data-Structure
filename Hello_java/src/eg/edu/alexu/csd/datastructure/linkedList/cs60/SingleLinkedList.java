package eg.edu.alexu.csd.datastructure.linkedList.cs60;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

class node{
	
	//Vars for data and the pointer
	public Object data;
	public node next = null;
	
	//assign the Object
	public node (Object x)
	{
		data = x;
	}
	
}

public class SingleLinkedList implements ILinkedList {
	
	//The head pointer of the list and the size to keep track
	public node head = null;
	public node last = null ;
	int size = 0;
	
	//Method to add a node in the start of the List
	public void addNodeAtHead(Object x)
	{
		node newnode = new node(x);
		newnode.next = head;
		head = newnode;
		last = head ;
		size++;
	}
	
	@Override
	public void add(int index, Object element) {
		
		//Sadd it to the begin if it's has index 0
		if(index == 0)
		{
			addNodeAtHead(element);
			return;
		}
		//add it in the last
		else if (index == size)
		{
			add(element);
			return;
		}
		//Sanity chceck for index int
		else if(index > size || index < 0)
		{
	         throw new ArithmeticException("You Can't Insert Here");
		}
		
		//"follow" to move around the list ,"newnode" is the new node
		node follow = head;
		node newnode = new node(element);
		
		//move to the wanted node
		int i;
		for(i=1;i<index;i++)
		{
			follow = follow.next;
		}
		
		//add the node to the list by editing the pointers and increase the size by one
		newnode.next = follow.next;
		follow.next = newnode;
		size++;
		
	}

	@Override
	public void add(Object element) {
		
		//create the new node
		node newnode = new node(element);
		
		//Increase the size by one
		size++;
		
		//If the list is empty
		if(head == null)
		{
			head = newnode;
			last = head ;
			return;
		}
		
		//Move to the last node
		node follow = head;
		while(follow.next != null)
		{
			follow = follow.next;
		}
		
		//add the node to the end of the list
		follow.next = newnode;
		last = newnode ;

	}

	@Override
	public Object get(int index) {
		
		//Sanity check for the index
		if(index >= size || index < 0)
	         throw new ArithmeticException("You Can't Get the data here");
		
		//Move to the wanted position
		node follow = head;
		int i;
		for(i=0;i<index;i++)
		{
			follow = follow.next;
		}
		
		//return the Object data
		return follow.data;
		
	}

	@Override
	public void set(int index, Object element) {
		
		//Sanity check
		if(index >= size || index < 0)
		{
	         throw new ArithmeticException("You Can't Insert Here");
		}
		
		//Get the wanted node to be changed
		node follow = head;
		int i;
		for(i=0;i<index;i++)
		{
			follow = follow.next;
		}
		
		//change the data
		follow.data = element;

	}

	@Override
	public void clear() {
		
		//assign the head to null and make the size zero , the Garbage collector will do the rest
		head = null;
		size = 0;

	}

	@Override
	public boolean isEmpty() {
		
		return head==null;
		
	}

	@Override
	public void remove(int index) {
		
		//Sanity check for the index value
		if(index >= size || index < 0)
		{
	         throw new ArithmeticException("You Can't Remove node doesn't exist ");
		}
		//Remove the first node
		if(index == 0)
		{
			head = head.next;
			size--;
			return;
		}
		
		//Get the node before the one we want to delete it
		node follow = head;
		int i;
		for(i=1;i<index;i++)
		{
			follow = follow.next;
		}
		node to = follow.next; // the one we want to delete it
		follow.next = to.next; // delete it by drop it from the pointer 
		
		//decrease the size by one
		size--;

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		
		node follow = head; // Var to move aroung the original list
		SingleLinkedList x = new SingleLinkedList(); // the new Linked list which will return back
		
		//Sanity check for the Indexs values
		if(fromIndex < 0 || toIndex >= size || fromIndex >= size || toIndex < 0 || fromIndex > toIndex)
	         throw new ArithmeticException("You Can't BUILD nodes doesn't exist ");
		
		//move to the first node
		int i;
		for(i=0;i<fromIndex;i++)
			follow = follow.next;
		
		//Build the new List 
		for(i=fromIndex;i<=toIndex;i++)
		{
			x.add(follow.data);
			follow = follow.next;
		}
		
		//Return the new list
		return x;
	}

	@Override
	public boolean contains(Object o) {

		//move around the whole list by "follow"
		node follow = head;
		while(follow != null)
		{
			//IF you find the data return true
			if(follow.data.equals(o))
				return true;
			
			follow = follow.next;
		}
		
		//Else return false
		return false;

	}
	
	public void print()
	{
		//Move around the whole List and print the data in order // for Debugging purpose
		node follow = head;
		while(follow != null)
		{
			System.out.println(follow.data);
			follow = follow.next;
		}
	}

	
	/*
	public static void main(String[] args) 
	{
		SingleLinkedList x = new SingleLinkedList();
		
		x.add(0,3);
		x.add(0,2);

		x.add(0,1);
		x.add(2,31);
		x.add(2,33);
		x.add(0,0);
		

		SingleLinkedList y = new SingleLinkedList();
		x.print();
		System.out.println("BREAK");

		
		y = (SingleLinkedList) x.sublist(1,4);
		y.print();
		System.out.println("BREAK");
		
		System.out.println(y.size());
	
	}
	*/
	
	
	
}
