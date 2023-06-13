package com.company;


import com.company.model.*;
import com.company.model.pieces.Piece;
import com.company.model.pieces.Rook;
import com.company.view.Table;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        // write your code here
        GameModel gameModel= new GameModel();
        gameModel.getTable().createMenuBar();
        gameModel.getTable().createChessBoard();
        Move move= new Move(gameModel.getBoard().getSquare(0,1),gameModel.getBoard().getSquare(2,1) );
        gameModel.executeMove(move);
        gameModel.getTable().createMenuBar();
        gameModel.getTable().createChessBoard();

    }
}



