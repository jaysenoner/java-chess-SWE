package model;

public abstract class Piece {
    boolean ate;

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    boolean hasMoved = false;
    public void ate() {
        ate = true;
    }
}
class King extends Piece{
}
class Queen extends Piece{

}
class Knight extends Piece{

}
class Rook extends Piece{


    public Rook() {
    }
}
class Bishop extends Piece{

}
class Pawn extends Piece{

}

