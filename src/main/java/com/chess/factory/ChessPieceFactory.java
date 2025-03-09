package main.java.com.chess.factory;

import main.java.com.chess.exception.ChessPieceNotFoundException;
import main.java.com.chess.models.ChessPiece;
import main.java.com.chess.movement.MoveStrategy;

import java.util.HashMap;
import java.util.Map;

public class ChessPieceFactory {
    private static final Map<String, ChessPieceCreator> chessPieceRegistry = new HashMap<>();
    private static final Map<String, MoveStrategy> moveRegistry = new HashMap<>();

    public static void registerChessPieceAndStrategy(String chessPieceType, ChessPieceCreator creator, MoveStrategy strategy) {
        chessPieceRegistry.put(chessPieceType.toLowerCase(), creator);
        moveRegistry.put(chessPieceType.toLowerCase(), strategy);
    }

    public static ChessPiece createPiece(String chessPieceType, String position) {
        ChessPieceCreator creator = chessPieceRegistry.get(chessPieceType.toLowerCase());
        if (creator == null) {
            throw new ChessPieceNotFoundException("Invalid Chess Piece: " + chessPieceType);
        }
        return creator.create(position);
    }

    public static MoveStrategy getChessMoveStrategy(String chessPieceType) {
        return moveRegistry.get(chessPieceType.toLowerCase());
    }
}
