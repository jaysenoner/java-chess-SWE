package com.company.model.pieces;

import com.company.model.*;
import com.company.model.movement.Movement;

import java.util.ArrayList;

public abstract class Piece implements Cloneable {
    private boolean captured = false;
    private Color color;
    private boolean hasMoved = false;
    private boolean firstMove= true;
    private Coordinate position;
    protected String imageURL;
    protected ArrayList<Movement> listOfMovementRules;
    protected ArrayList<Move> possibleMoves;

    public Piece(Color color) {
        this.color= color;
        possibleMoves= new ArrayList<>();
        listOfMovementRules = new ArrayList<>();
    }

    public Piece copy() {
        try {
            Piece clone = (Piece) super.clone();
            clone.listOfMovementRules = new ArrayList<>(this.listOfMovementRules);
            clone.possibleMoves = new ArrayList<>(this.possibleMoves);
            clone.position = new Coordinate(this.position.getRow(), this.position.getCol());
            return clone;
        } catch (CloneNotSupportedException e) {
            // This should never happen since Piece implements Cloneable
            throw new AssertionError();
        }
    }

    public ArrayList<Movement> getListOfMovementRules() {
        return listOfMovementRules;
    }
    public boolean isCaptured() {
        return captured;
    }
    public boolean HasMoved() {
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

    public String getImageURL() {
        return imageURL;
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

    //Metodo che itera sulla lista delle regole di movimento e per ognuna di esse genera una lista di mosse POSSIBILI(no controllo legalità es scacchi ecc)
    public void setPossibleMoves(final Board board){
        Coordinate startPosition = getPosition();
        possibleMoves.clear();
        for(Movement movementRule: listOfMovementRules)
            possibleMoves.addAll(movementRule.getMovesFromMovementRule(startPosition, board));
    }

    public abstract String getRightLetterForChessNotation();


    public void setHasMoved() {
        this.hasMoved = true;
        this.firstMove = false;
    }

    public void setCaptured() {
        captured = true;
    }


}







