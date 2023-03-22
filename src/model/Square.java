package model;

public abstract class Square {

    Coordinate position;

    public abstract boolean isOccupied();

}

class EmptySquare extends Square {


    @Override
    public boolean isOccupied() {
        return false;
    }
}

class OccupiedSquare extends Square {

   // Piece piece;   //TODO: implement Piece

    @Override
    public boolean isOccupied() {
        return true;
    }



}
