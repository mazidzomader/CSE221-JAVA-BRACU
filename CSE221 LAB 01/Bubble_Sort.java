import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bubble_Sort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int first = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        
        String[] second = new String[first];
        int index = 0;
        
        while (st.hasMoreTokens()) {
            second[index++] = st.nextToken();
        }

        for (int i = 0; i < first - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < first - 1 - i; j++) {
                if (Integer.parseInt(second[j]) > Integer.parseInt(second[j + 1])) {
                    flag = true;
                    String temp = second[j];
                    second[j] = second[j + 1];
                    second[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            }
        }

        for (int i = 0; i < first; i++) {
            System.out.print(second[i] + " ");
        }
    }
}
