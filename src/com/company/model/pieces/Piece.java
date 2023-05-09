package com.company.model.pieces;

import com.company.model.Board;
import com.company.model.Coordinate;
import com.company.model.Move;
import java.util.ArrayList;
import com.company.model.Color;

public abstract class Piece {
    private boolean captured = false;
    private Color color;

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public ArrayList<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(ArrayList<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    private boolean hasMoved = false;
    private boolean firstMove= false;
    private Coordinate position;
    private ArrayList<Move> possibleMoves;

    public Piece(Color color) {
        this.color= color;
        possibleMoves= new ArrayList<>();

    }
    public void setPosition(Coordinate position) {
        this.position = position;
    }
    public Coordinate getPosition(){
        return position;
    }
    public abstract void setPossibleMoves(final Board board);
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







