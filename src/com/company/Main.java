package com.company;


import com.company.model.Board;
import com.company.model.Square;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Board board = new Board();
        for(Square[] square:board.getSquares()) {
            for (Square s : square) {
                if(s.getPiece() == null){
                    System.out.println("");
                }else {
                    s.getPiece().setPossibleMoves(board);
                    System.out.println(s.getPiece().getPossibleMoves());
                }
            }
        }
}}

