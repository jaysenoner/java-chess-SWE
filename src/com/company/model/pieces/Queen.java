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
        listOfMovementRules.add(new DiagonalMovement(true));
        listOfMovementRules.add(new HorizontalMovement(true));
        listOfMovementRules.add(new VerticalMovement(true));
    }

}
