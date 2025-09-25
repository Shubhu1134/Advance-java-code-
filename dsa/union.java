class Test{
    public static void main(String args[])
    {
        int[] arr1={1,2,2,3,3,3,5,5};
        int[] arr2={1,2,3,4,5};
        int[] union=new int[arr1.length+arr2.length];
        arr1[0]=union[0];
        for(int i=0;i<arr1.length;i++)
        {
            
            if(i>0 && arr1[i]>union[i-1]);
            {
                arr1[i]=union[i];
            }
        }
        for(int i:union)
        {
            System.out.println(i+" ,");
        }
    }
}