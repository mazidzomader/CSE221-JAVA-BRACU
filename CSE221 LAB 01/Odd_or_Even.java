import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Odd_or_Even {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(reader.readLine());
            int number = Integer.parseInt(st.nextToken());
            String result = (number % 2 == 0) ? number + " is an Even number." : number + " is an Odd number.";
            System.out.println(result);
        }
    }
}
