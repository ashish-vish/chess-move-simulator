package main.java.com.chess.movement;

import main.java.com.chess.models.ChessPiece;

import java.util.Optional;

public class PawnMovingStrategy implements MoveStrategy{
    @Override
    public String calculateValidMoves(ChessPiece piece) {

        Optional<String> validMove = Optional.empty();
        char column = Character.toUpperCase(piece.getPosition().charAt(0));
        int row = Character.getNumericValue(piece.getPosition().charAt(1));

        if (row < 8) {
            row += 1;
            validMove = Optional.of(column + String.valueOf(row));
        }

        return validMove.orElse("No valid moves");
    }
}
