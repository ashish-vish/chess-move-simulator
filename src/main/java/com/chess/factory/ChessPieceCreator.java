package main.java.com.chess.factory;

import main.java.com.chess.models.ChessPiece;

@FunctionalInterface
public interface ChessPieceCreator {
    ChessPiece create(String position);
}
