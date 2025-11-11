import java.util.Scanner;


class CheckPrimeNo{
    public static void main(String args[]){

    int count =0; 
    System.out.println("Enter the number ");

    Scanner sc = new Scanner(System.in);
    int n =sc.nextInt();
    
    for(int i=1; i<=n; i++ ){
        if(n%i==0){
            count++;
        }
        else{
            break;
        }
    }

    if(count >=2){
        System.out.println(n+": is not a prime number ");
    }
    else{
        System.out.println(n+": IS A PRIME NUMBER ");
    }

 }

}
