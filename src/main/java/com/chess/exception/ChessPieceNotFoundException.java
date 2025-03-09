package main.java.com.chess.exception;

public class ChessPieceNotFoundException extends RuntimeException {
    public ChessPieceNotFoundException(String message) {
        super(message);
    }
}
