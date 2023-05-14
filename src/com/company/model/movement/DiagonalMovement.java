package com.company.model.movement;

import com.company.model.Board;
import com.company.model.Coordinate;
import com.company.model.Move;
import com.company.model.Square;
import com.company.model.movement.Movement;

import java.util.ArrayList;

public class DiagonalMovement extends Movement {

    boolean checkAll;

    public DiagonalMovement(boolean checkAll){this.checkAll = checkAll;}

    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in diagonale, o per tutta la lunghezza
   della diagonale (alfiere), o solo per una casella in diagonale (re). se checkAllDiagonal è true controlla tutta la diagonale.
    */
    @Override
    public ArrayList<Move> getMovesFromMovementRule(Coordinate startPosition, Board chessBoard) {
        ArrayList<Move> listOfMove = new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = chessBoard.getSquare(startPosition);
        boolean stop = false;
        int i = row, j = col;
        while (!stop) {
            i++;
            j++;
            Move move = new Move(startSquare, chessBoard.getSquare(i, j));
            stop = move.checkMove(listOfMove, checkAll);
        }
        stop = false;
        i = row;
        j = col;
        while (!stop) {
            i--;
            j--;
            Move move = new Move(startSquare, chessBoard.getSquare(i, j));
            stop = move.checkMove(listOfMove, checkAll);
        }
        stop = false;
        i = row;
        j = col;
        while (!stop) {
            i++;
            j--;
            Move move = new Move(startSquare, chessBoard.getSquare(i, j));
            stop = move.checkMove(listOfMove, checkAll);
        }
        stop = false;
        i = row;
        j = col;
        while (!stop) {
            i--;
            j++;
            Move move = new Move(startSquare, chessBoard.getSquare(i, j));
            stop = move.checkMove(listOfMove, checkAll);
        }
        return listOfMove;
    }
}
