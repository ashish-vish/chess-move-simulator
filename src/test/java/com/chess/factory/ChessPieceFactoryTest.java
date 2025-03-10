package test.java.com.chess.factory;

import main.java.com.chess.exception.ChessPieceNotFoundException;
import main.java.com.chess.factory.ChessPieceFactory;
import main.java.com.chess.models.ChessPiece;
import main.java.com.chess.models.King;
import main.java.com.chess.models.Pawn;
import main.java.com.chess.models.Queen;
import main.java.com.chess.movement.KingMoveStrategy;
import main.java.com.chess.movement.MoveStrategy;
import main.java.com.chess.movement.PawnMoveStrategy;
import main.java.com.chess.movement.QueenMoveStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ChessPieceFactoryTest {

    @BeforeAll
    static void setup() {
        ChessPieceFactory.registerChessPieceAndStrategy("pawn", Pawn::new, new PawnMoveStrategy());
        ChessPieceFactory.registerChessPieceAndStrategy("Queen", Queen::new, new QueenMoveStrategy());
        ChessPieceFactory.registerChessPieceAndStrategy("king", King::new, new KingMoveStrategy());
    }

    static Stream<Object[]> chessPieceProvide(){
        return Stream.of(
                new Object[]{"king", "D4", new King("D4")},
                new Object[]{"queen", "E5", new Queen("E5")},
                new Object[]{"pawn", "A2", new Pawn("A2")}
        );
    }

    @ParameterizedTest
    @MethodSource("chessPieceProvide")
    void givenChessPieceAndPosition_whenPieceCreated_thenReturnCorrectInstance(
            String pieceType,
            String position,
            ChessPiece expectedPiece
    ){
        ChessPiece piece = ChessPieceFactory.createPiece(pieceType, position);
        assertNotNull(piece);
        assertTrue(expectedPiece.getClass().isInstance(piece));
        assertEquals(position, piece.getPosition());

    }

    static Stream<String> invalidChessPieceProvider() {
        return Stream.of("pawnn", "quuen", "kking");
    }

    @ParameterizedTest
    @MethodSource("invalidChessPieceProvider")
    void givenInvalidPieceType_whenCreatingPiece_thenThrowsException(String invalidPieceType) {
        Exception exception = assertThrows(ChessPieceNotFoundException.class, () -> ChessPieceFactory.createPiece(invalidPieceType, "C3"));
        assertEquals("Invalid Chess Piece: " + invalidPieceType, exception.getMessage());
    }

    static Stream<Object[]> moveStrategyProvider() {
        return Stream.of(
                new Object[]{"king", new KingMoveStrategy()},
                new Object[]{"queen", new QueenMoveStrategy()},
                new Object[]{"pawn", new PawnMoveStrategy()}
        );
    }

    @ParameterizedTest
    @MethodSource("moveStrategyProvider")
    void givenChessPieceType_whenGettingMoveStrategy_thenReturnsCorrectStrategy(String pieceType, MoveStrategy expectedStrategy) {
        MoveStrategy strategy = ChessPieceFactory.getChessMoveStrategy(pieceType);
        assertNotNull(strategy);
        assertTrue(expectedStrategy.getClass().isInstance(strategy));
    }
}
