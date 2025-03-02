import java.util.Scanner;

public class Two_Sum_Trouble{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] n = sc.nextLine().split(" ");
        int len = Integer.parseInt(n[0]), target = Integer.parseInt(n[1]), idx_1 = 0, idx_2 = len-1;
        String[] temp = sc.nextLine().split(" "); 
        sc.close();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++){
            arr[i] = Integer.parseInt(temp[i]);
        }
        boolean flag = false;
        while (idx_1<idx_2){
            if (arr[idx_1]+arr[idx_2] == target){
                System.out.println((idx_1+1) +" "+ (idx_2+1));
                flag = true;
                break;
            }
            else if (arr[idx_1]+arr[idx_2] < target){
                idx_1 += 1;
            }
            else {
                idx_2 -= 1;
            }
        }
        if (flag == false){
            System.out.println(-1);
        }
    }
}