package lab1;
import java.util.Scanner;
public class q3 {
    public static void  main (String [] args ){

        Scanner input = new Scanner(System.in);
        System.out.print( "enter time in minitue:");
        int min = input.nextInt();
        int years = min /525600 ;
        int r_min = min %  525600 ;
        int days = r_min / 1440 ;
        r_min =r_min %1140 ;

        System.out.print(years + " years " + days + " days ");
    
        input.close();
    }
}
