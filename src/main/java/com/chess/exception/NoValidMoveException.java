package main.java.com.chess.exception;

public class NoValidMoveException extends RuntimeException{
    public NoValidMoveException(String message) {
        super(message);
    }
}
