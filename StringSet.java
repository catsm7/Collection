/**
* StringSet.java
* @author Catalina Sanchez-Maes
* Janurary 30th, 2019
* CS 272
*/

public class StringSet{//class open
   private String[] data;
   private int capacity;

      public StringSet(){
         capacity =  2;
         data = new String[capacity];
      }//end constructor
      
      
      /**		
	   *	@param	_capacity	
	   *	 The amount of spaces available to store all the string values
	   *	@precondition 
      *   must be a positive value	
	   */	
      public StringSet(int _capacity){
         capacity = _capacity;
         data = new String[capacity];
      }//end constructor
      
      
      /**		
	   *	@param	obj	
	   *	 follows the precondition
	   *	@precondition 
      *   should not be a null object and must be an instance of StringSet
	   */	
      public StringSet(Object obj){
         if (obj != null){
            if (obj instanceof StringSet){
               StringSet candidate = (StringSet) obj;
               capacity = candidate.capacity;
               data = candidate.data;
            }else{
               return;
            }//end inner else
         }else{
            return;
         }//end outer else
      }//end copy constructor
      
      public int size(){
         int currSize = 0;
         for(int i = 0; i < data.length; i++){
            if(data[i] != null){
               currSize++;
            }//end if
         }//end for
         return currSize;
      }//end size

      public int capacity(){
         return capacity;
      }//end capacity
      
      
      /**		
	   *	@param	a	
	   *	 The string to be put into the first available array space 
	   *	@precondition
      *   the String value should not be null
      */	
      public void add(String a){
         if(!contains(a) && a != null){
            ensurecapacity(2* data.length);
            data[size()] = a;
         }//end if
      }//end add
      
      public boolean contains(String a){
         if(a == null){
            return false;
         }
         for(int i = 0; i < data.length; i++){
            if(a.equals(data[i])){
               return true;
            }//end if
         }//end for
            return false;
      }//end contains
      
      public boolean remove(String a){
         if(a == null){
            return false;
         }//end if
         for(int i = 0; i < capacity; i++){
            if(a.equals(data[i])){
               data[i] = data[size() - 1];
               data[size() - 1] = null;
               return true;
            }//end if
         }//end for
         return false;
      }//end remove
      
      
      /**		
	   *	@param	Integer	
	   *	 sets the collection to a higher capacity
	   *	@precondition 
      *   the integer parameter should be positive
      */
      private void ensurecapacity(int minimumcapacity){
         if (capacity < minimumcapacity){
            capacity = minimumcapacity;
            String[] enlargedData = new String[minimumcapacity];
            for (int i = 0; i < data.length; i++){
               enlargedData[i] = data[i];
            }//end for
            data = enlargedData;
         }//end if 
      }//end ensurecapacity
      
      private void printSet(){
         for(int i = 0; i < size(); i++){
            System.out.print(data[i] + ", ");
         }//end print for loop
         System.out.println();
      }//end printSet
      
      
      /**	
	   *	@param	a	
	   *	 adding a string to an ordered collection instance
	   *	@precondition
      *   the String value should not be null and the values in the collection's string array are already ordered accordingly
      */
      public void addOrdered(String a){
         if (a == null){
            return;
         }//end if
         String tmp;
         add(a);
         for(int i = 0; i < size(); i++){//leftmost potential swap
            for(int j = i + 1; j < size(); j++){//indexes after
               for(int k = 0; k < data[i].length() && k < data[j].length(); k++){//index of the char you go through
                  System.out.printf("%c tested against %c, %d\n", data[i].charAt(k), data[j].charAt(k), k);
                  if(data[i].charAt(k) == data[j].charAt(k) && data[i].length() - 1 != k && data[j].length() - 1 == k){
                     tmp = data[i];
                     data[i] = data[j];
                     data[j] = tmp;
                     break;
                  }//end if both
                  else if(data[i].charAt(k) != data[j].charAt(k) && data[i].charAt(k) > data[j].charAt(k)){
                     tmp = data[i];
                     data[i] = data[j];
                     data[j] = tmp;
                     break;
                  }
                  else if (data[i].charAt(k) != data[j].charAt(k) && data[i].charAt(k) < data[j].charAt(k)){
                     break;
                  }//end if
               }//end for k
            }//end for j
         }//end for i
      }//end addOrdered
      
   public static void main(String[] args) {//main open
      //Test default constructor & making sure capacity/capacity are the same values
      StringSet s1 = new StringSet();
      System.out.println("s1 Data: " + s1.data.length);
      System.out.println("s1 capacity: " + s1.capacity);
      
      //Testing the parameter constructor & making sure capacity/capacity are the same values
      StringSet s2 = new StringSet(10);
      System.out.println("s2 Data: " + s2.data.length);
      System.out.println("s2 capacity: " + s2.capacity);
      
      //Testing size() supposed to be 0
      System.out.println("s1 size: " + s1.size());
      System.out.println("s2 size: " + s2.size());
      
      //Testing capacity()
      System.out.println("s1 capacity method: " + s1.capacity());
      System.out.println("s2 capacity method: " + s2.capacity());
      
      //Testing add method
      s1.add("Adam");
      s1.add("Lucy");
      s1.printSet();
      s1.add("Ricardo");
      s1.printSet();
      System.out.println("s1 size: " + s1.size());
      System.out.println("s1 vals: " + s1.capacity);
      s1.add("Lucy");
      s1.printSet();
      System.out.println("s1 size: " + s1.size());
      System.out.println("s1 vals: " + s1.capacity);
      String s3 = null;
      s1.add(s3);
      s1.printSet();
      System.out.println("s1 size: " + s1.size());
      System.out.println("s1 vals: " + s1.capacity);
      
      //Testing remove method
      System.out.println("Remove: " + s1.remove(s3));//false
      s1.printSet();
      System.out.println("Remove: " + s1.remove("Lucy"));//true
      s1.printSet();
      System.out.println("Remove: " + s1.remove("Alex"));//false
      
      //Testing the copy constructor
      s2.printSet();
      s2 = new StringSet(s1);
      s2.printSet();
      
      //Testing ensurecapacity
      System.out.println(s2.capacity());
      s2.ensurecapacity(14);
      System.out.println(s2.capacity());
      s2.ensurecapacity(16);
      System.out.println(s2.capacity());
      s2.ensurecapacity(18);
      System.out.println(s2.capacity());
      
      //Testing addOrdered
      s2.addOrdered("Hillary");
      s2.printSet();
      StringSet s4 = new StringSet();
      s4.addOrdered("Adam");
      s4.addOrdered("Adams");
      s4.printSet();
      s4.addOrdered("Adam");
      s4.printSet();
      s4.addOrdered(s3);
      s4.printSet();
     
   }//end main
}//end class