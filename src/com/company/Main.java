package com.company;


import com.company.model.Board;
import com.company.model.Square;


public class Main {

    public static void main(String[] args) {
	// write your code here
        Board board = new Board();
        board.getSquares()[1][1].getPiece().setPossibleMoves(board);
        System.out.println(board.getSquares()[1][1].getPiece().getPossibleMoves());
}}

