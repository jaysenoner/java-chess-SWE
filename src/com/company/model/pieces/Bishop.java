package com.company.model.pieces;

import com.company.model.*;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }
    // controlla in tutte e quattro le diagonali partendo dalla posizione di partenza
    // se ci sono case libere, se è così le inserisce alla lista, altrimenti si ferma.
    //inserisce anche come possibile mossa quella in cui la prima casa occupata è occupata da un avversario.

    @Override
    public void setPossibleMoves(final Board board) {
        Coordinate startPosition = getPosition();
        possibleMoves.addAll(board.diagonalMoves(startPosition, true));
    }
}