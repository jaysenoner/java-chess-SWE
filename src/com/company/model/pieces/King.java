package com.company.model.pieces;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Move;

import java.util.ArrayList;

public class King extends Piece{


    private Boolean isChecked = false;


    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }


    public King(Color color) {
        super(color);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(final Board board) {
        return null;
    }


}
