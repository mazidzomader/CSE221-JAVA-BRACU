import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Array_Reverse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalElements = Integer.parseInt(st.nextToken());
        int reverseStart = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String[] second = new String[totalElements];
        for (int i = 0; i < totalElements; i++) {
            second[i] = st.nextToken();
        }
        
        for (int i = 0; i < (totalElements / 2); i++) {
            String temp = second[i];
            second[i] = second[totalElements - i - 1];
            second[totalElements - i - 1] = temp;
        }
        
        for (int i = totalElements - reverseStart; i < totalElements; i++) {
            System.out.print(second[i] + " ");
        }
    }
}
