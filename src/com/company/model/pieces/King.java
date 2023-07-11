package com.company.model.pieces;


import com.company.model.Color;
import com.company.model.Square;
import com.company.model.movement.DiagonalMovement;
import com.company.model.movement.HorizontalMovement;
import com.company.model.movement.VerticalMovement;

import java.util.ArrayList;

public class King extends Piece{


    private Boolean isChecked = false;

    public King(Color color) {
        super(color);
        if( color == Color.WHITE)
            imageURL="image/WK.gif";
        else
            imageURL="image/BK.gif";
        listOfMovementRules.add(new DiagonalMovement(false));
        listOfMovementRules.add(new HorizontalMovement(false));
        listOfMovementRules.add(new VerticalMovement(false));
    }

    @Override
    public Piece copy() {
        King clone = (King) super.copy();
        clone.isChecked = this.isChecked;
        return clone;
    }

    @Override
    public String getRightLetterForChessNotation() {
        return "K";
    }


    public Boolean getChecked() {
        return isChecked;
    }
    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

}
