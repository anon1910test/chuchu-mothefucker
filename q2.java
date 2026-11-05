package lab1;
import java.util.Scanner;;
public class q2 {
   // a programmer that converts seconds into hours minitues seconds 
   public static void main (String[] args){
    Scanner input = new Scanner (System.in);
    System.out.print ("enter time in seconds:");
    int s = input.nextInt();
    int h = s / 3600 ;
    int remain_s = s%3600 ;
    int min = remain_s/ 60 ;
    remain_s = remain_s % 60 ;

    System.out.print(h+ " hours " + min + " miniuts " + remain_s + " seconds");

    input.close(); 
   }
}
