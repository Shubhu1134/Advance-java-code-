import java .util.Scanner;

class Test{
    public static void main (String args[]){
     
       Scanner sc = new Scanner(System.in);
      
             System.out.println("press 1 for resisteration");
             	System.out.println("Press 2 for fetch user by id");
				System.out.println("Press 3 for update user");
				System.out.println("Press 4 for delete user");
				System.out.println("Press O for exit");
			
             	System.out.println("Enter Your Choice");



              while(true){
             
          
                int n = sc.nextInt();
                 if(n<5){
                    System.out.println("you have enterd wrong input ");
                }


                switch(n){
                    case 1 : User.resisteration();
                    break;
                    case 2 :User.fetch();
                    break;
                    case 3 : User.update();
                    break;
                    case 4 :User.delete();
                    break;
                    case 0: System.exit(0);
                }
               
       }
    }
}

class User{

   public static void resisteration(){
        System.out.println("resistration completed ");
    }

   public  static void  fetch(){
        System.out.println("data fetched :"+"shubham singh lodhgi is genius ");
    }

   public static void  delete(){
      System.out.println("user deketed successfully ");
    }
    public static void update(){
        System.out.println("user updated successfully ");
    }

}