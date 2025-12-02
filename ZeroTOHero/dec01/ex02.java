class Demo{
    public static void main (String args[]){
        try{
            int [] arr = new int [3];
            arr[5]= 10;
        }
        catch(ArrayIndexOutOfBoundsException e ){
            System.out.println(" u have ebntered wrong index to store ur value ");
        }
        catch(Exception e ){
            System.out.println("exception occured ");
        }
    }
}