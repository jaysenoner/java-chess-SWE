package com.company.model.movement;

import com.company.model.Board;
import com.company.model.Coordinate;
import com.company.model.Move;
import com.company.model.Square;
import com.company.model.movement.Movement;

import java.util.ArrayList;

public class HorizontalMovement extends Movement {

    public boolean checkAll;
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in orizzontale,
       o per tutta la lunghezza della riga (torre), o solo per una casella (re). se checkAllRow è true controlla
       tutta la riga.*/
    public HorizontalMovement(boolean checkAll) {
        this.checkAll = checkAll;
    }
    @Override
    public ArrayList<Move> getMovesFromMovementRule(Coordinate startPosition, Board chessBoard) {
        ArrayList<Move> listOfMove = new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = chessBoard.getSquare(startPosition);
        Move move;

        boolean stop = false;
        int j = col;
        while (!stop) {
            j++;
            move = new Move(startSquare, chessBoard.getSquare(row, j));
            stop = move.checkMove(listOfMove, checkAll);
        }
        stop = false;
        j = col;
        while (!stop) {
            j--;
            move = new Move(startSquare, chessBoard.getSquare(row, j));
            stop = move.checkMove(listOfMove, checkAll);
        }
        return listOfMove;
    }
}
