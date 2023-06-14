package com.company.control;


import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.Square;
import com.company.model.pieces.King;
import com.company.model.pieces.Piece;
import com.company.model.pieces.Queen;

import java.util.ArrayList;

public class MoveController extends Controller{
    private GameModel gameModel;

    public MoveController(GameModel gameModel) {
        super(gameModel);
    }

    //todo: se è possibile fare una arrocco
    //controllo se il re è sotto scacco
    public boolean kingIsChecked(){
        for(Move m: gameModel.getTurn().getListOfPossibleMoves()){
            if(m.getEndSquare().getPiece().getClass() == King.class){
                return true;
            }
        }
        return false;
    }
    //controllo se siamo in scacco matto
    public boolean kingIsCheckMate(){
       return(kingIsChecked() && gameModel.getTurn().getKing().getPossibleMoves().isEmpty());
    }
    // dato un pezzo controllo se muovendolo metto sotto scacco il re, se così fosse rimuovo quella mossa
    //dalle mosse possibili.
    public void checkPiecesMovement(Piece piece){
        ArrayList<Move> illegalMovement= new ArrayList<>();
        for(Move m: piece.getPossibleMoves()) {
            //try movement
            gameModel.getBoard().updateBoard(m);
            if(kingIsChecked()){
                illegalMovement.add(m);
            }
            Move reverseMove = new Move(m.getEndSquare(), m.getStartSquare());
            gameModel.getBoard().updateBoard(reverseMove);
        }
        for(Move m : illegalMovement) {
            piece.getPossibleMoves().remove(m);
        }
    }

}
