
package eg.edu.alexu.csd.datastructure.iceHockey.cs60;

import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;
import java.awt.Point;


public class Implementation implements IPlayersFinder{

    Point[] ans ;
    
    
    char team ;
    int threshold , element_area = 0 , points_num = 0;
    int len_row , len_col ;
    
    int[][] dp , points ;
    char[][] chars ;
    
    // borders of the element
    int[] max_min_row  , max_min_col ;
    
    // variables for sort 
    int[][] numbers;
    int[][] helper;
    int number;
    
    
    @Override
    public  Point[]  findPlayers(String[] photo, int team, int threshold) {
       if(!check_array(photo)){
          // System.out.println("error 1");
           return null ;
       }
       if(!check_int(team) || !check_int_2(threshold)){
          // System.out.println("error 2");
           return null ;
       }
       this.team = (char)(team+'0');
       this.threshold = threshold;
       len_row = photo.length;
       len_col = photo[0].length();
       chars = new char[len_row][len_col];
       dp = new int[len_row][len_col];
       points = new int[len_row*len_col][2];
       fill_chars(photo);
       fill_dp();
       max_min_row = new int[2] ;
       max_min_col = new int[2] ;
       process();
       if(points_num == 0){
           ans = new Point[0];
           return ans ;
       }
       sort(points);
       ans = new Point[points_num];
       fill_points();
       return ans;

      
    }
    
    
  
    
    
     // get element_path
    void process(){
        
        for(int i =  0 ; i < len_row ; i++){
            for(int j = 0 ; j < len_col ; j++){
                if(chars[i][j] == team && dp[i][j] == -1 ){
                                                       // System.out.println(chars[i][j]); 
                    max_min_row[0] = i ;
                    max_min_row[1] = i ;
                    max_min_col[0] = j ;
                    max_min_col[1] = j ;
                    element_area = 0 ;
                    get_element(i,j);
                                                       //  System.out.println("*** " + element_area);
                                                       //  System.out.println((max_min_row[0]*2+1) + "  " + (max_min_row[1]*2+1));
                                                       //  System.out.println((max_min_col[0]*2+1) + "  " + (max_min_col[1]*2+1));
                 if(element_area*4 >= threshold){
                     points[points_num][0] = ((max_min_col[0]*2+1) + (max_min_col[1]*2+1))/2 ;
                     points[points_num][1] = ((max_min_row[0]*2+1) + (max_min_row[1]*2+1))/2 ;
                     points_num ++ ;
                                                       //  System.out.println("Center :-  "  + ((max_min_col[0]*2+1) + (max_min_col[1]*2+1))/2 + "  " + ((max_min_row[0]*2+1) + (max_min_row[1]*2+1))/2);
                 }
            }
        }
        
    }
    }   
    
    void get_element(int i , int j){
                                                        //  System.out.println(i + "     " + j);
        
        if(i >= len_row || j >= len_col) return ;
        if(i < 0 || j < 0 ) return ;
        if(dp[i][j] != -1) return ; 

    if(chars[i][j] == team){
        
        element_area ++ ;
        if(i < max_min_row[0]) max_min_row[0] = i ; 
        if(i > max_min_row[1]) max_min_row[1] = i ;
        
        if(j < max_min_col[0]) max_min_col[0] = j ; 
        if(j > max_min_col[1]) max_min_col[1] = j ;
        
        
            dp[i][j] = 1 ;
            get_element(i-1 , j);
            get_element(i+1 , j);
            get_element(i , j+1);
            get_element(i , j-1);
        }
        else dp[i][j] = 0 ;
     
    }
    
    
    
    
    
    
    
    // intialize 
    void fill_dp(){
        for( int i = 0  ; i < len_row ; i++){
            for( int j = 0 ; j < len_col ; j++){
                dp[i][j] = -1 ;
            }
        }
    }
    void fill_chars(String[] arr){
        
        for(int i = 0 ;  i < arr.length ; i++){
            chars[i] = arr[i].toCharArray();
        }
    }
    
    
    
    
    

    
    
    // validation
    boolean check_int( int n ){
        if(n >=0 && n<= 9) return true;
        return false;
    }
    
    boolean check_int_2(int n){
        if( n < 0 ) return false ;
        return true;
    }
    
    boolean check_array(String[] arr){
        if(arr == null) return false;
        if(arr.length == 0) return false;
        if(arr[0] == null) return false;
        int len = arr[0].length();
        if(len == 0) return false ;
        for( int i = 0  ; i<arr.length ; i++ ){
            if(arr[i] == null ) return false;
            if(arr[i].length() != len) return false;
            if(!check_string(arr[i])) return false ;
        }
        
        return true  ;
    }
    
    boolean check_string(String s){
        for(int i=0 ; i<s.length() ; i++){
            char c  = s.charAt(i);
            if((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {} 
            else return false ;
        }
        return true ;
    }
    
    
    
    
    
    // sort the points
    void sort(int[][] values) {
    numbers = values;
    helper = new int[points_num][2];
    mergesort(0, points_num -1);
  }

  void mergesort(int low, int high) {
  
    if (low < high) {
     
      int middle = low + (high - low) / 2;
      
      mergesort(low, middle);
     
      mergesort(middle + 1, high);
     
      merge(low, middle, high);
    }
  }

    void merge(int low, int middle, int high) {

      for (int i = low; i <= high; i++) {
        helper[i][0] = numbers[i][0];
        helper[i][1] = numbers[i][1];
    }

    int i = low;
    int j = middle + 1;
    int k = low;

    while (i <= middle && j <= high) {
      if ((helper[i][0] < helper[j][0]) || (helper[i][0] == helper[j][0]   && helper[i][1] < helper[j][1])) {
        numbers[k][0] = helper[i][0];
        numbers[k][1] = helper[i][1];
        i++;
      } else {
        numbers[k][0] = helper[j][0];
        numbers[k][1] = helper[j][1];
        j++;
      }
      k++;
    }
    while (i <= middle) {
        numbers[k][0] = helper[i][0];
        numbers[k][1] = helper[i][1];
      k++;
      i++;
    }
    while (j <= high) {
        numbers[k][0] = helper[j][0];
        numbers[k][1] = helper[j][1];
      k++;
      j++;
    }
  }
    
    
    
    
    // fill_point to Point-arr
    void fill_points(){
        for(int i=0 ; i<points_num ; i++){
            ans[i] = new Point(points[i][0] , points[i][1]);
        }
    }
}
