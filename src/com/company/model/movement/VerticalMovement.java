package com.company.model.movement;

import com.company.model.Board;
import com.company.model.Coordinate;
import com.company.model.Move;
import com.company.model.Square;
import com.company.model.movement.Movement;

import java.util.ArrayList;

public class VerticalMovement extends Movement {

    public boolean checkAll;

    public VerticalMovement(boolean checkAll){
        this.checkAll = checkAll;
    }
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in verticale,
        o per tutta la lunghezza della riga (torre), o solo per una casella (re). se checkAllCol è true controlla
        tutta la colonna.*/
    @Override
    public ArrayList<Move> getMovesFromMovementRule(Coordinate startPosition, Board chessBoard) {
        ArrayList<Move> listOfMove = new ArrayList<>();
        Square startSquare = chessBoard.getSquare(startPosition);

        boolean stop = false;
        int i = startPosition.getRow();
        int col = startPosition.getCol();
        while (!stop) {
            i++;
            Move move = new Move(startSquare, chessBoard.getSquare(i, col));
            stop = move.checkMove(listOfMove, checkAll);
        }
        stop = false;
        i = startPosition.getRow();
        while (!stop) {
            i--;
            Move move = new Move(startSquare, chessBoard.getSquare(i, col));
            stop = move.checkMove(listOfMove, checkAll);
        }
        return listOfMove;
    }
}
