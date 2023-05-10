package com.company.model;

import com.company.model.pieces.*;

import java.util.ArrayList;

public class Board {
    int width =8;
    int height =8;
    boolean isFirstTurn;
    Square[][] squares;


    public Board() {

        this.isFirstTurn = true;
        this.squares = new Square[height][width];
        int count = 0;

        //Assegno i colori alle case

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinate c = new Coordinate(i, j);
                if ((count) % 2 == 0) {
                    squares[i][j] = new Square(c, Color.WHITE);
                } else {
                    squares[i][j] = new Square(c, Color.BLACK);
                }
                count++;
            }
            count++;
        }


        //costruisco i vari pezzi nelle case di partenza
        for (int i = 2; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = null;
            }
        }

        //Posiziono pedoni
        for (int j = 0; j < 8; j++) {
            squares[1][j].setPiece(new Pawn(Color.BLACK));
            squares[6][j].setPiece(new Pawn(Color.WHITE));
        }

        //Posiziono pezzi
        squares[0][0].setPiece(new Rook(Color.BLACK));
        squares[0][1].setPiece(new Knight(Color.BLACK));
        squares[0][2].setPiece(new Bishop(Color.BLACK));
        squares[0][3].setPiece(new Queen(Color.BLACK));
        squares[0][4].setPiece(new King(Color.BLACK));
        squares[0][5].setPiece(new Bishop(Color.BLACK));
        squares[0][6].setPiece(new Knight(Color.BLACK));
        squares[0][7].setPiece(new Rook(Color.BLACK));
        squares[7][0].setPiece(new Rook(Color.WHITE));
        squares[7][1].setPiece(new Knight(Color.WHITE));
        squares[7][2].setPiece(new Bishop(Color.WHITE));
        squares[7][3].setPiece(new Queen(Color.WHITE));
        squares[7][4].setPiece(new King(Color.WHITE));
        squares[7][5].setPiece(new Bishop(Color.WHITE));
        squares[7][6].setPiece(new Knight(Color.WHITE));
        squares[7][7].setPiece(new Rook(Color.WHITE));
    }

    //TODO:controllare metodo changeTurn
    public void changeTurn(){
        this.isFirstTurn = false;
        //ruota scacchiera
        Square s;
        for( int i=0; i<4; i++){
            for(int j = 0; j<8; j++) {
                s= this.squares[i][j] ;
                this.squares[i][j]= this.squares[7-i][7-j];
                this.squares[7-i][7-j] = s;
            }
        }
    }

    public boolean removePiece(Coordinate c){
        if(getSquare(c).isOccupied()) {
            getSquare(c).piece.setCaptured();
            getSquare(c).piece = null;
            return true;
        }else {
            return false;
        }
    }
    public boolean addPiece(Coordinate c, Piece p){
        Square s= getSquare(c);
        if(s.isOccupied()){
            return false;
        }else{
            s.setPiece(p);
            s.setOccupied(true);
            return true;
            }
    }
    public Square getSquare(Coordinate pos){
        return squares[pos.getRow()][pos.getCol()];
    }

    public void updateBoard(Move move, Piece p){
        move.startSquare.piece.setHasMoved(true);
        move.startSquare.piece= null;
        move.endSquare.piece = p;
    }
    public Square[][] getSquares(){
        return squares;
    }

    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in diagonale, o per tutta la lunghezza
    della diagonale (alfiere), o solo per una casella in diagonale (re). se checkAllDiagonal è true controlla tutta la diagonale.
     */
     public ArrayList<Move> diagonalMoves(Coordinate startPosition, boolean checkAllDiagonal){
         ArrayList<Move> listOfMove= new ArrayList<>();
         int col = startPosition.getCol();
         int row = startPosition.getRow();
         Square startSquare = getSquare(startPosition);

         boolean stop = false;
         int i = row, j = col;
         while (!stop && i < 8 && i >= 0 && j < 8 && j >= 0) {
             i++;
             j++;
             Move move= new Move(startSquare, squares[i][j]);
             stop = move.checkMove(listOfMove);
             if(!checkAllDiagonal){stop = true;}
         }
         stop = false;
         i = row;
         j = col;
         while (!stop && i < 8 && i >= 0 && j < 8 && j >= 0) {
             i--;
             j--;
             Move move= new Move(startSquare, squares[i][j]);
             stop = move.checkMove(listOfMove);
             if(!checkAllDiagonal){stop = true;}
         }
         stop = false;
         i = row;
         j = col;
         while (!stop && i < 8 && i >= 0 && j < 8 && j >= 0) {
             i++;
             j--;
             Move move= new Move(startSquare, squares[i][j]);
             stop = move.checkMove(listOfMove);
             if(!checkAllDiagonal){stop = true;}
         }
         stop = false;
         i = row;
         j = col;
         while (!stop && i < 8 && i >= 0 && j < 8 && j >= 0) {
             i--;
             j++;
             Move move= new Move(startSquare, squares[i][j]);
             stop = move.checkMove(listOfMove);
             if(!checkAllDiagonal){stop = true;}
         }
         return listOfMove;
     }
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in orizzontale,
    o per tutta la lunghezza della riga (torre), o solo per una casella (re). se checkAllRow è true controlla
    tutta la riga.*/
    public ArrayList<Move> horizontalMoves(Coordinate startPosition, boolean checkAllRow){
        ArrayList<Move> listOfMove= new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = getSquare(startPosition);

        boolean stop = false;
        int i = row, j = col;
        while (!stop && j < 8 && j >= 0) {
            j++;
            Move move= new Move(startSquare, squares[i][j]);
            stop = move.checkMove(listOfMove);
            if(!checkAllRow){stop = true;}
        }
        stop = false;
        j = col;
        while (!stop && j < 8 && j >= 0) {
            j--;
            Move move= new Move(startSquare, squares[i][j]);
            stop = move.checkMove(listOfMove);
            if(!checkAllRow){stop = true;}
        }
        return listOfMove;
    }
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in verticale,
    o per tutta la lunghezza della riga (torre), o solo per una casella (re). se checkAllCol è true controlla
    tutta la colonna.*/
    public ArrayList<Move> verticalMoves(Coordinate startPosition, boolean checkAllCol){
        ArrayList<Move> listOfMove= new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = getSquare(startPosition);

        boolean stop = false;
        int i = row, j = col;
        while (!stop && i < 8 && i >= 0) {
            i++;
            Move move= new Move(startSquare, squares[i][j]);
            stop = move.checkMove(listOfMove);
            if(!checkAllCol){stop = true;}
        }
        stop = false;
        i = row;
        while (!stop && i < 8 && i >= 0) {
            i--;
            Move move= new Move(startSquare, squares[i][j]);
            stop = move.checkMove(listOfMove);
            if(!checkAllCol){stop = true;}
        }
        return listOfMove;
    }
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in orizzontale,
    o per tutta la lunghezza della riga (torre), o solo per una casella (re). se allMoves è true controlla
    tutta la diagonale.*/
    public ArrayList<Move> pawnMovement(Coordinate startPosition){
        ArrayList<Move> listOfMove= new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = getSquare(startPosition);
        if (startSquare.getPiece().getColor() == Color.WHITE){
            if(row >0 && col >0) {
                if (squares[row - 1][col - 1].isOccupied() && squares[row - 1][col - 1].getPiece().getColor() == Color.BLACK) {
                    listOfMove.add(new Move(startSquare, squares[row - 1][col - 1]));
                }
            }
            if(row >0 && col <7) {
                if (squares[row - 1][col + 1].isOccupied() && squares[row - 1][col + 1].getPiece().getColor() == Color.BLACK) {
                    listOfMove.add(new Move(startSquare, squares[row - 1][col + 1]));
                }
            }
        }else {
            if (row < 7 && col > 0) {
                if (squares[row + 1][col - 1].isOccupied() && squares[row + 1][col - 1].getPiece().getColor() == Color.WHITE) {
                    listOfMove.add(new Move(startSquare, squares[row + 1][col - 1]));
                }
            }
            if (row < 7 && col < 7) {
                if (squares[row + 1][col + 1].isOccupied() && squares[row + 1][col + 1].getPiece().getColor() == Color.WHITE) {
                    listOfMove.add(new Move(startSquare, squares[row + 1][col + 1]));
                }
            }
        }
        if(squares[row][col].getPiece().isFirstMove()  && !squares[row +1][col].isOccupied() && !squares[row +2][col].isOccupied() ){
            listOfMove.add(new Move(startSquare, squares[row +2][col]));
        }
        if(!squares[row +1][col].isOccupied()){
            listOfMove.add(new Move(startSquare, squares[row +1][col]));
        }
        return listOfMove;
    }
    //TODO: completare! manca controllare se posizione occupata da ns pezzo, mancano dei movimenti
    public ArrayList<Move> knightMovement(Coordinate startPosition){
        ArrayList<Move> listOfMove = new ArrayList<>();
        Coordinate c = new Coordinate(startPosition.getRow() +1, startPosition.getCol() +2);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        c.setRow(startPosition.getRow() +2);
        c.setCol(startPosition.getCol() +1);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        c.setRow(startPosition.getRow() -2);
        c.setCol(startPosition.getCol() -1);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        c.setRow(startPosition.getRow() -1);
        c.setCol(startPosition.getCol() -2);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        c.setRow(startPosition.getRow() -2);
        c.setCol(startPosition.getCol() +1);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        c.setRow(startPosition.getRow() -1);
        c.setCol(startPosition.getCol() +2);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        c.setRow(startPosition.getRow() +2);
        c.setCol(startPosition.getCol() -1);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        c.setRow(startPosition.getRow() +1);
        c.setCol(startPosition.getCol() -2);
        if (c.isValid()){
            listOfMove.add(new Move(getSquare(startPosition),getSquare(c) ));
        }
        return listOfMove;
    }
}


