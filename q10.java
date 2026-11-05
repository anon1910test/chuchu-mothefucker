package lab1;
import java.util.Scanner;
public class q10 {
    public static void main (String[] args){
        Scanner scan = new Scanner (System.in);
        System.out.println("press 0  for USD to TAKA:");
        System.out.println("press 1  taka USD to TAKA:");

        System.out.print("enter your choise(0 or 1)");
        int choise = scan.nextInt();
        if (choise ==1){
            System.out.print ("enter exchange rate (USD to BDT )");
            double exchange_rate = scan.nextDouble();
            System.out.print("enter the ammont of USD:");
            double USD = scan.nextDouble();
            double TAKA = USD * exchange_rate;
            System.out.print(USD +" USD "+ " = "+ TAKA + "TAKA");
        }

        else if (choise == 0){
            System.out.print ("enter exchange rate ( BDT to USD )");
            double exchange_rate = scan.nextDouble();
            
            System.out.print("enter the ammont of taka:");
            double taka = scan.nextDouble();
            double USD = taka / exchange_rate;
            System.out.print(taka +" taka "+ " = "+ USD + "USD");
        }
        else {
            System.out.println("invalid choise.");
        }
        scan.close();
    }
}
