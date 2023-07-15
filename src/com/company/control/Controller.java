package com.company.control;


import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.Player;
import com.company.model.Square;
import com.company.model.pieces.King;
import com.company.view.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        addListeners();
        gameModel.getWhitePlayer().calculateAllPossibleMoves();
        gameModel.getBlackPlayer().calculateAllPossibleMoves();
    }

    private void addListeners(){
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




    //TODO: ESTENDERE CLASSE OBSERVER E FARE OVERRIDE DI UPDATE
    public void updatePossibleEndSquares(Square s) {
        if(s.getPiece() != null){
            table.resetGraySquares(gameModel.getBoard().getSquares());
            table.renderGrayPossibleEndSquares(gameModel.filterLegalMoves(s.getPiece().getPossibleMoves()));
        }

    }
    private void startNewGame(){
        gameModel = new GameModel();
        JFrame frame = table.getChessFrame();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        table = new Table();
        squares = gameModel.getBoard().squares;
        table.initializeView(gameModel);
        //Setup listeners
        addListeners();
        gameModel.getWhitePlayer().calculateAllPossibleMoves();
        gameModel.getBlackPlayer().calculateAllPossibleMoves();
    }

    private void generatePGNFile(){
        ArrayList<String> movesInPGN = gameModel.getMovesInPgn();
        try {
            File pgnFile = new File("chess_game.pgn");
            if(pgnFile.createNewFile()){
                System.out.println("File pgn creato con successo");
            }
            else{
                System.out.println("File già esistente, il file verrà sovrascritto");
            }
            FileWriter writer = new FileWriter("chess_game.pgn");
            for(String move: movesInPGN)
            {
                writer.write(move + " ");
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println("Errore nella creazione del file");
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        boolean kingClicked;
        //Controllo se è stato premuto un quadrato nella scacchiera
        if (source.getClass() == Square.class) {

            if (((Square) source).getBackground() != Color.DARK_GRAY &&((Square) source).getPiece() != null) {
                kingClicked = false;
                currentStartSquare = (Square) source;
                updatePossibleEndSquares(currentStartSquare);
                //Se è stato selezionato il re, controllo che l'arrocco sia possibile, e in caso affermativo coloro di grigio la casa in cui si sposterà il re
                if(((Square) source).getPiece().getClass() == King.class){
                    kingClicked = true;
                    if(gameModel.isShortCastlingPossible()){    //TODO:sostituire short/longcastlingPossible con Legal
                        Square shortCastleSquare = gameModel.getTurn().getShortCastleMove().get(0).getEndSquare();
                        shortCastleSquare.setBackground(Color.DARK_GRAY);
                        shortCastleSquare.setName("short_castle_square");
                        shortCastleSquare.setEnabled(true);
                    }
                    if(gameModel.isLongCastlingPossible()){
                        Square longCastleSquare = gameModel.getTurn().getLongCastleMove().get(0).getEndSquare();
                        longCastleSquare.setBackground(Color.DARK_GRAY);
                        longCastleSquare.setName("long_castle_square");
                        longCastleSquare.setEnabled(true);

                    }

                }
            }
            else if (((Square) source).getBackground() == Color.DARK_GRAY) {
                if(((Square) source).getName().equals("short_castle_square"))
                {
                    gameModel.executeCastlingMove(true);
                    table.repaintChessBoard(gameModel);
                }
                else if(((Square) source).getName().equals("long_castle_square")){
                    gameModel.executeCastlingMove(false);
                    table.repaintChessBoard(gameModel);
                }
                else {
                    currentEndSquare = (Square) source;
                    Move moveToExecute = new Move(currentStartSquare, currentEndSquare);
                    //System.out.println(moveToExecute.getMoveInChessNotation());
                    gameModel.executeMove(moveToExecute);
                    table.repaintChessBoard(gameModel);
                }

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
        //Controllo se è stato premuto un bottone della ToolBar
        if(source.getClass() == JButton.class){
           if( ((JButton) source).getText().equals("New Game")){

               startNewGame();
           }
           else if( ((JButton) source).getText().equals("Download PGN")){
                System.out.println(gameModel.getMovesInPgn());
                generatePGNFile();
           }
        }
    }




}
