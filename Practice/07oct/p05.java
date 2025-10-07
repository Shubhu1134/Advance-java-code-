class Test{
    public static void main(String args[]){
        int temp=0 ; 

        int arr[]= { 1,2,3,4,5,6,7,8,9};
        int n = arr.length;
        
        for(int i =0; i<n;i++){
            System.out.println(arr[i]);
        }

        for(int i = 0; i<n/2; i++){
            temp = arr[i];
            arr[i]= arr[n-i-1];
            arr[n-i-1]=temp;
        }
         System.out.println("\\");
        for(int i =0; i<n;i++){
            System.out.println(arr[i]);
        }

    }
}