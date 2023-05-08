package com.company.model.pieces;

import com.company.model.Move;
import java.util.ArrayList;
import com.company.model.Color;

public abstract class Piece {
    private boolean captured = false;
    private Color color;
    private boolean hasMoved = false;

    public Piece(Color color) {
        this.color= color;

    }
    public abstract ArrayList<Move> getLegalMoves();
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    public void setCaptured() {
        captured = true;
    }
    public Color getColor() {
        return color;
    }
}







