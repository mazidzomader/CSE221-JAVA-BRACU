import java.util.Scanner;

public class Longest_Subarray_Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] temp = sc.nextLine().split(" ");
        int n = Integer.parseInt(temp[0]), k = Integer.parseInt(temp[1]), sum = 0, l = 0, count = 0;
        int[] arr = new int[n];
        String[] input2 = sc.nextLine().split(" ");
        sc.close();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input2[i]);
        }
        for (int i = 0; i< n; i++){
            sum += arr[i];
            while (sum > k){
                sum -= arr[l];
                l += 1;
            }
            count = Math.max(count, i - l + 1);
        }
        System.out.println(count);
    }
}
