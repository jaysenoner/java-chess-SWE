package com.company.model.pieces;


import com.company.model.*;
import com.company.model.movement.KnightMovement;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
        if( color == Color.WHITE)
            imageURL="image/75px_white_knight.png";
        else
            imageURL="image/75px_black_knight.png";
        listOfMovementRules.add(new KnightMovement());
    }

    @Override
    public Piece copy() {
        return (Knight) super.copy();
    }

    @Override
    public String getRightLetterForChessNotation() {
        return "N";
    }


}
