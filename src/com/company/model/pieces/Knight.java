package com.company.model.pieces;


import com.company.model.*;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public void setPossibleMoves(final Board board) {
        Coordinate startPosition = getPosition();
        ArrayList<Move> listOfMoves = board.knightMovement(startPosition);
        for (Move move :listOfMoves ){
            getPossibleMoves().add(move);
        }
    }
}
