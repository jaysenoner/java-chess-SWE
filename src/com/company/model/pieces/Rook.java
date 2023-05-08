package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Move;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(final Board board) {
        return null;
    }
}