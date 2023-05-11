package com.company;


import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Move;
import com.company.model.Square;
import com.company.model.pieces.Rook;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        // write your code here
        /*Board board = new Board();
        for (Square[] squares : board.getSquares()) {
            for (Square s : squares) {
                if (s.isOccupied()) {
                    s.getPiece().setPossibleMoves(board);
                    System.out.println(s.getPiece() + "" + s.getPiece().getPossibleMoves());
                }
            }
        }*/
        Board b= new Board();
        Rook rook=new Rook(Color.BLACK);
        b.squares[4][4].setPiece(rook);
        rook.setPossibleMoves(b);
        ArrayList<Move> listOfMove= rook.getPossibleMoves();
        for(Move m:listOfMove){
            System.out.println(m.getEndSquare().getPosition().getRow() +" "+ m.getEndSquare().getPosition().getCol());
        }

    }
}



