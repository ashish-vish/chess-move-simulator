package test.java.com.chess.movement;

import main.java.com.chess.exception.InvalidPositionException;
import main.java.com.chess.models.ChessPiece;
import main.java.com.chess.models.King;
import main.java.com.chess.movement.KingMoveStrategy;
import main.java.com.chess.movement.MoveStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KingMoveStrategyTest {
    private final MoveStrategy kingMoveStrategy = new KingMoveStrategy();

    @Test
    void givenKingAtD4_whenCalculatingMoves_thenReturnsEightMoves() {
        ChessPiece king = new King("D4");
        String validMoves = kingMoveStrategy.calculateValidMoves(king);
        assertEquals("C3, C4, C5, D3, D5, E3, E4, E5", validMoves);
    }

    /** Corner Cases **/
    static Stream<Object[]> kingCornerCases() {
        return Stream.of(
                new Object[]{"A1", "A2, B1, B2"},
                new Object[]{"A8", "A7, B7, B8"},
                new Object[]{"H1", "G1, G2, H2"},
                new Object[]{"H8", "G7, G8, H7"}
        );
    }

    @ParameterizedTest
    @MethodSource("kingCornerCases")
    void givenKingAtCorner_whenCalculatingMoves_thenReturnsValidMoves(String position, String expectedMoves) {
        ChessPiece king = new King(position);
        String validMoves = kingMoveStrategy.calculateValidMoves(king);
        assertEquals(expectedMoves, validMoves);
    }

    /**  Edge Cases **/
    static Stream<Object[]> kingEdgeCases() {
        return Stream.of(
                new Object[]{"A5", "A4, A6, B4, B5, B6"},
                new Object[]{"H5", "G4, G5, G6, H4, H6"},
                new Object[]{"D1", "C1, C2, D2, E1, E2"},
                new Object[]{"D8", "C7, C8, D7, E7, E8"}
        );
    }

    @ParameterizedTest
    @MethodSource("kingEdgeCases")
    void givenKingAtEdge_whenCalculatingMoves_thenReturnsValidMoves(String position, String expectedMoves) {
        ChessPiece king = new King(position);
        String validMoves = kingMoveStrategy.calculateValidMoves(king);
        assertEquals(expectedMoves, validMoves);
    }

    /** For InvalidPositionException **/
    static Stream<Object[]> invalidKingPositions() {
        return Stream.of(
                new Object[]{"J5", "Invalid position: J5"},
                new Object[]{"A9", "Invalid position: A9"},
                new Object[]{"J9", "Invalid position: J9"}
        );
    }

    @ParameterizedTest
    @MethodSource("invalidKingPositions")
    void givenInvalidPosition_whenCreatingKing_thenThrowsInvalidPositionException(String position, String expectedMessage) {
        InvalidPositionException exception = assertThrows(InvalidPositionException.class, () -> new King(position));
        assertEquals(expectedMessage, exception.getMessage());
    }
}
