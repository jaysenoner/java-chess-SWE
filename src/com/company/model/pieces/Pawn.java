package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Coordinate;
import com.company.model.Move;
import com.company.model.movement.PawnMovement;

import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(Color color) {
        super(color);
        if( color == Color.WHITE)
            imageURL="image/75px_white_pawn.png";
        else
            imageURL="image/75px_black_pawn.png";
        listOfMovementRules.add(new PawnMovement());
    }

    @Override
    public Piece copy() {
        return (Pawn) super.copy();
    }

    @Override
    public String getRightLetterForChessNotation() {
        return "";
    }
}