import java.util.Scanner;
public class Odd_or_Even {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 0; i < num; i++){
            int number = sc.nextInt();
            String result = (number % 2 == 0) ? number+" is an Even number.": number + " is an Odd number.";
            System.out.println(result);
        }
        sc.close();
    }
}
