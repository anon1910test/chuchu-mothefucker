package lab1;
import  java.util.Scanner;
public class q1 {

    public static void main(String[] args) {
        // calculating the area of a  circle using Pi as a constant 
        Scanner input = new Scanner (System.in);
        System.out.print("enter the radius of the circle:");
        double radiuds = input.nextDouble();
        final double PI = 3.1416 ;
        double result = PI * radiuds * radiuds ;

        System.out.print("the area of the circle is " + result);

        input.close();
    }
}