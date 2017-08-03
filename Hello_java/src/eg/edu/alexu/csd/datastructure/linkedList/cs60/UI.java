package eg.edu.alexu.csd.datastructure.linkedList.cs60;

import java.util.*;
import java.util.Scanner;



public class UI {
      
	static Solver app = new Solver();
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args){
		
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
  "1 - Set a polynomial variable\n" +
 "2 - Print the value of a polynomial variable\n" +
 "3 - Add two polynomials\n" +
 "4 - Subtract two polynomials\n" +
 "5 - Multiply two polynomials\n" +
 "6 - Evaluate a polynomial at some point\n" +
 "7 - Clear a polynomial variable\n" + 
 "====================================================================");
	}
	
	
	static void validate(int choice){
		 switch(choice){
		 case 1:
			 set();
		     break;
		 case 2:
			 print();
			 break;
		 case 3:
			 make_operation(1);
			 break ;
		 case 4:
			 make_operation(2);
			 break;
		 case 5:
			 make_operation(3);
			 break ;
		 case 6:
			 evaluate();
			 break;
		 case 7:
			 clear();
			 break;
		 default:
			 throw new RuntimeException("Invalid Choice");
				 
		 }
	}
	
	
	static void set(){
		System.out.println("Insert the variable name : A , B or C");
		char c = in.nextLine().charAt(0);
		if(!check_char(c)){
			 throw new RuntimeException("Invalid Choice");
		}
		System.out.println("Insert the polynomial terms in the form : \n ( coeff1 , exponent1 ) , ( coeff2 , exponent2 ) , ..");
		String data = in.nextLine();
		
		System.out.println("Polynomial"+ Character.toString(c) +"is set");
		int len = data.length() ;
		List<Integer> list = new ArrayList<>();
		for(int i=0 ; i<len ; i++ ){
			char c2 = data.charAt(i) ;
			if(c2 == '-') throw new RuntimeException("Invalid number(-)");
			if(c2 >= '0' && c2 <= '9') list.add(c2-'0');
		}
		
		
		int[][] points = new int[list.size()/2][2] ;
		
		for(int i=0 ; i< list.size() ; i++){
			 if(i%2 == 0){
				 points[i/2][0] = list.get(i);
			 }
			 else  points[i/2][1] = list.get(i);
		}
		
		app.setPolynomial(c, points);
	}
	
	
	
    static void print(){
		 
    	System.out.println("Insert the variable name : A , B , C or R");
    	char c = in.nextLine().charAt(0);
    	if(!check_char(c) && c != 'R')  throw new RuntimeException("Invalid Choice");
    	
    	if(! check_empty(c)){
    		System.out.println("Variable is empty");
    		return ;
    	}
    	System.out.printf("Value in %c : ",c);
    	app.print(c);
	}

    
    
    static void add(char c1 , char c2){
	    app.add(c1, c2);
  }

    static void subtract(char c1 , char c2){
    	app.subtract(c1, c2);
   }

   static void multiply(char c1 , char c2){
	    app.multiply(c1, c2); 
   }

   
   static void make_operation(int num){
	   System.out.println("Insert first operand variable name : A , B or C");
	   char c1 = in.nextLine().charAt(0);
	   if(!check_char(c1)) throw new RuntimeException("Invalid Choice");
	   if(!check_empty(c1)) throw new RuntimeException("Invalid Choice");
	   
	   
	   System.out.println("Insert second operand variable name : A , B or C");
	   char c2 = in.nextLine().charAt(0);
	   if(!check_char(c2)) throw new RuntimeException("Invalid Choice");
	   if(!check_empty(c2)) throw new RuntimeException("Invalid Choice");
	   
	   if(num == 1)  add(c1 , c2) ;
	   else if(num == 2) subtract(c1 , c2);
	   else multiply(c1 , c2);
	   
	   System.out.print("Result set in R : ");
	   app.print('R');
   }
   
   
   static void evaluate(){
	   System.out.println("Insert first operand variable name : A , B , C or R");
	   char c = in.nextLine().charAt(0);
	   if(!check_char(c) && c != 'R')  throw new RuntimeException("Invalid Choice");
	   if(!check_empty(c)) {
		   System.out.println("Variable Is empty");
		   return ;
	   }
	 System.out.print("Enter the value : ");
	 float value = in.nextFloat() ;
	 float result ;
	 result = app.evaluatePolynomial(c, value) ;
	 System.out.printf("the value of %c  is %f\n" , c , result );
	   
   }

   static void clear(){
	   System.out.println("Insert first operand variable name : A , B or C");
	   char c = in.nextLine().charAt(0);
	   if(!check_char(c)) throw new RuntimeException("Invalid Choice");
	   if(!check_empty(c)) {
		   System.out.println("Variable Is empty ");
		   return ;
	   }
	   
	   if(c == 'A') app.A.clear();
	   else if( c == 'B') app.B.clear();
	   else app.C.clear();
	   
	   System.out.println("Variable " + Character.toString(c) + " is cleared");
  } 
   
   
   
   
   static boolean check_char(char c){
	   if(c != 'A' && c != 'B' && c != 'C')  return false ;
	   return true ;
   }
   
   static boolean check_empty(char c){
	   if(c == 'A' && app.A.isEmpty()) {
   		
   		return false;
   	}
   	
   	if(c == 'B' && app.B.isEmpty()) {
  
   		return false;
   	}
   	if(c == 'C' && app.C.isEmpty()) {
   		
   		return false;
   	}
   	if(c == 'R' && app.R.isEmpty()) {
 
   		return false ;
   	}
   	
   	return true;
   }
}