package com.company.model;

import com.company.control.Controller;
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
        turn.calculateAllPossibleMoves();
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



}
