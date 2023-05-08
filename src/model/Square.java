package model;

public class Square {

    Coordinate position;
    Boolean occupied;
    Color color;
    Piece piece;

    public boolean isOccupied() {
        return occupied;
    }
    public void setPiece(Piece p){
        this.piece= p;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}
