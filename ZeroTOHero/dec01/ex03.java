class Demo{
    public static void main (String args []){
        try{
            System.out.println(10/0);
        }
        catch(Exception e ){
            System.out.println("error occured !");
        }
        finally{
            System.out.println("no worries it has to be ran ");
        }
    }
}