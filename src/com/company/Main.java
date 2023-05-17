package com.company;


import com.company.model.*;
import com.company.model.pieces.Piece;
import com.company.model.pieces.Rook;

import java.util.ArrayList;
import java.util.Arrays;


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
        }
        Board b= new Board(false);
        Rook rook=new Rook(Color.BLACK);
        b.squares[4][4].setPiece(rook);
        rook.setPossibleMoves(b);
        ArrayList<Move> listOfMove= rook.getPossibleMoves();
        for(Move m:listOfMove){
            System.out.println(m.getEndSquare().getPosition().getRow() +" "+ m.getEndSquare().getPosition().getCol());
        }*/
        GameModel gameModel= new GameModel();
        for(Square[] ls :gameModel.getBoard().getSquares()){
            for(Square s: ls){
                System.out.print(s.getPiece()+" ");
            }
            System.out.println("");

        }
        Move m = gameModel.getBoard().getSquares()[6][3].getPiece().getPossibleMoves().get(0);
        gameModel.movePiece(m);
        for(Square[] ls :gameModel.getBoard().getSquares()){
            for(Square s: ls){
                System.out.print(s.getPiece()+" ");
            }
            System.out.println("");

        }







    }
}



