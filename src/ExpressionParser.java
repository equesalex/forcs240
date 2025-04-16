import java.util.Scanner;

public class ExpressionParser {
    public  static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression or type 'exit' to quit.");

        while (true){
            System.out.print("expr> ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")) break;

            try{
                Tokenizer tokenizer = new Tokenizer (input);
                Parser parser = new Parser(tokenizer);
                int result = parser.parseExpr();
                System.out.println("Result: "+ result);
            } catch (RuntimeException e){
                System.out.println("Error: " + e.getMessage());
            }

        }
        scanner.close();

    }

}
