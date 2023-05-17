package com.company.model;

import com.company.model.pieces.Pawn;
import com.company.model.pieces.Piece;

import java.util.ArrayList;

public class Move {
    public Square getStartSquare() {
        return startSquare;
    }

    private final Square startSquare;

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
            if (isOccupiedByAllyPiece()) {
                stop = true;
            } else if (isOccupiedByEnemyPiece()) {
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

    public boolean isOccupiedByEnemyPiece(){
        return endSquare.isOccupied()&& startSquare.getPiece().getColor() != endSquare.getPiece().getColor();
    }
    public boolean isOccupiedByAllyPiece(){
        return endSquare.isOccupied() && startSquare.getPiece().getColor() == endSquare.getPiece().getColor();
    }

    public boolean isValid(){
        return (startSquare != null && endSquare!= null && startSquare.getPosition().isValid() && endSquare.getPosition().isValid()) ;
    }

    //Metodo che data una mossa legale(quindi gia controllata a priori) ne determina l'espressione in notazione algebrica
    // DA CHIAMARE PRIMA CHE VENGA FATTO L'UPDATE DELLA SCACCHIERA, altrimenti non torna.
    // Per torre e cavallo scrive anche la colonna di appartenenza del pezzo, al fine di evitare ambiguità sulla mossa effettuata
    public String getMoveInChessNotation(){

        String moveInChessNotation;
        String letter = startSquare.getPiece().getRightLetterForChessNotation();

          if(letter.equals("") || letter.equals("R") || letter.equals("N"))
              moveInChessNotation = letter +
                      startSquare.getPosition().colToChessNotation();
          else moveInChessNotation = letter;
          if(endSquare.isOccupied())
              moveInChessNotation = moveInChessNotation.concat("x" + endSquare.getPosition().fromIndexToChessNotation());
          else moveInChessNotation = moveInChessNotation.concat(endSquare.getPosition().fromIndexToChessNotation());
        return moveInChessNotation;

    }

}
