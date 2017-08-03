/**
 * 
 */
package eg.edu.alexu.csd.datastructure.linkedList.cs60;

import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;

import java.awt.Point;

import eg.edu.alexu.csd.datastructure.linkedList.*;

/**
 * @author chrx
 *
 */
public class Solver implements IPolynomialSolver {


	DoubleLinkedList A = new DoubleLinkedList();
	DoubleLinkedList B = new DoubleLinkedList();
	DoubleLinkedList C = new DoubleLinkedList();
	DoubleLinkedList R = new DoubleLinkedList();
	
	
	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver#setPolynomial(char, int[][])
	 */
	@Override
	public void setPolynomial(char poly, int[][] terms) {
		int len = terms.length;
		int i ;
		for(i=0;i<len;i++)
		{
			if(terms[i][1] < 0)
				throw new RuntimeException("No Negative Exponent allowed !");
			if(i > 0)
				if(terms[i][1] > terms[i-1][1])
					throw new RuntimeException("Not orderes");

		}
		
		switch(poly)
		{
		case 'A':
			A.clear();
			break;
		case 'B':
			B.clear();
			break;
		case 'C':
			C.clear();
			break;
		default :
			throw new RuntimeException("WRONG CHARACHTER");
		}
		
		for(i=0;i<len;i++)
		{
			switch(poly)
			{
			case 'A':
					A.add(new Point(terms[i][0],terms[i][1]));
				break;
			case 'B':
				B.add(new Point(terms[i][0],terms[i][1]));
				break;
			case 'C':
				C.add(new Point(terms[i][0],terms[i][1]));
				break;
			default :
				throw new RuntimeException("WRONG CHARACHTER");
			}
		}

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver#print(char)
	 */
	@Override
	public String print(char poly) {
		
		int i,cz;
		String x = "";
		Point[] arr;
		
		DoubleLinkedList y = get_poly(poly);
		
		cz = y.size();

		if(cz == 0)
		{
			System.out.println(0);
			return null;
		}
		
		arr = new Point[cz];
		 
		 for(i=0;i<cz;i++)
		 {
			 arr[i] =  (Point)y.get(i);
		 }
		 
		 sortpoint(arr, cz);
		 
		 for(i=0;i<cz;i++)
		 {
			 y.set(i,arr[i]) ;
		 }
		 
		for(i=0;i<cz;i++)
		{
			if(((Point)y.get(i)).x  == 0)
				continue;
			
			if(((Point)y.get(i)).x > 0)
				if(i != 0)
					x += "+";
			if(((Point)y.get(i)).x < 0)
				x += "-";
			
			if(((Point)y.get(i)).x > 1)
				x += ((Point)y.get(i)).x;
			
			if(((Point)y.get(i)).x < -1)
				x += ((Point)y.get(i)).x*-1;
			
			if(((Point)y.get(i)).y == 1)
				x += "x";
			
			if(((Point)y.get(i)).y > 1)
				x += "x^" + ((Point)y.get(i)).y;
			
			if(((Point)y.get(i)).y == 0 && ((Point)y.get(i)).x == 1)
				x+= "1";

		}
		
		if(x == "")
			x = null;
		System.out.println(x);
		return x;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver#clearPolynomial(char)
	 */
	@Override
	public void clearPolynomial(char poly) {
		
		switch(poly)
		{
		case 'A':
			if(A == null) throw new RuntimeException("Already Cleared");
			A.clear() ;
			break;
		case 'B':
			if(B == null) throw new RuntimeException("Already Cleared");
			B.clear() ;
			break;
		case 'C':
			if(C == null) throw new RuntimeException("Already Cleared");
			C.clear() ;
			break;
		default :
			throw new RuntimeException("WRONG CHARACHTER");
		}

	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver#evaluatePolynomial(char, float)
	 */
	@Override
	public float evaluatePolynomial(char poly, float value) {
		
		DoubleLinkedList Wanted_polynomial  ;
		switch(poly){
		case 'A':
			if(A.isEmpty()) throw new RuntimeException("It's Cleared");
			Wanted_polynomial = A ;
			break;
		case 'B':
			if(B.isEmpty()) throw new RuntimeException("It's Cleared");
			Wanted_polynomial = B ;
			break;
		case 'C':
			if(C.isEmpty()) throw new RuntimeException("It's Cleared");
			Wanted_polynomial = C ;
			break;
		case 'R':
			if(R.isEmpty()) throw new RuntimeException("It's Cleared");
			Wanted_polynomial =R ;
			break;
		default :
			throw new RuntimeException("WRONG CHARACHTER");
		}
		
		
		float ans = 0 ;
		int size = Wanted_polynomial.size();
		for(int i=0 ; i<size ; i++){
			Point element = (Point)Wanted_polynomial.get(i);
			ans += element.x * Math.pow(value, (double)element.y);
		}
		return ans;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver#add(char, char)
	 */
	@Override
	public int[][] add(char poly1, char poly2) {
		char check1 = check_char(poly1);
		char check2 = check_char(poly2);
		if( check1 == 'O' || check2 == 'O') throw new RuntimeException("WRONG CHARACHTER");
		
		R.clear();
		DoubleLinkedList adder_1 , adder_2 ;
		adder_1 = get_polynomial(check1);
		adder_2 = get_polynomial(check2);
		
		int[] flag = new int[adder_2.size()];
		int l1 = adder_1.size();
		int l2 = adder_2.size();
		String x = "";
		
		for(int i =0;i<l1;i++)
		{
			R.add(adder_1.get(i));
			x += ((Point)adder_1.get(i)).x + "  " + ((Point)adder_1.get(i)).y + "--";
		}
		x += "FFF";
		for(int i =0;i<l2;i++)
		{
			R.add(adder_2.get(i));
			x += ((Point)adder_2.get(i)).x + "  " + ((Point)adder_2.get(i)).y + "--";

		}
		collector(R);
		
		//Sort the array
		int cz,i;
		cz = R.size();
		Point[] arr1 = new Point[cz];
		 
		 for(i=0;i<cz;i++)
		 {
			 arr1[i] =  (Point)R.get(i);
		 }
		 
		 sortpoint(arr1, cz);
		 
		 for(i=0;i<cz;i++)
		 {
			 R.set(i,arr1[i]) ;
		 }
		 //Sort it finish
		
		int size = R.size();
		int[][] arr = new int[size][2];
		
		for(i=0 ; i<size ; i++){
			Point p = (Point)R.get(i);
			arr[i][0] = p.x ;
			arr[i][1] = p.y;
		}
		System.out.println(arr.length);
			return arr ;
	}
	
	public void collector(DoubleLinkedList result)
	{
		int i,j;
		int size = result.size();
		boolean[] arr = new boolean[result.size()];
		int x = 0,y = 0;
		DoubleLinkedList finalList = new DoubleLinkedList();
		for(i=0;i<size;i++)
		{
			if(arr[i] == true)
				continue;
			arr[i] = true;

			Point a1 = (Point)result.get(i);
			x = a1.x;
			y = a1.y;
			
			for(j=i+1;j<size;j++)
			{
				if(arr[j] == true)
					continue;
				
				Point a2 = (Point)result.get(j);
				if(a1.y == a2.y && arr[j] == false)
				{
					x += a2.x;
					arr[j] = true;
				}
			}
			
			Point Result = new Point(x,y);
			finalList.add(Result);
		}
		R.clear();
		R = finalList;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver#subtract(char, char)
	 */
	@Override
	public int[][] subtract(char poly1, char poly2) {
		char check1 = check_char(poly1);
		char check2 = check_char(poly2);
		if( check1 == 'O' || check2 == 'O') throw new RuntimeException("WRONG CHARACHTER");
		
		R.clear();
		DoubleLinkedList pol1 , pol2 ;
		pol1 = get_polynomial(check1);
		pol2 = get_polynomial(check2);
		
		if(poly1 == poly2 ) {
			R.clear();
			int arr[][] = {{0,0}};
			return arr;
		}
		
		int[] flag = new int[pol2.size()];
		int l1 = pol1.size();
		int l2 = pol2.size();
		int flag_ ;
		
		for(int i=0 ; i < l1 ; i++){
			Point p1 = (Point)pol1.get(i);
			flag_ = 0 ;
			for(int j = 0 ; j < l2 ; j++){
				Point p2 = (Point)pol2.get(j);
				if(p1.y == p2.y && flag[j] == 0){
					Point new_point = new Point(p1.x - p2.x , p1.y);
					R.add(new_point);
					flag[j] = 1 ;
					flag_ = 1 ;
					break;
				}
			}
			
			if(flag_ == 0){
				R.add(new Point(p1.x , p1.y));
			}
		}
		
		for(int j = 0 ; j < l2 ; j++){
			if(flag[j] == 0){
				Point p2 = (Point)pol2.get(j);
				R.add(new Point(-1*p2.x , p2.y));
			}
		}
		
		collector(R);
		
		//Sort the array
		int cz,i;
		cz = R.size();
		Point[] arr1 = new Point[cz];
		 
		 for(i=0;i<cz;i++)
		 {
			 arr1[i] =  (Point)R.get(i);
		 }
		 
		 sortpoint(arr1, cz);
		 
		 for(i=0;i<cz;i++)
		 {
			 R.set(i,arr1[i]) ;
		 }
		 //Sort it finish
		
		int size = R.size();
		int[][] arr = new int[size][2];
		
		for(i=0 ; i<size ; i++){
			Point p = (Point)R.get(i);
			arr[i][0] = p.x ;
			arr[i][1] = p.y ;
		}
		
		return arr ;
	}

	/* (non-Javadoc)
	 * @see eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver#multiply(char, char)
	 */
	@Override
	public int[][] multiply(char poly1, char poly2) {
		char check1 = check_char(poly1);
		char check2 = check_char(poly2);
		if( check1 == 'O' || check2 == 'O') throw new RuntimeException("WRONG CHARACHTER");
		
		DoubleLinkedList pol1 , pol2 ;
		pol1 = get_polynomial(check1);
		pol2 = get_polynomial(check2);
		R.clear();
		
		int l1 = pol1.size();
		int l2 = pol2.size();
		int flag_ ;
		
		
		for(int i=0 ; i<l1 ; i++){
			
			Point p1 = (Point)pol1.get(i);
			
			for(int j=0 ; j<l2 ; j++){
				  Point p2 = (Point)pol2.get(j);
				  int new_cof = p1.x * p2.x ;
				  int new_exp = p1.y + p2.y ;
				  
				  if(R.isEmpty()){
					  R.add(new Point(new_cof , new_exp));
				  }
				  else{
					  
					  flag_ = 0 ;
					  int l3 = R.size();
					  for(int k = 0 ; k<l3 ; k++){
						  Point p3 = (Point)R.get(k);
						  if(p3.y == new_exp){
							  flag_ = 1 ;
							  R.set(k, new Point(p3.x + new_cof, p3.y));
							  break ;
						  }
					  }
					  if(flag_ == 0) {
						  R.add(new Point(new_cof , new_exp ));
					  }
				  }
			}
		}

		
		collector(R);
		
		//Sort the array
		int cz,i;
		cz = R.size();
		Point[] arr1 = new Point[cz];
		 
		 for(i=0;i<cz;i++)
		 {
			 arr1[i] =  (Point)R.get(i);
		 }
		 
		 sortpoint(arr1, cz);
		 
		 for(i=0;i<cz;i++)
		 {
			 R.set(i,arr1[i]) ;
		 }
		 //Sort it finish
		 
		int size = R.size();
		int[][] arr = new int[size][2];
		
		for(i=0 ; i<size ; i++){
			Point p = (Point)R.get(i);
			arr[i][0] = p.x ;
			arr[i][1] = p.y ;
		}

		return arr ;
	}
	
	char check_char(char c){
		if(c == 'A') return 'A' ;
		else if ( c == 'B') return 'B' ;
		else if (c == 'C') return 'C' ;
		return 'O' ;
	}
	
	
	DoubleLinkedList get_polynomial(char c){
		switch(c){
		case 'A' :
			if(A.isEmpty())  throw new RuntimeException("Can't get A");
			return A ;
		
		case 'B' :
			if(B.isEmpty()) throw new RuntimeException("Can't get B");
			return B ;
		case 'C' :
			if(C.isEmpty()) throw new RuntimeException("Can't get c");
			return C ;
			default:
				throw new RuntimeException("ERROR");
		}
	}
	
	DoubleLinkedList get_poly(char c){
		switch(c){
		case 'A' :
			return A ;
		case 'B' :
			return B ;
		case 'C' :
			return C ;
		case 'R' :
			return R ;
			default:
				throw new RuntimeException("ERROR");
		}
	}
	
	public void sortpoint(Point[] list , int inc)
	{
		//check if it's empty
		if(list == null)
			return;
		
		int i,j,x,y,pos;
		for(j=0;j<inc;j++)
		{
			x = list[j].x;
			y = list[j].y;
			pos = j;
			Point temp = new Point(list[j]);
			
			for(i=j+1;i<inc;i++)
			{
					if(list[i].y > y)
					{
						pos = i;
						x = list[i].x;
						y = list[i].y;
					}
			}
			
			list[j] = list[pos];
			list[pos] = temp;
		}
	}
	/*
	public static void main (String argc[])
	{
		Solver poly = new Solver();
		int arr1[][] = {{1,1}};
		poly.setPolynomial('A',arr1);
		int[][] try1 = poly.add('A','A');

		for(int i = 0;i<try1.length;i++)
		{
			System.out.println(try1[i][0] + "x^" + try1[i][1]);
		}
		
		String x =  poly.print('R');
		System.out.println(try1.length);


	}
	*/
	

}
