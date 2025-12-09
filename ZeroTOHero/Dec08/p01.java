// Q.3 Write a program to reverse the array.

class Test{
    public static void main(String args[])
    {
        
        int arr[]={1,2,3,4};
       
        
   

       for( int i=0; i<arr.length; i++)
       {
         System.out.println(arr[i]);
       }
         

    System.out.println("---------------------------------");
    System.out.println("Resverse of array ");
      
       int n = arr.length;
      

       for(int i=0; i<n/2; i++){
            int temp = arr [i];
            arr[i]= arr[n-1-i];
            arr[n-1-i]= temp;
        }
      
       
       /* 
       temp =4
       arr[arr.length]=4;



       */
       
    


       for(int i=0; i<n; i++)
       {
         System.out.println(arr[i]);
       }
       
       
          
   }
}