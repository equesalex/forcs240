public class Tokenizer {
    private String input; // The input string to tokenize
    private int pos = 0;// Current position in the input

    public Tokenizer(String input) {
        this.input = input.replaceAll("\\s+", "");
    }

    public Token nextToken() {
        //reached the end of input
        if (pos >= input.length()) return new Token(TokenType.EOF, "");

        //look at current character
        char current = input.charAt(pos);

        //start of a number
        if (Character.isDigit(current)) {
            int start = pos;
            while (pos < input.length() && Character.isDigit(input.charAt(pos)))
                pos++;


            return new Token(TokenType.NUMBER, input.substring(start, pos));
        }

        switch (current) {
            case '+':
                pos++;
                return new Token(TokenType.PLUS, "+");
            case '-':
                pos++;
                return new Token(TokenType.MINUS, "-");
            case '*':
                pos++;
                return new Token(TokenType.MUL, "*");
            case '/':
                pos++;
                return new Token(TokenType.DIV, "/");
            case '(':
                pos++;
                return new Token(TokenType.LPAREN, "(");
            case ')':
                pos++;
                return new Token(TokenType.RPAREN, ")");
            default:
                throw new RuntimeException("Unknown character: " + current);


        }
    }
}