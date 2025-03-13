import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Fast_MOD_Drift_Revisited {
    static long modPow(long base, long exp, long mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    static long sumMod(long a, long n, long m) {
        if (n == 1) return modPow(a, 1, m);
        
        long halfSum = sumMod(a, n / 2, m);
        long halfPower = modPow(a, n / 2, m);
        
        if (n % 2 == 0) {
            return (halfSum * (1 + halfPower) % m) % m;
        } else {
            return (halfSum + (halfSum * halfPower % m) + modPow(a, n, m)) % m;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        
        while (T-- > 0) {
            String[] input = br.readLine().trim().split(" ");
            long a = Long.parseLong(input[0]);
            long n = Long.parseLong(input[1]);
            long m = Long.parseLong(input[2]);
            
            long result = sumMod(a, n, m);
            output.append(result).append("\n");
        }
        
        System.out.print(output);
        br.close();
    }
}
