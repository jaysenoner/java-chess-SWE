package com.company.model.pieces;

import com.company.model.Board;
import com.company.model.Coordinate;
import com.company.model.Move;
import java.util.ArrayList;
import com.company.model.Color;

public abstract class Piece {
    private boolean captured = false;
    private Color color;
    private boolean hasMoved = false;
    private Coordinate position;

    public Piece(Color color) {
        this.color= color;

    }
    public void setPosition(Coordinate position) {
        this.position = position;
    }
    public abstract ArrayList<Move> getPossibleMoves(final Board board);
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







