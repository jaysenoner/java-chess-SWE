package com.company.model;

enum GameState{START, CHECK, CHECKMATE, TIE}
public class GameModel extends Subject{
    Player whitePlayer;
    Player blackPlayer;
    Boolean turn;
    Board board;
    Move[] listOfMovement;
    GameState state;

    public boolean movePiece(Move move){
        //TODO:IMPLEMENTARE MEGLIO (solo idea)
        if(checkLegalMove(move)) {
           // board.updateBoard(move);
            return true;
        }else {
            return false;
        }
    }
    public boolean checkLegalMove(Move move){
        //TODO:IMPLEMENTARE
        return true;
    }
}
