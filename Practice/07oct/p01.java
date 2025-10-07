class Test {
    public static void main(String args[]){
        int arr []= {23, 4,5,5,6,7,3,2};

        int temp = arr[0];

        for(int i =0; i<arr.length; i++){
            if(temp <arr[i]){
                temp = arr[i];
              
            }
        
        }
        System.out.println(" largest number is "+ temp);
    }
}