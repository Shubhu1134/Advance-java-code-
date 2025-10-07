class Test {
    public static void main(String args[]){
        int countEven =0;
        int countOdd=0;
        
        int arr[]= {2,3,4,5,6,3,4,45,67,86,78,45,33,22,44};

        for(int i= 0; i<arr.length; i++){
            if(arr[i]%2 ==0){
                 countEven++;
            }else {
               countOdd++;
            }
        }
        System.out.println("total even numbers: "+ countEven);
        System.out.println("total odd numbers: "+ countOdd);

    }
}