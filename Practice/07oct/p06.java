import java.util.Scanner;

class Test{
    public static void main(String args[]){

    Scanner sc = new Scanner(System.in);
    System.out.println(" enter the size of an array :" );
      int n = sc.nextInt();
    int arr []= new int[n];

     System.out.println(" enter "+n+" numbers of elements:");
      for (int i =0 ; i<n; i++){
       arr[i]= sc.nextInt();
      }

      boolean istrue= true ;

        for(int i =0 ; i<n; i++){
          
          if(arr[i]<arr[i+1])
          istrue = false ; 
          break ;
        
        }
        

        if (is) {
            System.out.println("Array is sorted.");
        } else {
            System.out.println("Array is not sorted.");
        }

        System.out.println("Array elements:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}