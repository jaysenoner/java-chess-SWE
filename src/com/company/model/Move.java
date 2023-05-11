package com.company.model;

import java.util.ArrayList;

public class Move {
    Square startSquare;

    public void setEndSquare(Square endSquare) {
        this.endSquare = endSquare;
    }

    Square endSquare;

    public Move(Square startSquare, Square endSquare) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }
    /* metodo che controlla, data una mossa se è possibile, se lo è crea la mossa e la inserisce nell'array
    che gli passiamo e restituisce un bolleano che ci indica se è possibile proseguire in quella direzione*/
    public boolean checkMove(ArrayList<Move> listOfMove){
        boolean stop= false;
        if (isThereMyPiece()) {
            stop = true;
        } else if (isThereAnEnemy()) {
            listOfMove.add(this);
            stop = true;
        } else {
            listOfMove.add(this);
        }
        return stop;
    }
    public boolean isFree(){
        if(!startSquare.isOccupied()){
            return true;
        }else{
            return false;
        }
    }
    public boolean isThereAnEnemy(){
        if(startSquare.isOccupied() && startSquare.piece.getColor()!= endSquare.piece.getColor()){
            return true;
        }else{
            return false;
        }
    }
    public boolean isThereMyPiece(){
        if(startSquare.isOccupied() && startSquare.piece.getColor()== endSquare.piece.getColor()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isValid(){
        if(startSquare.getPosition().isValid() && endSquare.getPosition().isValid()){
            return true;
        }else{
            return false;
        }
    }

}
