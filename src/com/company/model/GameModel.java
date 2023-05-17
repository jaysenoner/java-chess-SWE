package com.company.model;

import java.util.ArrayList;

enum GameState{START, INPLAY, CHECK, CHECKMATE, TIE}
public class GameModel extends Subject{
    private Player whitePlayer;
    private Player blackPlayer;
    private Color turn;
    private Board board;
    private ArrayList<Move> listOfMovement;
    private GameState state;

    public GameModel() {
        this.board = new Board(false);
        this.whitePlayer = new Player(board, true);
        this.blackPlayer = new Player(board, false);
        this.turn = Color.WHITE ;
        this.listOfMovement = new ArrayList<Move>();
        this.state = GameState.START;
    }

    //getter
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Color getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Move> getListOfMovement() {
        return listOfMovement;
    }

    public GameState getState() {
        return state;
    }
    public void changeTurn() {
        if(turn == Color.WHITE){
            turn= Color.BLACK;
        }else turn= Color.WHITE;
    }

    public void movePiece(Move move){
        board.updateBoard(move);
        listOfMovement.add(move);
        changeTurn();
        if(state == GameState.START){
            state= GameState.INPLAY;
        }
    }
}
