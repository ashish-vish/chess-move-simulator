package main.java.com.chess;

import main.java.com.chess.exception.ChessPieceNotFoundException;
import main.java.com.chess.exception.InvalidPositionException;
import main.java.com.chess.exception.NoValidMoveException;
import main.java.com.chess.factory.ChessPieceFactory;
import main.java.com.chess.models.ChessPiece;
import main.java.com.chess.models.King;
import main.java.com.chess.models.Pawn;
import main.java.com.chess.models.Queen;
import main.java.com.chess.movement.KingMoveStrategy;
import main.java.com.chess.movement.MoveStrategy;
import main.java.com.chess.movement.PawnMoveStrategy;
import main.java.com.chess.movement.QueenMoveStrategy;

import java.util.Scanner;

public class ChessApplication {
    public static void main(String[] args) {
        ChessPieceFactory.registerChessPieceAndStrategy("Pawn", Pawn::new, new PawnMoveStrategy());
        ChessPieceFactory.registerChessPieceAndStrategy("Queen", Queen::new, new QueenMoveStrategy());
        ChessPieceFactory.registerChessPieceAndStrategy("King", King::new, new KingMoveStrategy());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChess Move Simulator");
            System.out.println("Press 1: To Enter Chess Piece and Position");
            System.out.println("Press 2: To Exit");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Enter Chess Piece Type (Pawn, Queen, King) : ");
                    String chessPiece = scanner.nextLine().trim();
                    System.out.println("Enter the position (A-H)(1-8) e.g, G6");
                    String position = scanner.nextLine().trim();

                    try {
                        ChessPiece piece = ChessPieceFactory.createPiece(chessPiece, position);
                        MoveStrategy chessMoveStrategy = ChessPieceFactory.getChessMoveStrategy(chessPiece);
                        String validMoves = chessMoveStrategy.calculateValidMoves(piece);
                        System.out.println(piece + " at " + position + "\nValid Moves -> " + validMoves);
                    } catch (InvalidPositionException | NoValidMoveException | ChessPieceNotFoundException e) {
                        System.out.println("Exception: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("Exiting Chess Simulator.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Wrong Choice: Select 1 or 2");
            }
        }
    }
}
