package dev.amblelabs.minos;

public record Lexeme(String lexeme, Type type) {

    public enum Type {
        STR,
        INT,
        DECIMAL,
        BOOL,
        IDENTIFIER,
        OPERATOR,
        KEYWORD
    }
}
