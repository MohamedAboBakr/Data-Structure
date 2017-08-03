package eg.edu.alexu.csd.datastructure.stack.cs60;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class UI {
    
	
	static Stack stack = new Stack();
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws IOException{

		
 
		while(true){
			
			choose();
			int ch = in.nextInt();
			in.nextLine();
			validate(ch);
			System.out.println("====================================================================");
		}
		
	
	}
	
	
	
	
	static void choose(){
		
		System.out.println("Please choose an action \n" + 
  "-----------------------\n"+
 "1: Push " +
 "2: Pop" +
 "3: Peek " +
 "4: Get size " +
 "5: Check if empty " +
 "====================================================================");
	}
	
	
	static void validate(int choice){
		 switch(choice){
		 case 1:
			 push();
		     break;
		 case 2:
			
			 break;
		 case 3:
			
			 break ;
		 case 4:
			
			 break;
		 case 5:
			 
			 break ;
		 default:
			 throw new RuntimeException("Invalid Choice");
				 
		 }
	}
	
	static void push(){
		System.out.println("Enter the type of Object to push");
		System.out.println("1- Integer   2- Float  3- Charcater  \n 4- String 5- Boolean");
		int choice = in.nextInt() ;
		in.nextLine();
		Object to_push ;
		
		switch(choice){
		case 1:{
			to_push = (Integer)in.nextInt() ;
			break;
		}
		case 2:{
			to_push = (Float)in.nextFloat() ;
			break;
		}
		case 3:{
			to_push = (Character)in.nextLine().charAt(0) ;
			break;
		}
		case 4:{
			to_push = (String)in.nextLine() ;
			break;
		}
		case 5:{
			to_push = (Boolean)in.nextBoolean() ;
			break;
		}
			default :  throw new RuntimeException("Invalid Choice");
		}
		
		
		stack.push(to_push);
	}
	
	static void pop(){
		Object ob; 
		if(stack.isEmpty()){
			System.out.println("Stack Is empty");
			return ;
		}
		
		ob = stack.pop() ;
		System.out.println(ob.toString());
	}
	
	static void peek(){
		Object ob; 
		if(stack.isEmpty()){
			System.out.println("Stack Is empty");
			return ;
		}
		
		ob = stack.peek() ;
		System.out.println(ob.toString());
	}
	
	
	static void get_size(){
		if(stack.isEmpty()){
			System.out.println("Stack Is empty");
			return ;
		}
		
		System.out.println("the size of stack is " + stack.size());
	}
	
	
	static void check_empty(){
		 boolean b = stack.isEmpty();
		 System.out.printf("Stack Is%s Empty\n",(b == true)? "" : "not");
	}
}
