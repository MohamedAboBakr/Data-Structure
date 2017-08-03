package eg.edu.alexu.csd.datastructure.linkedList.cs60;

//Node class ---> handle data , next_node  pointer and prev_node pointer 
public class noode {
	private Object data ;
	private noode next ;
	private noode prev ;
	
	
	public noode(Object element , noode next , noode prev){
		Set_element(element);
		Set_NextNode(next);
		Set_prevNode(prev);
	}
	
	
	
	// data setter && getter 
	public void Set_element(Object element){
		data = element ;
	}
	
	public Object Get_element(){
		return data ;
	}
	
	
	
	// next setter && getter
	public void Set_NextNode(noode next){
		this.next = next ;
	}
	
	public noode Get_NextNode(){
		return this.next ;
	}
	
	

	// prev setter && getter 
	public void Set_prevNode(noode prev){
		this.prev = prev ;
	}
	
	public noode Get_prevNode(){
		return this.prev ;
	}
	
}
