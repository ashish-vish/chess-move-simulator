package test.java.com.chess.movement;

import main.java.com.chess.exception.InvalidPositionException;
import main.java.com.chess.exception.NoValidMoveException;
import main.java.com.chess.models.ChessPiece;
import main.java.com.chess.models.Pawn;
import main.java.com.chess.movement.MoveStrategy;
import main.java.com.chess.movement.PawnMoveStrategy;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PawnMoveStrategyTest {
    private final MoveStrategy pawnMoveStrategy = new PawnMoveStrategy();

    @Test
    public void givenPawnAtD2_whenCalculatingMoves_thenReturnsD3() {
        ChessPiece pawn = new Pawn("D2");
        String validMoves = pawnMoveStrategy.calculateValidMoves(pawn);
        assertEquals("D3", validMoves);
    }

    @Test
    public void givenPawnAtD8_whenCalculatingMoves_thenThrowsNoValidMoveException() {
        ChessPiece pawn = new Pawn("D8");
        NoValidMoveException exception = assertThrows(NoValidMoveException.class,() -> pawnMoveStrategy.calculateValidMoves(pawn));
        assertEquals("No valid Moves", exception.getMessage());
    }

    @Test
    public void givenInvalidPositionA9_whenCreatingPawn_thenThrowsInvalidPositionException() {

        InvalidPositionException exception = assertThrows(InvalidPositionException.class,() -> new Pawn("A9"));
        assertEquals("Invalid position: A9", exception.getMessage());
    }

    @Test
    public void givenInvalidPositionP3_whenCreatingPawn_thenThrowsInvalidPositionException() {

        InvalidPositionException exception = assertThrows(InvalidPositionException.class,() -> new Pawn("P3"));
        assertEquals("Invalid position: P3", exception.getMessage());
    }
}
