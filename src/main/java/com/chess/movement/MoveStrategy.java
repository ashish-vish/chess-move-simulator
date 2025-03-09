package main.java.com.chess.movement;

import main.java.com.chess.models.ChessPiece;

public interface MoveStrategy {
    String calculateValidMoves(ChessPiece piece);
}
