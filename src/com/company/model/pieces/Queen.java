package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Coordinate;
import com.company.model.Move;
import com.company.model.movement.DiagonalMovement;
import com.company.model.movement.HorizontalMovement;
import com.company.model.movement.VerticalMovement;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
        if( color == Color.WHITE)
            imageURL="image/75px_white_queen.png";
        else
            imageURL="image/75px_black_queen.png";
        listOfMovementRules.add(new DiagonalMovement(true));
        listOfMovementRules.add(new HorizontalMovement(true));
        listOfMovementRules.add(new VerticalMovement(true));
    }

    @Override
    public Piece copy() {
        return (Queen) super.copy();
    }

    @Override
    public String getRightLetterForChessNotation() {
        return "Q";
    }

}
