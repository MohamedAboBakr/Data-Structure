package eg.edu.alexu.csd.datastructure.stack.cs60;

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;

public class Application implements IExpressionEvaluator{

	Stack stack = new Stack();
	@Override
	public  String infixToPostfix(String expression) {
		
		int len = expression.length();
		boolean b = Check_expression(expression , len , stack);
		if(b == false){
			throw new RuntimeException("Invalid Expression");
		}
		
		stack = new Stack();
		StringBuilder result = new StringBuilder(); 
		for(int i=0 ; i<len ; i++){
			char c = expression.charAt(i) ;
			if(c == ' ') continue ;
		    if(c == '(') {
		    	stack.push('(') ;
		    	continue ;
		    }
		    
		    if (c == ')') {
		    	char peek = (char)stack.peek();
		    	while(!stack.isEmpty() && peek != '('){
		    		result.append(peek) ;
		    		stack.pop();
		    		peek = (char)stack.peek();
		    	}
		    	stack.pop();
		    	continue ;
		    }
		    
		    
		    
		    if(c == '*' || c == '/') {
		    	if(!stack.isEmpty()){
		    		char peek = (Character) stack.peek();
		    		if(peek == '/' || peek == '*'){
		    			result.append(peek) ;
		    			stack.pop();
		    		}
		    	}
		    	stack.push(c) ;
		    }

		    else if(c == '+' || c == '-'){
		    	if(stack.isEmpty()){
		    	
		    		stack.push(c);
		    	}
		    	else{
		    		char peek = (Character) stack.peek();
		    		if(peek != '('){
		    			result.append(peek) ;
		    			stack.pop();
		    		}
		    		
		    		stack.push(c) ;
		    	}
		    }
		    
		    else if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')){
		    	result.append(c) ;
		    	if(!stack.isEmpty()){
		    	char peek = (Character) stack.peek();
	    		if(peek == '/' || peek == '*'){
	    			result.append(peek) ;
	    			stack.pop();
	    			
	    		}
		    	}
		    }
		}
		
		
		if(!stack.isEmpty()){
    		char peek = ' ';
    		while(!stack.isEmpty() && peek != '('){
    			peek = (char)stack.peek();
    			result.append(peek) ;
	    		stack.pop();
	    		
	    	}
	    	
    	}
		
		StringBuilder  new_result = new StringBuilder ();
		for(int i=0 ; i< result.length(); i++){
			
			if(i > 0) new_result.append(" ") ;
			new_result.append(result.charAt(i));
		}

