package test.java.com.chess.movement;

import main.java.com.chess.exception.InvalidPositionException;
import main.java.com.chess.models.ChessPiece;
import main.java.com.chess.models.Queen;
import main.java.com.chess.movement.MoveStrategy;
import main.java.com.chess.movement.QueenMoveStrategy;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueenMoveStrategyTest {
    private final MoveStrategy queenMoveStrategy = new QueenMoveStrategy();

    @Test
    public void givenQueenAtD4_whenCalculatingMoves_thenReturnsValidMoves() {

        ChessPiece queen = new Queen("D4");
        String expectedMoves = "A1, A4, A7, B2, B4, B6, C3, C4, C5, D1, D2, D3, D5, D6, D7, D8, E3, E4, E5, F2, F4, F6, G1, G4, G7, H4, H8";

        String validMoves = queenMoveStrategy.calculateValidMoves(queen);
        assertEquals(expectedMoves, validMoves);
    }


    static Stream<Object[]> queenCornerPosition() {
        return Stream.of(
                new Object[]{"A1", "A2, A3, A4, A5, A6, A7, A8, B1, B2, C1, C3, D1, D4, E1, E5, F1, F6, G1, G7, H1, H8"},
                new Object[]{"A8", "A1, A2, A3, A4, A5, A6, A7, B7, B8, C6, C8, D5, D8, E4, E8, F3, F8, G2, G8, H1, H8"},
                new Object[]{"H1", "A1, A8, B1, B7, C1, C6, D1, D5, E1, E4, F1, F3, G1, G2, H2, H3, H4, H5, H6, H7, H8"},
                new Object[]{"H8", "A1, A8, B2, B8, C3, C8, D4, D8, E5, E8, F6, F8, G7, G8, H1, H2, H3, H4, H5, H6, H7"}
        );
    }

    @ParameterizedTest
    @MethodSource("queenCornerPosition")
    void givenQueenAtCorner_whenCalculatingMoves_thenReturnsValidMoves(String position, String expectedMoves) {

        ChessPiece queen = new Queen(position);
        String validMoves = queenMoveStrategy.calculateValidMoves(queen);
        assertEquals(expectedMoves, validMoves, "Queen at " + position + " should have the correct moves.");
    }

    static Stream<Object[]> queenEdgePosition() {
        return Stream.of(
                new Object[]{"A5", "A1, A2, A3, A4, A6, A7, A8, B4, B5, B6, C3, C5, C7, D2, D5, D8, E1, E5, F5, G5, H5"},
                new Object[]{"E8","A4, A8, B5, B8, C6, C8, D7, D8, E1, E2, E3, E4, E5, E6, E7, F7, F8, G6, G8, H5, H8"}
        );
    }

    @ParameterizedTest
    @MethodSource("queenEdgePosition")
    void givenQueenAtEdge_whenCalculatingMoves_thenReturnsValidMoves(String position, String expectedMoves) {

        ChessPiece queen = new Queen(position);
        String validMoves = queenMoveStrategy.calculateValidMoves(queen);
        assertEquals(expectedMoves, validMoves, "Queen at " + position + " should have the correct moves.");
    }

    /**
     * Exception Cases for Invalid Positions
     */
    static Stream<Object[]> invalidQueenPositions() {
        return Stream.of(
                new Object[]{"J5", "Invalid position: J5"},
                new Object[]{"A9", "Invalid position: A9"},
                new Object[]{"J9", "Invalid position: J9"}
        );
    }

    @ParameterizedTest
    @MethodSource("invalidQueenPositions")
    void givenInvalidPosition_whenCreatingQueen_thenThrowsInvalidPositionException(String position, String expectedMessage) {

        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> new Queen(position));
        assertEquals(expectedMessage, exception.getMessage());
    }
}
