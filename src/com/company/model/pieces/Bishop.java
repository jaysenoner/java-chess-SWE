package com.company.model.pieces;

import com.company.model.Color;
import com.company.model.movement.DiagonalMovement;

import javax.swing.*;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
        if( color == Color.WHITE)
            imageURL="image/WB.gif";
        else
            imageURL="image/BB.gif";
        listOfMovementRules.add(new DiagonalMovement(true));
    }

    @Override
    public String getRightLetterForChessNotation() {
        return "B";
    }

}