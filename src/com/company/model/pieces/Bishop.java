package com.company.model.pieces;

import com.company.model.Color;
import com.company.model.movement.DiagonalMovement;

public class Bishop extends Piece {

    public Bishop(Color color) {

        super(color);
        listOfMovementRules.add(new DiagonalMovement(true));
    }

}