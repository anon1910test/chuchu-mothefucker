package lab1;
import java.util.Scanner;
public class q6 {
    //determinign even or odd in Java ;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("enter a number:");
        int num = scan.nextInt();
        if (num %2 == 0){
            System.out.print("the number is even");
        }
        else {
            System.out.print("the number is odd");
          
        }
        scan.close();
    }
}
