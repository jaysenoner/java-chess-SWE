package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;

public class Pawn extends Piece{

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public void setPossibleMoves(final Board board) {
        //TODO: vedere se va bene il controllo diagonale
    }
}