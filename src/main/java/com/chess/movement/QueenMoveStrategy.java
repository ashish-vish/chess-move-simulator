package main.java.com.chess.movement;

import main.java.com.chess.models.ChessPiece;

import java.util.TreeSet;

public class QueenMoveStrategy implements MoveStrategy {
    @Override
    public String calculateValidMoves(ChessPiece piece) {
        TreeSet<String> validMoves = new TreeSet<>();
        char column = Character.toUpperCase(piece.getPosition().charAt(0));
        int row = Character.getNumericValue(piece.getPosition().charAt(1));

        for (char col = 'A'; col <= 'H'; col++) {
            if (col != column)
                validMoves.add(col + String.valueOf(row));
        }

        for (int rowNumber = 1; rowNumber <= 8; rowNumber++) {
            if (row != rowNumber)
                validMoves.add(column + String.valueOf(rowNumber));
        }

        for (int i = 1; i <= 8; i++) {
            if (column + i <= 'H' && row + i <= 8)
                validMoves.add((char)(column +i) + String.valueOf(row+i));
            if (column + i <= 'H' && row - i >= 1)
                validMoves.add((char)(column +i) + String.valueOf(row-i));
            if (column - i >= 'A' && row + i <= 8)
                validMoves.add((char)(column - i) + String.valueOf(row+i));
            if (column -i >= 'A' && row - i >= 1)
                validMoves.add((char)(column -i) + String.valueOf(row-i));
        }
        return String.join(", ",validMoves);
    }
}
