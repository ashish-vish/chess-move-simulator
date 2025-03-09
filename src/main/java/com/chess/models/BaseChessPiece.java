package main.java.com.chess.models;

import main.java.com.chess.exception.InvalidPositionException;

public abstract class BaseChessPiece implements ChessPiece{
    protected String position;

    public BaseChessPiece(String position) {
        validatePosition(position);
        this.position=position;
    }

    @Override
    public String getPosition(){
        return position;
    }
    private void validatePosition(String position) {
        if (position.length() != 2) {
            throw new InvalidPositionException("Invalid position: " + position);
        }

        char column = Character.toUpperCase(position.charAt(0));
        char row = position.charAt(1);

        if (column < 'A' || column > 'H' || row < '1' || row > '8') {
            throw new InvalidPositionException("Invalid position: " + position);
        }
    }
}
