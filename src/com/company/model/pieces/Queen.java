package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Coordinate;
import com.company.model.Move;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }

    @Override
    public void setPossibleMoves(final Board board) {
        Coordinate startPosition = getPosition();
        ArrayList<Move> listOfMoves = board.diagonalMoves(startPosition, true);
        for (Move move :listOfMoves ){
            getPossibleMoves().add(move);
        }
        //TODO: aggiungere mosse in orizzontale e verticale
    }
}
