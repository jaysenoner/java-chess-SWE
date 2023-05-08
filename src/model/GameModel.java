package model;

import view.Observer;
enum GameState{START, CHECK, CHECKMATE, TIE}
public class GameModel extends Subject{
    Player whitePlayer;
    Player blackPlayer;
    Player inTurn;
    Board board;
    Move[] listOfMovement;
    GameState state;

    public boolean movePiece(Move move){
        //TODO:IMPLEMENTARE MEGLIO (solo idea)
        if(checkLegalMove(move)) {
            board.update(move, move.startSquare.piece);
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
