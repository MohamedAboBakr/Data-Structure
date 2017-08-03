package eg.edu.alexu.csd.datastructure.stack.cs60;

import eg.edu.alexu.csd.datastructure.stack.IStack;
import eg.edu.alexu.csd.datastructure.linkedList.cs60.DoubleLinkedList;;


public class Stack implements IStack{

	DoubleLinkedList stack = new DoubleLinkedList();
	@Override
	public void add(int index, Object element) {
		stack.add(index, element) ;
	}

	@Override
	public Object pop() {
		if(stack.isEmpty()) throw new RuntimeException("Empty Stack");
		Object obj = stack.Last.Get_element();
		stack.remove(stack.size() - 1 );
		return obj ;
	}

	@Override
	public Object peek() {
		if(stack.isEmpty()) throw new RuntimeException("Empty Stack");
		Object obj = stack.Last.Get_element();
		return obj ;
	}

	@Override
	public void push(Object element) {
		stack.add(element);
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public int size() {
		return stack.size();
	}

}
