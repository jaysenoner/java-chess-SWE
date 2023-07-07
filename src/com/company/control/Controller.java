package com.company.control;


import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.Player;
import com.company.model.Square;
import com.company.model.pieces.King;
import com.company.model.pieces.Piece;
import com.company.model.pieces.Rook;
import com.company.view.Table;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {
    private GameModel gameModel;
    private Table table;
    private Square[][] squares;
    public Controller() {
        super();
        gameModel = new GameModel();
        table = new Table(gameModel);
        squares = gameModel.getBoard().squares;
        table.createMenuBar();
        table.createChessBoard();

        //Setup controller
        table.getPgn().addActionListener(this);
        table.getNewGame().addActionListener(this);
        for(Square[] row: squares)
        {
            for(Square square: row)
            {
                square.addActionListener(this);
            }
        }





    }

    //todo: se è possibile fare una arrocco
    //todo: occhio che non si sa se il parametro hasmoved viene modificato dopo le mosse
    public boolean isShortCastlingPossible(){
        Player currentPlayer = gameModel.getTurn();
        return !currentPlayer.getKing().HasMoved() && !currentPlayer.getShortCastleRook().HasMoved();
    }
    public boolean isLongCastlingPossible(){
        Player currentPlayer = gameModel.getTurn();
        return !currentPlayer.getKing().HasMoved() && !currentPlayer.getLongCastleRook().HasMoved();
    }


    //TODO: Jay: non mi torna come mai si controlla le mosse del giocatore di turno per determinare lo scacco
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

    //TODO: Jay: Non mi torna questo metodo, se un pezzo muovendosi mette sotto scacco il proprio re, allora tutte le sue
    // mosse sono illegali
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


    public void update(Square s) {
        Square start = null, end;
        if( s.getPiece() != null){
            start = s;
            table.reset();
            table.seePossibleMovement(s);
        }
        if(s.getBackground() == Color.DARK_GRAY){
            end = s;
            Move move = new Move(start, end);
            //TODO: implementare metodo
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        update((Square) e.getSource());
    }
}
