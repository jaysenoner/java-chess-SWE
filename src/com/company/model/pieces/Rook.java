package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Coordinate;
import com.company.model.Move;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public void setPossibleMoves(final Board board) {
        Coordinate startPosition = getPosition();
        //mosse in orizzontale
        ArrayList<Move> listOfMoves = board.horizontalMoves(startPosition, true);
        for (Move move :listOfMoves ){
            getPossibleMoves().add(move);
        }
        //mosse verticali
        listOfMoves = board.verticalMoves(startPosition, true);
        for (Move move :listOfMoves ){
            getPossibleMoves().add(move);
        }
    }
}