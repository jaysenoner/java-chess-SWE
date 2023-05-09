package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Coordinate;
import com.company.model.Move;

import java.util.ArrayList;

public class King extends Piece{


    private Boolean isChecked = false;


    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }


    public King(Color color) {
        super(color);
    }

    @Override
    public void setPossibleMoves(final Board board) {
        Coordinate startPosition = getPosition();
        //mosse in diagonale
        ArrayList<Move> listOfMoves = board.diagonalMoves(startPosition, false);
        for (Move move :listOfMoves ){
            getPossibleMoves().add(move);
        }
        //mosse in orizzontale
        listOfMoves= board.horizontalMoves(startPosition, false);
        for (Move move :listOfMoves ){
            getPossibleMoves().add(move);
        }
        //mosse verticali
        listOfMoves= board.verticalMoves(startPosition, false);
        for (Move move :listOfMoves ){
            getPossibleMoves().add(move);
        }
    }


}
