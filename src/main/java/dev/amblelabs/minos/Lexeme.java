package dev.amblelabs.minos;

public record Lexeme(String lexeme, Type type) {

    public enum Type {
        STR,
        INT,
        DECIMAL,
        BOOL,
        IDENTIFIER,
        OPERATOR,
        DIVIDE,
        PLUS,
        MINUS,
        MULTIPLY,
        LPAREN,
        RPAREN,
        EOF,
        KEYWORD
    }
}
