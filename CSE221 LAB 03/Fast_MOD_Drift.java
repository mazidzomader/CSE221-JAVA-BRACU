import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fast_MOD_Drift {

    public static long fastModularExponentiation(long base, long exponent) {
        long result = 1;
        base = base % 107;
        
        while (exponent > 0) {
            if (exponent % 2 != 0) {
                result = (result * base) % 107;
            }
            base = (base * base) % 107;
            exponent /= 2;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine().trim());
        long[] numbers = new long[2];
        for (int i = 0; i < 2; i++) {
            numbers[i] = Long.parseLong(tokenizer.nextToken());
        }
        
        long result = fastModularExponentiation(numbers[0], numbers[1]);
        System.out.println(result);
    }
}