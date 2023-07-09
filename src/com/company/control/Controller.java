package com.company.control;


import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.Player;
import com.company.model.Square;
import com.company.model.pieces.King;
import com.company.model.pieces.Piece;
import com.company.view.Table;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {
    private GameModel gameModel;
    private Table table;
    private Square[][] squares;
    private Square currentStartSquare;
    private Square currentEndSquare;

    private Thread gameLoop;
    private boolean isRunning;



    public Controller() {
        super();
        gameModel = new GameModel();
        table = new Table();
        squares = gameModel.getBoard().squares;

        /*table.renderMenuBar();
        table.renderChessBoard(gameModel);
         */
        table.initializeView(gameModel);

        //Setup listeners
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

    public boolean isShortCastlingPossible(){
        Player currentPlayer = gameModel.getTurn();
        boolean castle = !currentPlayer.getKing().HasMoved() && !currentPlayer.getShortCastleRook().HasMoved();
        if(currentPlayer.isWhite())
        {
            return castle && (!squares[7][6].isOccupied() && !squares[7][5].isOccupied());
        }
        else {
            return castle && (!squares[0][6].isOccupied() && !squares[0][5].isOccupied());
        }
    }

    public boolean isLongCastlingPossible(){
        Player currentPlayer = gameModel.getTurn();
        boolean castle = !currentPlayer.getKing().HasMoved() && !currentPlayer.getShortCastleRook().HasMoved();
        if(currentPlayer.isWhite())
        {
            return castle && (!squares[7][1].isOccupied() && !squares[7][2].isOccupied() && !squares[7][3].isOccupied());
        }
        else {
            return castle && (!squares[0][1].isOccupied() && !squares[0][2].isOccupied() && !squares[0][3].isOccupied());
        }
    }

    //controllo se il proprio re è sotto scacco
    public boolean kingIsChecked(){
        if (gameModel.getTurn().isWhite()) {
            for (Move m : gameModel.getBlackPlayer().getListOfPossibleMoves()) {
                if (m.getEndSquare().getPiece().getClass() == King.class) {
                    gameModel.getTurn().getKing().setChecked(true);
                    return true;
                }
            }
        }else{
            for (Move m : gameModel.getWhitePlayer().getListOfPossibleMoves()) {
                if (m.getEndSquare().getPiece().getClass() == King.class) {
                    gameModel.getTurn().getKing().setChecked(true);
                    return true;
                }
            }
        }
            return false;
    }
    //controllo se siamo in scacco matto
    public boolean kingIsCheckMated(){
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

    //TODO: ESTENDERE CLASSE OBSERVER E FARE OVERRIDE DI UPDATE
    public void updatePossibleEndSquares(Square s) {
        if(s.getPiece() != null){
            //checkPiecesMovement(s.getPiece());
            table.resetGraySquares(gameModel);
            table.renderGrayPossibleEndSquares(s);
        }

        /*
        if(s.getBackground() == Color.DARK_GRAY){
            end = s;
            Move move = new Move(start, end);
            gameModel.executeMove(move);

        }

         */
    }


    //TODO: Diversificare le azioni
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //Controllo se è stato premuto un quadrato nella scacchiera
        if (source.getClass() == Square.class)
            if(((Square) source).getBackground() != Color.DARK_GRAY) {
                currentStartSquare = (Square) source;
                updatePossibleEndSquares(currentStartSquare);
            }
            else if(((Square) source).getBackground() == Color.DARK_GRAY){
               currentEndSquare = (Square) source;
               gameModel.executeMove(new Move(currentStartSquare, currentEndSquare));
               table.repaintAll(gameModel);

            }
    }




    public void setupGameLoop(){
        gameLoop = new Thread(()->{
            while(isRunning){

                //TODO:QUI CALCOLO TUTTE LE MOSSE LEGALI,E ASPETTO MOSSA DEL GIOCATORE DI TURNO

                //TODO:DOPO AVER RICEVUTO LA MOSSA,FACCIO UPDATE BOARD

                //TODO: ADESSO RIDISEGNO LA VIEW IN BASE ALLE MODIFICHE SULLA BOARD

            }

        });
    }
}
