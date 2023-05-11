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
        possibleMoves.clear();
        //mosse diagonali
        possibleMoves.addAll(board.diagonalMoves(startPosition, true));
        //mosse in orizzontale
        possibleMoves.addAll(board.horizontalMoves(startPosition, true));
        //mosse verticali
        possibleMoves.addAll(board.verticalMoves(startPosition, true));
    }
}
