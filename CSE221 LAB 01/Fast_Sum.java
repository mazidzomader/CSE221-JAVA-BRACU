import java.util.Scanner;

public class Fast_Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < input; i ++){
            int num = Integer.parseInt(sc.nextLine());
            System.out.println((num*(num+1))/2);
        }
        sc.close();
    }
}
