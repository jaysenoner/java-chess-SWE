package com.company.model;

import com.company.model.pieces.*;

import java.util.ArrayList;

public class Board {
    private boolean isFirstTurn;
    public Square[][] squares;


    public Board(boolean emptyBoard) {

        this.isFirstTurn = true;
        this.squares = new Square[8][8];
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

        if(!emptyBoard) {
            //costruisco i vari pezzi nelle case di partenza
            for (int i = 2; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    squares[i][j].setPiece(null);
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

    }


    public boolean addPiece(Square s, Piece p){
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
    public Square getSquare(int row, int col) {
        Coordinate c = new Coordinate(row, col);
        if (c.isValid()) {
            return squares[row][col];
        } else {
            return null;
        }
    }

    public void updateBoard(Move move){
        Piece p = move.getStartSquare().getPiece();
        p.setHasMoved();
        move.getStartSquare().setPiece(null);
        move.getEndSquare().setPiece(p);
    }
    public Square[][] getSquares(){
        return squares;
    }

    public Square getSquare(String letter, String number){
        return getSquare(new Coordinate(letter,number));

    }


}
