package com.company.model;

import java.util.ArrayList;

public class Move {
    public Square getStartSquare() {
        return startSquare;
    }

    private Square startSquare;

    private Square endSquare;

    public Move(Square startSquare, Square endSquare) {
        if(startSquare != null  && startSquare.getPosition().isValid()) {
            this.endSquare = endSquare;
        }
        this.startSquare = startSquare;
    }
    public void setEndSquare(Square endSquare) {
        this.endSquare = endSquare;
    }
    public Square getEndSquare(){return endSquare;}
    /* metodo che controlla, data una mossa se è possibile, se lo è crea la mossa e la inserisce nell'array
    che gli passiamo e restituisce un bolleano che ci indica se è possibile proseguire in quella direzione*/
    public boolean checkMove(ArrayList<Move> listOfMove, boolean checkAll){
        boolean stop= false;
        if(isValid()) {
            if (isThereMyPiece()) {
                stop = true;
            } else if (isThereAnEnemy()) {
                listOfMove.add(this);
                stop = true;
            } else {
                listOfMove.add(this);
            }
        }else{
            return true;
        }
        if(!checkAll){return true;}
        return stop;
    }
    public boolean isFree(){
        return !endSquare.isOccupied();
    }
    public boolean isThereAnEnemy(){
        return endSquare.isOccupied()&& startSquare.getPiece().getColor() != endSquare.getPiece().getColor();
    }
    public boolean isThereMyPiece(){
        return endSquare.isOccupied() && startSquare.getPiece().getColor() == endSquare.getPiece().getColor();
    }

    public boolean isValid(){
        return (startSquare != null && endSquare!= null && startSquare.getPosition().isValid() && endSquare.getPosition().isValid()) ;
    }

}
