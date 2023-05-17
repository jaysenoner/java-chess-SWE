package com.company.control;


import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.pieces.King;
import com.company.model.pieces.Piece;

public class MoveController extends Controller{
    private GameModel gameModel;

    //todo: controllare se il re con una mossa si mette sotto scacco,
    // se un pezzo muovendosi mette sotto scacco il re,
    // se è possibile fare una arrocco, se il re è sotto scacco o scacco matto
    public boolean kingIsChecked(){
        for(Move m: gameModel.getTurn().getListOfPossibleMoves()){
            if(m.getEndSquare().getPiece().getClass() == King.class){
                return true;
            }
        }
        return false;
    }
    public boolean kingIsCheckMate(){
       return(kingIsChecked() && gameModel.getTurn().getKing().getPossibleMoves().isEmpty());
    }
    public void checkKingMovement(){
        Piece king= gameModel.getTurn().getKing();

    }
    public void checkPiecesMovement(){}

}
