package eg.edu.alexu.csd.datastructure.linkedList.cs60;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;





// doubly_LnLt handle operations on nodes and contain the head of the linkedList
public class DoubleLinkedList implements ILinkedList{

    // initialize size_var that update size of LinkedList after each operation
	public  int size = 0 ;    // empty LnLt
	
    // initialize the head of the linkedList to null 
	public noode head = null ;
	public noode Last = null ;
	
	
	@Override
	public void add(int index, Object element) {
		if(index > size || index < 0) {  
			throw new RuntimeException("Invalid Index"); 
		}
		
		else {
			
			noode new_node = new noode(element , null , null);
			
			
			if(index == size){
				add(element);
				return;
			}
			
			else if(index == 0){
				head.Set_prevNode(new_node);
				new_node.Set_NextNode(head);
				head = new_node ;
			}
	
			
			else {
		
			noode Curr_node = head ;
			int count = 0 ;
			while(count < index-1){
				Curr_node = Curr_node.Get_NextNode() ;
				count++ ;
			}
			
			Curr_node.Get_NextNode().Set_prevNode(new_node);
			new_node.Set_NextNode(Curr_node.Get_NextNode());
			new_node.Set_prevNode(Curr_node);
			Curr_node.Set_NextNode(new_node);
	      }
			
			size++;
		}
		
	}

	@Override
	public void add(Object element) {
		 if(head == null ){
			 head = new noode(element , null , null );
			 Last = head ;
		 }
		 else {
			 
			 noode new_node = new noode(element , null , Last);
			 Last.Set_NextNode(new_node);
			 Last = new_node ;
		 }
		 size++;
	}

	
	@Override
	public Object get(int index) {
		if(index >= size || index < 0){
			throw new RuntimeException("Invalid Index"); 
		}
		
		else{
			
			noode Curr_node = head ;
			int count = 0 ;
			while(true){
				if(count == index){
					return Curr_node.Get_element();
				}
				Curr_node = Curr_node.Get_NextNode();
				count ++ ;
			}
		}
		
	}

	@Override
	public void set(int index, Object element) {
		if(index >= size || index < 0){
			throw new RuntimeException("Invalid Index"); 
		}
		
		else {
			noode Curr_node = head ;
			int count = 0 ;
			while(true){
				if(count == index){
					Curr_node.Set_element(element);
					break ;
				}
				Curr_node = Curr_node.Get_NextNode();
				count ++ ;
			}
		}
	}

	@Override
	public void clear() {
		head = null;
		Last = null;
		size = 0 ;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0) return true ;
		return false ;
	}

	
	
	
	@Override
	public void remove(int index) {
		if(index >= size  || index < 0){
			throw new RuntimeException("Invalid Index"); 
		}
		
		else {
			
			if(size == 1){
				head = null;
				Last = null ;
				
			}
			
			else if(index == size-1){
				noode before_last = Last.Get_prevNode() ;
				before_last.Set_NextNode(null);
				Last = before_last ;
			}
			
			else if(index == 0){
				head = head.Get_NextNode();
			}
			
			else{
			     noode Curr_node = head ;
			     int count = 0 ;
			     while(count < index){
			    	 Curr_node = Curr_node.Get_NextNode();
			    	 count++;
			     }
			     
			     Curr_node.Get_NextNode().Set_prevNode(Curr_node.Get_prevNode());
			     Curr_node.Get_prevNode().Set_NextNode(Curr_node.Get_NextNode());
			}
			
			size -- ;
		}
	}

	
	
	
	
	
	@Override
	public int size() {
		return size ;
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		if(fromIndex < 0 || toIndex < 0){
			throw new RuntimeException("Invalid Index"); 
		}
		
		if( fromIndex > toIndex){
			throw new RuntimeException("Invalid Index"); 
		}
		
		if( fromIndex >= size  || toIndex >= size){
			throw new RuntimeException("Invalid Index"); 
		}
		
		DoubleLinkedList new_object = new DoubleLinkedList();

			noode Curr_node = head ;
			int count = 0;
            while(count <= toIndex){
            	if(count >= fromIndex){
            		new_object.add(Curr_node.Get_element()) ;
            	}
            	
            	Curr_node = Curr_node.Get_NextNode() ;
            	count ++;
            }
		
		return new_object;
	}

	@Override
	public boolean contains(Object o) {
		
		noode Curr_node = head ;
		while(Curr_node != null){
			if(Curr_node.Get_element().equals(o)) return true ;
			Curr_node = Curr_node.Get_NextNode();
		}
		return false ;
	}

}