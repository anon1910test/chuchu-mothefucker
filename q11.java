package lab1;

import java.util.Scanner;

public class q11 {
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("enter the center (p,q):");
        int p = scan.nextInt();
        int q = scan.nextInt();
        System.out.print("enter the raiuds:");
        int r = scan.nextInt();

        System.out.print("enter  a point (x,y)");
        int x = scan.nextInt();
        int y = scan.nextInt();

        double distance = Math.sqrt(Math.pow(p-x,2) + Math.pow(q-y,2));
        if (distance > r){
            System.out.print ("the point is not inside the circle");
        }
        else {
            System.err.print("the point is inside the cirlce");
        }
        scan.close();

    }
}
