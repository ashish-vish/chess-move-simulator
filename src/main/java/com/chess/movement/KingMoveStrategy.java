package main.java.com.chess.movement;

import main.java.com.chess.models.ChessPiece;

import java.util.TreeSet;

public class KingMoveStrategy implements MoveStrategy{
    @Override
    public String calculateValidMoves(ChessPiece piece) {

        TreeSet<String> resultSet = new TreeSet<>();

        char col = Character.toUpperCase(piece.getPosition().charAt(0));
        int row = Character.getNumericValue(piece.getPosition().charAt(1));

        int[][] directions = {
                {1,-1},{1,0},{1,1},
                {0,-1},{0,1},
                {-1,-1},{-1,0},{-1,1}
        };

        for (int[] dir : directions){
            char newCol = (char)(col+dir[1]);
            int newRow = row+dir[0];

            if (newCol >= 'A' && newCol <= 'H' && newRow >= 1 && newRow <= 8){
                resultSet.add(newCol + String.valueOf(newRow));
            }
        }

        return String.join(", ",resultSet);
    }
}
