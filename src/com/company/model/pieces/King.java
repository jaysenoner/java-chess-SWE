package com.company.model.pieces;


import com.company.model.Color;
import com.company.model.movement.DiagonalMovement;
import com.company.model.movement.HorizontalMovement;
import com.company.model.movement.VerticalMovement;

public class King extends Piece{


    private Boolean isChecked = false;

    public King(Color color) {
        super(color);
        listOfMovementRules.add(new DiagonalMovement(false));
        listOfMovementRules.add(new HorizontalMovement(false));
        listOfMovementRules.add(new VerticalMovement(false));
    }




    public Boolean getChecked() {
        return isChecked;
    }
    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

}
