package com.company.model;

import com.company.model.pieces.King;
import com.company.model.pieces.Pawn;
import com.company.model.pieces.Piece;

import java.util.ArrayList;

public class Player {
    private final Color color;
    private Board board;
    private ArrayList<Move> listOfPossibleMoves;
    private ArrayList<Piece> listOfPieces;
    private Piece king;
    private Piece shortCastleRook;
    private Piece longCastleRook;
    private ArrayList<Move> enPassantMove;

    private ArrayList<Move> shortCastleMove;
    private ArrayList<Move> longCastleMove;

    //Necessità di aver prima inizializzato un oggetto Board contenente tutti i pezzi in posizione iniziale.
    public Player(Board board, boolean isWhite) {
        this.board = board;
        listOfPossibleMoves = new ArrayList<>();
        listOfPieces = new ArrayList<>();
        shortCastleMove = new ArrayList<>();
        longCastleMove = new ArrayList<>();
        enPassantMove = new ArrayList<>();
        if(isWhite) {
            this.color = Color.WHITE;
            king = board.squares[7][4].getPiece();
            shortCastleRook = board.squares[7][7].getPiece();
            longCastleRook = board.squares[7][0].getPiece();
            for(int i = 7; i >= 6 ; i--)
                for(int j = 0; j < 8 ; j++)   {
                     listOfPieces.add(board.squares[i][j].getPiece());
                }
        }
        else{
            this.color = Color.BLACK;
            king = board.squares[0][4].getPiece();
            shortCastleRook = board.squares[0][7].getPiece();
            longCastleRook = board.squares[0][0].getPiece();
            for(int i = 0; i < 2; i++)
                for(int j= 0 ; j < 8; j++)
                    listOfPieces.add(board.squares[i][j].getPiece());
        }
        generateCastlingMoves(isWhite);
    }

    private void generateCastlingMoves(boolean isWhite){

        Move shortKingMove;
        Move shortRookMove;
        Move longKingMove;
        Move longRookMove;
        if(isWhite){
            shortKingMove = new Move(board.squares[7][4],board.squares[7][6]);
            shortRookMove = new Move(board.squares[7][7],board.squares[7][5]);
            longKingMove = new Move(board.squares[7][4],board.squares[7][2]);
            longRookMove = new Move(board.squares[7][0],board.squares[7][3]);

        }
        else{
            shortKingMove = new Move(board.squares[0][4],board.squares[0][6]);
            shortRookMove = new Move(board.squares[0][7],board.squares[0][5]);
            longKingMove = new Move(board.squares[0][4],board.squares[0][2]);
            longRookMove = new Move(board.squares[0][0],board.squares[0][3]);

        }
        shortCastleMove.add(shortKingMove);
        shortCastleMove.add(shortRookMove);

        longCastleMove.add(longKingMove);
        longCastleMove.add(longRookMove);
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
    public King getKing() {
        return (King)king;
    }
    public boolean isWhite(){
        return this.color == Color.WHITE;
    }

    public Piece getShortCastleRook() {
        return shortCastleRook;
    }

    public Piece getLongCastleRook() {
        return longCastleRook;
    }

    public ArrayList<Move> getShortCastleMove() {
        return shortCastleMove;
    }

    public ArrayList<Move> getLongCastleMove() {
        return longCastleMove;
    }
     public ArrayList<Move> getEnPassantMove(){return  enPassantMove;}


}
