package com.company.model;

import com.company.model.pieces.Piece;

public class Square {
    private final Coordinate position;
    private Boolean occupied = false;
    private final Color color;
    private Piece piece;

    public Boolean getOccupied() {
        return occupied;
    }

    public Color getColor() {
        return color;
    }
    public Square(Coordinate position, Color color) {
        this.position = position;
        this.color= color;
    }
    public boolean isOccupied() {
        return occupied;
    }
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if(piece != null) {
            this.occupied = true;
            piece.setPosition(position);
        }
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Coordinate getPosition() {
        return position;
    }
}
