import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Can_you_solve_Arithmatic_Expression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int input = Integer.parseInt(br.readLine());
        for (int i = 0; i < input; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken(); // Ignore the first token
            float operand_1 = Integer.parseInt(st.nextToken());
            String operator = st.nextToken();
            float operand_2 = Integer.parseInt(st.nextToken());
            
            switch (operator) {
                case "+":
                    System.out.println(operand_1 + operand_2);
                    break;
                case "-":
                    System.out.println(operand_1 - operand_2);
                    break;
                case "*":
                    System.out.println(operand_1 * operand_2);
                    break;
                case "/":
                    System.out.println(operand_1 / operand_2);
                    break;
                default:
                    break;
            }
        }
    }
}
