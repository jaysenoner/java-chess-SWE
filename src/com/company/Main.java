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
        Board board= new Board(false);
        Table table= new Table(board);
        table.createMenuBar();
        table.createChessBoard();

    }
}



