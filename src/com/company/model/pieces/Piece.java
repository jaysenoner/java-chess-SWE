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
    private boolean firstMove= true;
    private Coordinate position;
    protected ArrayList<Move> possibleMoves;

    public Piece(Color color) {
        this.color= color;
        possibleMoves= new ArrayList<>();

    }

    public boolean isCaptured() {
        return captured;
    }
    public boolean isHasMoved() {
        return hasMoved;
    }

    public boolean isFirstMove() {
        return firstMove;
    }
    public Color getColor() {
        return color;
    }
    public Coordinate getPosition(){
        return position;
    }
    public ArrayList<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFirstMove() {
        this.firstMove = false;
    }
    public void setPossibleMoves(ArrayList<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
    public void setPosition(Coordinate position) {
        this.position = position;
    }
    public abstract void setPossibleMoves(final Board board);
    public void setHasMoved() {
        this.hasMoved = true;
        this.firstMove=false;
    }
    public void setCaptured() {
        captured = true;
    }

    //TODO: TESTARE setPossibleMoves()
}







