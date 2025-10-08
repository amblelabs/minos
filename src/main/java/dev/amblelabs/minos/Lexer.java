package dev.amblelabs.minos;

public class Lexer {

    private final String source;
    private int currentPos = 0;

    public Lexer(String source) {
        this.source = source;
    }

    public Lexeme next() {
        while (currentPos < source.length() && Character.isWhitespace(source.charAt(currentPos))) {
            currentPos++;
        }

        if (currentPos >= source.length()) {
            return new Lexeme("", Lexeme.Type.EOF);
        }

        char character = source.charAt(currentPos);

        switch (character) {
            case '+' : currentPos++; return new Lexeme("+", Lexeme.Type.PLUS);
            case '-' : currentPos++; return new Lexeme("-", Lexeme.Type.MINUS);
            case '*' : currentPos++; return new Lexeme("*", Lexeme.Type.MULTIPLY);
            case '/' : currentPos++; return new Lexeme("/", Lexeme.Type.DIVIDE);
            case '=' : currentPos++; return new Lexeme("=", Lexeme.Type.OPERATOR);
            case '{' : currentPos++; return new Lexeme("{", Lexeme.Type.LPAREN);
            case '}' : currentPos++; return new Lexeme("}", Lexeme.Type.RPAREN);
        }

        if (Character.isLetter(character)) {
            StringBuilder sb = new StringBuilder();
            while (currentPos < source.length() && (Character.isLetterOrDigit(source.charAt(currentPos)) || source.charAt(currentPos) == '_')) {
                sb.append(source.charAt(currentPos));
                currentPos++;
            }
            String lexemeStr = sb.toString();
            Lexeme.Type type = switch (lexemeStr) {
                case "let", "const", "if", "else", "while", "for", "return", "function", "true", "false" -> Lexeme.Type.KEYWORD;
                default -> Lexeme.Type.IDENTIFIER;
            };
            return new Lexeme(lexemeStr, type);
        }

        if (Character.isDigit(character)) {
            StringBuilder num = new StringBuilder();
            while (currentPos < source.length() && (Character.isDigit(source.charAt(currentPos)) || source.charAt(currentPos) == '.')) {
                num.append(source.charAt(currentPos));
                currentPos++;
            }
            return new Lexeme(num.toString(), num.toString().contains(".") ? Lexeme.Type.DECIMAL : Lexeme.Type.INT);
        }

        System.err.println("Unexpected character: " + character);
        currentPos++;
        return next();
    }
}
