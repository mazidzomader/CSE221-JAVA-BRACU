import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Longest_Subarray_Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int sum = 0, l = 0, count = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            while (sum > k) {
                sum -= arr[l];
                l += 1;
            }
            count = Math.max(count, i - l + 1);
        }
        
        System.out.println(count);
    }
}
