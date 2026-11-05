package lab1;
// labn 1 question 7 
import java.util.Scanner;
public class q7 {
 public static void main(String[] args) {
 
    Scanner scan = new Scanner(System.in);
    System.out.print("enter a intger");
    int num = scan.nextInt();
    if (num % 2 == 0 && num % 3 == 0){
        System.out.print("false");
    }
    else {
        System.out.print ("true");
    }
    

    scan.close();
    
 }   
}
