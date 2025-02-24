import java.util.Scanner;

public class Bubble_Sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int first = Integer.parseInt(sc.nextLine());
        String temp = sc.nextLine();
        sc.close();
        String[] second = temp.split(" ");
        for (int i = 0; i < first-1; i++){
            boolean flag = false;
            for (int j = 0; j < first-1-i; j++ ){
                if (Integer.parseInt(second[j]) > Integer.parseInt(second[j+1])){
                    flag = true;
                    temp = second[j]; 
                    second[j] = second[j+1];
                    second[j+1] = temp;
                }
            }
            if (!flag){
                break;
            }
        }
        for (int i = 0; i < first; i++ ){
            System.out.print(second[i]+" ");
        }
    }
}
