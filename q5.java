package lab1;
import java.util.Scanner;
public class q5 {
    public static void main (String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.print("enter temparature in Farenheight:");
        double F = scan.nextDouble();
        double C = (5.0/9)* (F-32);
        System.out.print("the temparature in Celcius cale is :"+ C);

        scan.close();
    }
}
