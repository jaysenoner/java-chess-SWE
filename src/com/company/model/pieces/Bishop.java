package com.company.model.pieces;

import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Move;
import com.company.model.Square;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(final Board board) {
        return null;
    }
}