package com.company.model;
import com.company.model.Color;

import com.company.model.pieces.*;

public class Board {
    int width =8;
    int height =8;
    boolean isFirstTurn;
    Square[][] squares;


    public Board() {
        this.isFirstTurn = true;
        this.squares= new Square[height][width];
        int count = 0;

        //Assegno i colori alle case

        for(int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinate c= new Coordinate(i,j);
                if( (count)%2 ==0){
                    squares[i][j]= new Square(c, Color.WHITE);
                }else{
                    squares[i][j]= new Square(c, Color.BLACK);
                }
                count++;
            }
            count++;
        }


        //costruisco i vari pezzi nelle case di partenza
        for(int i=2; i<5; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = null;
            }
        }

        //Posiziono pedoni
        for(int j = 0; j<8; j++){
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


}
