package com.company.model;

import com.company.model.pieces.Piece;

public class Square {
    Coordinate position;
    Boolean occupied = false;
    Color color;

    public Piece getPiece() {
        return piece;
    }

    Piece piece;

    public Square(Coordinate position, Color color) {
        this.position = position;
        this.color= color;
    }
    public boolean isOccupied() {
        return occupied;
    }
    public void setPiece(Piece p){
        this.piece= p;
        this.occupied = true;
        piece.setPosition(position);
    }
    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}
