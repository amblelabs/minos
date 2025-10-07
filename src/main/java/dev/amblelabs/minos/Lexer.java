package dev.amblelabs.minos;

import java.io.IOException;
import java.io.Reader;

public class Lexer {

    public final Reader reader;

    public Lexer(Reader reader) {
        this.reader = reader;
    }

    public Lexeme next() throws IOException {
        int character = reader.read();
        Lexeme lexeme = switch(character) {
            default -> {
                if (character == -1) {
                    yield null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append((char) character);
                Lexeme.Type type = Lexeme.Type.INT;
                while (true) {
                    reader.mark(1);
                    character = reader.read();
                    // TODO add error handling - Loqor
                    if (character == '.' && type != Lexeme.Type.DECIMAL) {
                        type = Lexeme.Type.DECIMAL;
                    } else if (character == -1 || !(Character.isDigit(character) ||
                            character == '.')) {
                        break;
                    }
                    sb.append((char) character);
                }
                yield new Lexeme(sb.toString(), Lexeme.Type.INT);
            }
        };
        return lexeme;
    }
}
