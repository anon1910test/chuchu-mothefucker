package lab1;
import java.util.Scanner;

public class q8 {
    public static void main (String[] args){
        Scanner scan = new Scanner (System.in);
        System.out.print("enter a char:");
        char Ch = scan.next().toLowerCase().charAt(0);
        switch (Ch) {
            case 'a':
                System.out.print("vowel.");
                break;
            case 'e':
                System.out.print("vowel.");
                break;
            case 'i':
                System.out.print("vowel.");
            case 'o':
                System.out.print("vowel.");
                break ;
            case 'u':
                System.out.print("vowel.");
                break;        
            default:
                System.out.print ("consotent.");
                break;
        }
        scan.close();

    }
}
