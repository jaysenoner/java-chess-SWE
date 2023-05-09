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
        Color c= startSquare.getPiece().getColor();
        if (endSquare.isOccupied() && endSquare.getPiece().getColor() == c) {
            stop = true;
        } else if (endSquare.isOccupied() && endSquare.getPiece().getColor() != c) {
            listOfMove.add(this);
            stop = true;
        } else {
            listOfMove.add(this);
        }
        return stop;
    }

}
