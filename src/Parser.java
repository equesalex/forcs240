public class Parser {
    private Tokenizer tokenizer;
    private Token currentToken;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        //starting with the first token
        this.currentToken = tokenizer.nextToken();
    }

    //Consume a token if it catches what we expect
    private void eat(TokenType expected) {
        if (currentToken.type == expected) {
            //advance to the next token
            currentToken = tokenizer.nextToken();
        } else {
            throw new RuntimeException("Expected " + expected + "but got " + currentToken.type);
        }
    }

    private int parseFactor() {
        if (currentToken.type == TokenType.NUMBER) {

            int value = Integer.parseInt(currentToken.text);
            eat(TokenType.NUMBER);
            return value;
        } else if (currentToken.type == TokenType.LPAREN) {

            eat(TokenType.LPAREN);
            int value = parseExpr();
            eat(TokenType.RPAREN);
            return value;
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken.type);
        }
    }

    private int parseTerm() {
        int result = parseFactor();

        while (currentToken.type == TokenType.MUL || currentToken.type == TokenType.DIV) {

            TokenType op = currentToken.type;

            eat(op);

            int rhs = parseFactor();

            //apply the operator to the running result
            if (op == TokenType.MUL)
                result *= rhs;
            else
                result /= rhs;

        }

        return result;
    }

    public int parseExpr() {
        //start by parsing the first term
        int result = parseTerm();

        //while the next token is + or -, continue parsing additional terms
        while (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS) {

            TokenType op = currentToken.type;
            eat(op);
            int rhs = parseTerm();

            if (op == TokenType.PLUS)
                result += rhs;
            else
                result -= rhs;

        }

        return result;

    }
}