package com.company.model;

import com.company.control.Controller;
import com.company.model.pieces.King;
import com.company.model.pieces.Piece;
import com.company.view.Table;

import java.util.ArrayList;

enum GameState{START, INPLAY, CHECK, CHECKMATE, TIE}
public class GameModel{
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player turn;
    private final Board board;
    private ArrayList<String> movesDone;
    private GameState state;
    public Controller gameController;





    public GameModel() {
        this.board = new Board(false);
        this.whitePlayer = new Player(board, true);
        this.blackPlayer = new Player(board, false);
        this.turn = whitePlayer ;
        this.movesDone = new ArrayList<>();
        this.state = GameState.START;


        //TODO: controllare game controller e observer
    }

    //getter
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }



    public ArrayList<String> getMovesDone() {
        return movesDone;
    }

    public GameState getState() {
        return state;
    }

    public void changeTurn() {
        if(turn.isWhite()){
            turn = blackPlayer;
        }else turn = whitePlayer;
       // turn.calculateAllPossibleMoves();
    }


    //metodo che data una mossa legale aggiorna la lista dei pezzi in caso di cattura e aggiorna la scacchiera e cambia il turno
    public void executeMove(Move move){
        if(move.getEndSquare().isOccupied()){
            if(turn.isWhite()){
                blackPlayer.getListOfPieces().remove(move.getEndSquare().getPiece());
            }else{
                whitePlayer.getListOfPieces().remove(move.getEndSquare().getPiece());
            }
        }
        movesDone.add(move.getMoveInChessNotation());
        board.updateBoard(move);
        changeTurn();
        if(state == GameState.START){
            state= GameState.INPLAY;
        }
    }

    public void printMovesDone(){
        for(String move: movesDone){
            System.out.println(move);
        }
    }

    //TODO: metodo problematico
    public ArrayList<Move> filterLegalMoves(ArrayList<Move> possibleMoves){
        ArrayList<Move> illegalMovement= new ArrayList<>();
        for(Move m: possibleMoves) {
            //try movement
            this.getBoard().updateBoard(m);
            if(kingIsChecked()){
                illegalMovement.add(m);
            }
            Move reverseMove = new Move(m.getEndSquare(), m.getStartSquare());
            this.getBoard().updateBoard(reverseMove);

        }



        for(Move m : illegalMovement) {
            possibleMoves.remove(m);
        }
        return possibleMoves;
    }

    //Calcolo tutte le mosse possibili del player avversario per determinare se esiste una mossa che attacca direttamente
    // il re del player corrente
    public boolean kingIsChecked(){
        if (this.turn.isWhite()) {
            this.blackPlayer.calculateAllPossibleMoves();
            for (Move move : this.blackPlayer.getListOfPossibleMoves()) {
                if(move.getEndSquare().getPiece() != null)
                if (move.getEndSquare().getPiece().getClass() == King.class) {
                    //this.whitePlayer.getKing().setChecked(true);
                    return true;
                }
            }
        }else{
            this.whitePlayer.calculateAllPossibleMoves();
            for (Move move : this.getWhitePlayer().getListOfPossibleMoves()) {
                if(move.getEndSquare().getPiece() != null)
                if (move.getEndSquare().getPiece().getClass() == King.class) {
                    //this.getTurn().getKing().setChecked(true);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean kingIsCheckMated(){
        return(kingIsChecked() && this.getTurn().getKing().getPossibleMoves().isEmpty());
    }



}
