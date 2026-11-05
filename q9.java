package lab1;

import java.lang.Math;
import java.util.Scanner;
public class q9 {
    public static void main (String[] args){

    Scanner scan = new Scanner(System.in);
    System.out.print("enter the coeffiecents of the quadratic eequation:");
    int a,b,c ;
    a = scan.nextInt();
    b= scan.nextInt();
    c = scan.nextInt();
    
    double discriminent = (b*b)- 4*a*c;
    if  (discriminent == 0){
        System.out.println("this euation has only 1 root.");
        double r1 =(-b+Math.sqrt(discriminent))/(2*a);
        System.out.print(r1);

    }
    else if (discriminent >0){
        System.out.println("this euation has 2 root.");
        double r1 =(-b+Math.sqrt(discriminent))/(2*a);
        double r2 =(-b-Math.sqrt(discriminent))/(2*a);
        System.out.print(r1);
        System.out.print(r2);

    }
    else {
        System.out.println("this equations has no real roots");
    }
    scan.close();

    }
}
