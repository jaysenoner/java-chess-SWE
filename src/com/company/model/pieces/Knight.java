package com.company.model.pieces;


import com.company.model.*;
import com.company.model.movement.KnightMovement;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
        listOfMovementRules.add(new KnightMovement());
    }

    @Override
    public String getRightLetterForChessNotation() {
        return "N";
    }


}
