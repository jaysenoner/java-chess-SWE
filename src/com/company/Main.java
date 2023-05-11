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
                if(s.piece == null){
                    System.out.println("");
                }else {
                    s.piece.setPossibleMoves(board);
                    System.out.println(s.piece.getPossibleMoves());
                }
            }
        }
}}

