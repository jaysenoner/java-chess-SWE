package com.company.model;

import com.company.model.pieces.Piece;

import java.util.ArrayList;

public class Player {
    String name = "";
    Color color;
    Board board;
    ArrayList<Move> listOfMoves;
    ArrayList<Piece> listOfPieces;

    //costruttore con nome. Necessità di aver prima inizializzato un oggetto Board contenente tutti i pezzi in posizione
    //iniziale.
    public Player(String name, Board board, boolean white) {
        this.name = name;
        this.board = board;
        listOfMoves = new ArrayList<>();
        listOfPieces = new ArrayList<>();
        if(white) {
            this.color = Color.WHITE;
            for(int i = 7; i >= 6 ; i--)
                for(int j = 0; j < 8 ; j++)
                    listOfPieces.add(board.squares[i][j].getPiece());

        }
        else{
            this.color = Color.BLACK;
            for(int i = 0; i < 2; i++)
                for(int j= 0 ; j < 8; j++)
                    listOfPieces.add(board.squares[i][j].getPiece());
        }
        calculateAllPossibleMoves();
    }

    //Metodo che per ogni pezzo bianco/nero(a seconda dello specifico player) calcola tutte le mosse POSSIBILI(non controlla la legalità)
    //e le inserisce nella lista listOfMoves.
    private void calculateAllPossibleMoves(){
        listOfMoves.clear();
        for(Piece p: listOfPieces){
                p.setPossibleMoves(board);
                listOfMoves.addAll(p.getPossibleMoves());
        }
    }
    //costruttore senza nome
    public Player(boolean white) {
        if(white) {
            this.color = Color.WHITE;
        }else{
            this.color = Color.BLACK;
        }

    }

    public boolean isWhite(){
        return this.color == Color.WHITE;
    }

    public String getName(){
        return this.name;
    }
}
