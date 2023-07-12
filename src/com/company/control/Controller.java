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



    public Controller() {
        super();
        gameModel = new GameModel();
        table = new Table();
        squares = gameModel.getBoard().squares;
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
        gameModel.getWhitePlayer().calculateAllPossibleMoves();
        gameModel.getBlackPlayer().calculateAllPossibleMoves();
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
        boolean castle = !currentPlayer.getKing().HasMoved() && !currentPlayer.getLongCastleRook().HasMoved();
        if(currentPlayer.isWhite())
        {
            return castle && (!squares[7][1].isOccupied() && !squares[7][2].isOccupied() && !squares[7][3].isOccupied());
        }
        else {
            return castle && (!squares[0][1].isOccupied() && !squares[0][2].isOccupied() && !squares[0][3].isOccupied());
        }
    }


    //TODO: ESTENDERE CLASSE OBSERVER E FARE OVERRIDE DI UPDATE
    public void updatePossibleEndSquares(Square s) {
        if(s.getPiece() != null){
            table.resetGraySquares(gameModel);
            table.renderGrayPossibleEndSquares(s, gameModel);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //Controllo se è stato premuto un quadrato nella scacchiera
        if (source.getClass() == Square.class) {

            if (((Square) source).getBackground() != Color.DARK_GRAY &&((Square) source).getPiece() != null) {
                currentStartSquare = (Square) source;
                updatePossibleEndSquares(currentStartSquare);
            }
            else if (((Square) source).getBackground() == Color.DARK_GRAY) {

                currentEndSquare = (Square) source;
                Move moveToExecute = new Move(currentStartSquare,currentEndSquare);
                //System.out.println(moveToExecute.getMoveInChessNotation());
                gameModel.executeMove(moveToExecute);
                table.repaintChessBoard(gameModel);

                if(gameModel.kingIsCheckMated()){
                    table.stopGame(gameModel);
                    if (gameModel.getTurn().isWhite()){
                        table.showCheckMateAlert(Color.BLACK);
                    }else{
                        table.showCheckMateAlert(Color.WHITE);
                    }
                }

                if(gameModel.isStaleMate()){
                    table.showStaleMateAlert();
                    table.stopGame(gameModel);
                }
            }
        }
    }

}
