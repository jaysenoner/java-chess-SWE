package com.company.model.pieces;

import com.company.model.Color;
import com.company.model.movement.DiagonalMovement;

import javax.swing.*;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
        if( color == Color.WHITE)
            imageURL="image/75px_white_bishop.png";
        else
            imageURL="image/75px_black_bishop.png";
        listOfMovementRules.add(new DiagonalMovement(true));
    }

    @Override
    public Piece copy() {
        return  (Bishop) super.copy();
    }

    @Override
    public String getRightLetterForChessNotation() {
        return "B";
    }

}