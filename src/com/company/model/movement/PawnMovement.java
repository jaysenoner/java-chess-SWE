package com.company.model.movement;

import com.company.model.*;
import com.company.model.movement.Movement;

import java.util.ArrayList;

public class PawnMovement extends Movement {
    /* metodo che restituisce un array con tutte le mosse possibili che il pedono può fare data la sua posizione iniziale.
     * TODO:aggiungere enpassant*/
    @Override
    public ArrayList<Move> getMovesFromMovementRule(Coordinate startPosition, Board chessBoard) {
        ArrayList<Move> listOfMove = new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = chessBoard.getSquare(startPosition);
        //controllo delle mosse in diagonale se sono occupate da un nemico e posso mangiare (solo in avanti),
        //della singola mossa avanti e se sono al primo spostamento anche del doppio spostamento
        if (startSquare.getPiece().getColor() == Color.WHITE) {
            //controllo mosse per mangiare
            Move move = new Move(startSquare, chessBoard.getSquare(row - 1, col - 1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            move = new Move(startSquare, chessBoard.getSquare(row - 1, col + 1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            //contollo mosse in avanti
            Move move1 = new Move(startSquare, chessBoard.getSquare(row - 1, col));
            if (move1.isValid() && move1.isFree()) {
                listOfMove.add(move1);
                Move move2 = new Move(startSquare, chessBoard.getSquare(row - 2, col));
                if (startSquare.getPiece().isFirstMove() && move2.isValid() && move2.isFree()) {
                    listOfMove.add(move2);
                }
            }
        } else {
            //controllo mosse per mangiare
            Move move = new Move(startSquare, chessBoard.getSquare(row + 1, col - 1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            move = new Move(startSquare, chessBoard.getSquare(row + 1, col + 1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            //controllo mosse in avanti
            Move move1 = new Move(startSquare, chessBoard.getSquare(row + 1, col));
            if (move1.isValid() && move1.isFree()) {
                listOfMove.add(move1);
                Move move2 = new Move(startSquare, chessBoard.getSquare(row + 2, col));
                if (startSquare.getPiece().isFirstMove() && move2.isValid() && move2.isFree()) {
                    listOfMove.add(move2);
                }
            }
        }

        return listOfMove;
    }
}
