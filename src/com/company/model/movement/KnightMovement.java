package com.company.model.movement;

import com.company.model.Board;
import com.company.model.Coordinate;
import com.company.model.Move;
import com.company.model.Square;
import com.company.model.movement.Movement;

import java.util.ArrayList;

public class KnightMovement extends Movement {

    @Override
    public ArrayList<Move> getMovesFromMovementRule(Coordinate startPosition, Board chessBoard) {
        ArrayList<Move> listOfMove = new ArrayList<>();
        Square startSquare = chessBoard.getSquare(startPosition);
        int row = startPosition.getRow();
        int col = startPosition.getCol();

        Move move = new Move(startSquare, chessBoard.getSquare(row + 1, col + 2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        move = new Move(startSquare, chessBoard.getSquare(row + 2, col + 1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        move = new Move(startSquare, chessBoard.getSquare(row - 2, col - 1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        move = new Move(startSquare, chessBoard.getSquare(row - 1, col - 2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        move = new Move(startSquare, chessBoard.getSquare(row - 2, col + 1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        move = new Move(startSquare, chessBoard.getSquare(row - 1, col + 2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        move = new Move(startSquare, chessBoard.getSquare(row + 2, col - 1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        move = new Move(startSquare, chessBoard.getSquare(row + 1, col - 2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()) {
            listOfMove.add(move);
        }
        return listOfMove;
    }
}