      return new_result.toString() ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static boolean Check_expression(String expression , int len , Stack s){
		String new_exp = "" ;
		int style = 0 ;
		/*
		for(int i=0 ; i<len ; i++){
			char c = expression.charAt(i);
			if(c >= '0' && c <= '9'){
				if(style == 2) return false ;
				style  = 1;
			}
			
			else if (c >= 'a' && c <= 'z'){
				if(style == 1) return false ;
				style  = 2; 
			}
		}
		
		if(style == 0) return false ;
		*/
		int open_bracket = 0 ;
		for(int i=0 ; i<len ; i++){
			char c = expression.charAt(i);
			 if(i > 0){
				 char before = expression.charAt(i-1);
				 int kind = char_kind(c) ;
				 int kind_before = char_kind(before);
				 if(kind == 1 ){
					 if(i == len-1){
						                                                      //  System.out.println("test1");
						 return  false ;
					 }
					 if(kind == kind_before) {
						                                                     // System.out.println("test2");
						 return false;
					 }
					 if(before == '('){
						                                                    // System.out.println("test3");
						 return false ;
					 }
					 new_exp += c ;
					                                                               // System.out.println("test1");
				 }
				 
				 else if( kind == 4){
					 if(c == '(' && (kind_before == 2 || kind_before == 3)){
						                                                  // System.out.println("test4");
						 return false;
					 }
					 if(c == ')' && kind_before ==1){
						                                                  // System.out.println("test5");
						 return false ;
					 }
					 if(c == '(' && before == ')'){
						                                                 // System.out.println("test6");
						 return false ;
					 }
					 if(c == ')' && before == '('){
						                                                 //  System.out.println("test7");
						 return false ;
					 }
					 if(c == '(') open_bracket ++ ;
					 else if(c == ')') {
						 if(open_bracket == 0) {
							                                            // System.out.println("test8");
							 return false ;
						 }
						 open_bracket -- ;
					 }
					 
					 new_exp += c ;
				 }
				 else if(c == ' '){
					 
					 while(i+1<len && expression.charAt(i+1) == ' '){
						 i++ ;
					 }
					 if(i<len){
						 
						 char next = expression.charAt(i+1) ;
						 int next_kind = char_kind(next);
						 if(kind_before == next_kind && kind_before !=4){
							                                         // System.out.println("test9");
							 return false ;
						 }
						 if( before == '(' && next ==')'){
							                                        //  System.out.println("test10");
							 return false ;
						 }
						 if( before == ')' && next =='('){
							                                       // System.out.println("test11");
							 return false ;
						 }
						 if( before == '(' &&  next_kind == 1){
							                                           // System.out.println("test12");
							 return false ;
						 }
						 if(kind_before == 1 && next == ')'){
							                                         //  System.out.println("test13");
							 return false ;
						 }
						 if(kind_before == ')' && (next_kind == 2 || next_kind == 3)){
						                                      //	 System.out.println("test14");
							 return false ;
						 }
						 if((kind_before == 2 || kind_before == 3) && next == '('){
							                                        //  System.out.println("test15");
							 return false ;
						 }
					 }
					 
					 else{
						 if(kind_before == 1){
							                                        // System.out.println("test16");
							 return false ;
						 }
					 }
				                                                                  //	 System.out.println("test2");
					 new_exp += ' ' ;
				 }
				 
				 else if(kind == 2){
					 new_exp += c ;
			                                                                    	//	 System.out.println("test3");
				 }
				 else if(kind == 3){
					 if(kind_before == 3) return false ;
					 new_exp += c ;
				 }
				 else return false ;
			 }
			 
			 else {
		         
				 //	 System.out.println("test4-1");
				 if(c == ' '){
				 while(i<len && expression.charAt(i) == ' '){
					 i++ ;
				 }
				}
				 if(i<len){
					 char c2 = expression.charAt(i) ;
					 if(c2 == '('){
						 open_bracket ++ ;
						new_exp += c2 ;
					 }
					 else if(char_kind(c2) == 1){
						                                                           // System.out.println("test18");
						 return false ;
					 }
					 else if(char_kind(c2) == 2 || char_kind(c2) == 3){
						 new_exp += c2 ;
					                                                                     	// System.out.println("test4-2");
					 }
					 else {
						                                                          // System.out.println("test19");
						 return false  ;
					 }
					 
				 }
			 }
		}
		
		if(open_bracket != 0){
			                                                                     // System.out.println("test20");
			return false ;
		}
		 // Stack s = new Stack();
		int len2 = new_exp.length() ;
		for(int i= len2-1  ; i >= 0 ; i--){
			char curr = new_exp.charAt(i);
			if(char_kind(curr) == 2){
				int num = 0 ;
				int power = 0 ;
				// System.out.println("ss " + i);
				while(i >= 0 && char_kind(curr) == 2){
				// 	System.out.println("ff " + i);
					num += (curr - '0')*(int)Math.pow(10, power);
					i-- ;	
					if(i>=0) curr = new_exp.charAt(i);
					power ++ ;
					
				}
				i++ ;
			//	System.out.println(num);
				
			s.push(String.valueOf(num));
			}
			
			else {
				s.push(String.valueOf(curr));
			}
		}
		
		
		
		/* while(!s.isEmpty()){
			System.out.println(s.pop().toString());
		}  */
		
		return true ;
	}
	
	static int char_kind(char c){
		if(c == '+' || c == '-' || c == '*' || c == '/' ) return 1 ;
		else if	(c == ')' || c == '(') return 4 ;
		else if ( (c >= '0' && c <= '9') ) return 2 ;
		else if (c >= 'a' && c <= 'z') return 3 ;
		return 0 ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int evaluate(String expression) {
	   if(expression == null ) throw new RuntimeException("empty Expression");
	   if(expression.isEmpty()) throw new RuntimeException("empty Expression");
	    int flag1 = 0 , flag2 = 0 ;
		stack = new Stack();
		int len = expression.length() ;
		
		for(int i = 0 ; i<len ; i++){
			char c = expression.charAt(i);
			if(c == ' ') continue ;
			if( c >= '0' && c <= '9'){
			    flag1 = 1 ;
				int value = 0;
				while(i<len && expression.charAt(i) >= '0' && expression.charAt(i) <= '9'){
					value = value*10 + (expression.charAt(i) - '0') ;
					i++ ;
				}
				i--;
				stack.push((double)value);
			}
			
			else {
				if(stack.isEmpty()) throw new RuntimeException("Invalid Expression");
				double n1 = (double)stack.pop();
				if(stack.isEmpty()) throw new RuntimeException("Invalid Expression");
				double n2 = (double)stack.pop();
				flag2 = 1 ;
				switch(c){
				case '+' :
					stack.push(n1+n2);
					break;
				case '-' :
					stack.push(n2-n1);
					break;
				case '*':
					stack.push(n1*n2);
					break;
				case '/':
					if(n1 == 0) throw new RuntimeException("Can't divide by zero");
					stack.push(n2/n1);
					break;
				default :
					throw new RuntimeException("Invalid Expression");	
				}
			}
		}
		
        if(flag1 != 1 || flag2 != 1) 	throw new RuntimeException("Invalid Expression");	
	   if(stack.size() != 1) return 0 ;
	   double f =  (Double)stack.pop();
	   long l = (long)f;
	   String s = String.valueOf(l);
	   int result = Integer.parseInt(s);
	  // if(result == -2) throw new RuntimeException(expression);
       return result;
	}

}
