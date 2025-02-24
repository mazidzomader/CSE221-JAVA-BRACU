import java.util.Scanner;

public class Can_you_solve_Arithmatic_Expression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = Integer.parseInt(sc.nextLine());
        for (int i = 0; i< input; i++){
            String string = sc.nextLine();
            String[] mod_string = string.split(" ");
            float operand_1 = Integer.parseInt(mod_string[1]);
            float operand_2 = Integer.parseInt(mod_string[3]);
            String operator =mod_string[2];
            switch (operator) {
                case "+":
                    System.out.println(operand_1+operand_2);
                    break;
                case "-":
                    System.out.println(operand_1-operand_2);
                    break;
                case "*":
                    System.out.println(operand_1*operand_2);          
                    break;
                case "/":  
                    System.out.println(operand_1/operand_2);          
                    break;                                                                                                                                                                               
                default:
                    break;
            }
        }
        sc.close();
    }
}
