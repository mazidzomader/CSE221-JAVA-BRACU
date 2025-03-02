import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Two_Sum_Trouble {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[len];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int idx_1 = 0, idx_2 = len - 1;
        boolean flag = false;
        while (idx_1 < idx_2) {
            if (arr[idx_1] + arr[idx_2] == target) {
                System.out.println((idx_1 + 1) + " " + (idx_2 + 1));
                flag = true;
                break;
            } else if (arr[idx_1] + arr[idx_2] < target) {
                idx_1 += 1;
            } else {
                idx_2 -= 1;
            }
        }
        
        if (!flag) {
            System.out.println(-1);
        }
    }
}
