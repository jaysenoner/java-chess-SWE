package com.company.model;

import com.company.model.pieces.King;
import com.company.model.pieces.Piece;

import java.util.ArrayList;

public class Player {
    private String name = "";
    private final Color color;
    private final Board board;
    private ArrayList<Move> listOfPossibleMoves;
    private ArrayList<Piece> listOfPieces;
    private Piece king;

    //costruttore con nome. Necessità di aver prima inizializzato un oggetto Board contenente tutti i pezzi in posizione
    //iniziale.
    public Player(String name, Board board, boolean white) {
        this.name = name;
        this.board = board;
        listOfPossibleMoves = new ArrayList<>();
        listOfPieces = new ArrayList<>();
        if(white) {
            king= board.squares[7][4].getPiece();
            this.color = Color.WHITE;
            for(int i = 7; i >= 6 ; i--)
                for(int j = 0; j < 8 ; j++)   {
                     listOfPieces.add(board.squares[i][j].getPiece());
                }
        }
        else{
            king= board.squares[0][4].getPiece();
            this.color = Color.BLACK;
            for(int i = 0; i < 2; i++)
                for(int j= 0 ; j < 8; j++)
                    listOfPieces.add(board.squares[i][j].getPiece());
        }
        calculateAllPossibleMoves();
    }
    //getter
    public ArrayList<Move> getListOfPossibleMoves() {
        return listOfPossibleMoves;
    }

    public ArrayList<Piece> getListOfPieces() {
        return listOfPieces;
    }


    //Metodo che per ogni pezzo bianco/nero(a seconda dello specifico player) calcola tutte le mosse POSSIBILI(non controlla la legalità)
    //e le inserisce nella lista listOfMoves.
    public void calculateAllPossibleMoves(){
        listOfPossibleMoves.clear();
        for(Piece p: listOfPieces){
                p.setPossibleMoves(board);
                listOfPossibleMoves.addAll(p.getPossibleMoves());
        }
    }
    //costruttore senza nome
    public Player(Board board, boolean white) {
        this.board = board;
        listOfPossibleMoves = new ArrayList<>();
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
    public Piece getKing() {
        return king;
    }
    public boolean isWhite(){
        return this.color == Color.WHITE;
    }

    public String getName(){
        return this.name;
    }
}
