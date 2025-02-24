import java.util.Scanner;

public class Array_Reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first_line = sc.nextLine();
        String second_line = sc.nextLine();
        sc.close();
        String[] first = first_line.split(" ");
        String[] second = second_line.split(" ");
        for (int i = 0; i < ((Integer.parseInt(first[0])/2)); i++){
            String temp = second[i];
            second[i] = second[second.length-i-1];
            second[second.length-1-i] = temp;
        }
        for (int i = Integer.parseInt(first[0])-Integer.parseInt(first[1]); i < second.length; i++){
            System.out.print(second[i]+ " ");
        }
    }
}
// Time Limit is Exceeding due to trash rule of BRACU. KKP and ARD, you guys are going to hell for changing the policy