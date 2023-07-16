package com.company.control;
import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.Player;
import com.company.model.Square;
import com.company.model.pieces.*;
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
    //metodo che controlla se l'arrocco è possibile e in caso affermativo coloro di grigio la casa in cui si sposterà
    // il re in base al tipo di arrocco
    public void showCastling(){
        if(gameModel.isShortCastlingLegal()){
            Square shortCastleSquare = gameModel.getTurn().getShortCastleMove().get(0).getEndSquare();
            shortCastleSquare.setName("short_castle_square");
            shortCastleSquare.setBackground(Color.DARK_GRAY);
            shortCastleSquare.setEnabled(true);
        }
        if(gameModel.isLongCastlingLegal()){
            Square longCastleSquare = gameModel.getTurn().getLongCastleMove().get(0).getEndSquare();
            longCastleSquare.setName("long_castle_square");
            longCastleSquare.setBackground(Color.DARK_GRAY);
            longCastleSquare.setEnabled(true);
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
                //Se è stato selezionato il re, controllo che l'arrocco sia possibile
                if(((Square) source).getPiece().getClass() == King.class){
                    showCastling();
                }
                ArrayList<Move> enPassantMoves = gameModel.getTurn().getEnPassantMove();
                if(!enPassantMoves.isEmpty()) {
                    enPassantMoves = gameModel.checkLegacyEnPassant(gameModel.getTurn().getEnPassantMove());
                    if (enPassantMoves.size()>0 && source == enPassantMoves.get(0).getStartSquare()) {
                        showEnPassant(enPassantMoves.get(0));
                    }
                    if (enPassantMoves.size()==2 && source == enPassantMoves.get(1).getStartSquare()) {
                        showEnPassant(enPassantMoves.get(1));
                    }
                }
            }

            else if (((Square) source).getBackground() == Color.DARK_GRAY) {
                if(((Square) source).getName().equals("short_castle_square"))
                {
                    gameModel.executeCastlingMove(true);
                }
                else if(((Square) source).getName().equals("long_castle_square")){
                    gameModel.executeCastlingMove(false);
                }
                else {
                    currentEndSquare = (Square) source;
                    Move moveToExecute = new Move(currentStartSquare, currentEndSquare);
                    gameModel.executeMove(moveToExecute);
                    if(currentEndSquare.getPiece().getClass() == Pawn.class &&
                            (currentEndSquare.getPosition().getRow()== 0 ||currentEndSquare.getPosition().getRow()== 7)){
                        //Mostro OptionDialog per selezionare il pezzo
                        String promotionPiece = table.showPromotionDialog();
                        promotePawn(currentEndSquare, promotionPiece);

                    }
                }
                if(((Square) source).getName().equals("en_passant_square")){
                    gameModel.executeEnPassant((Square) source);
                }
                table.repaintChessBoard(gameModel.getBoard().getSquares(), gameModel.getTurn().isWhite());
                table.resetGraySquares(gameModel.getBoard().getSquares());

                //contrtollo se il giocatore ha subito scacco matto, in caso arresto il gioco e stampo un alert
                if(gameModel.kingIsCheckMated()){
                    table.stopGame(gameModel.getBoard().getSquares());
                    table.showCheckMateAlert(gameModel.getTurn().isWhite());
                }

                //contrtollo se siamo in stato di stallo, in caso arresto il gioco e stampo un alert
                if(gameModel.isStaleMate()){
                    table.showStaleMateAlert();
                    table.stopGame(gameModel.getBoard().getSquares());
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

    private void showEnPassant(Move move) {
        Square enPassantSquare= move.getEndSquare();
        enPassantSquare.setName("en_passant_square");
        enPassantSquare.setBackground(Color.DARK_GRAY);
        enPassantSquare.setEnabled(true);
    }

    private void promotePawn(Square currentEndSquare, String promotionPiece ) {
        Player playerToPromote;
        ArrayList<String> movesDone = gameModel.getMovesDone();
        String lastMove = movesDone.get(movesDone.size()-1);
        if(gameModel.getTurn().isWhite()){
            playerToPromote = gameModel.getBlackPlayer();
        }else{
            playerToPromote = gameModel.getWhitePlayer();
        }
        playerToPromote.getListOfPieces().remove(currentEndSquare.getPiece());
        gameModel.getBoard().removePiece(currentEndSquare);

        Piece newPiece = null;
        switch (promotionPiece) {
            case "Queen" -> {
                newPiece = new Queen(playerToPromote.getKing().getColor());
                movesDone.set(movesDone.size()-1, lastMove + "=Q");
            }
            case "Knight" -> {
                newPiece = new Knight(playerToPromote.getKing().getColor());
                movesDone.set(movesDone.size()-1, lastMove + "=N");
            }
            case "Bishop" -> {
                newPiece = new Bishop(playerToPromote.getKing().getColor());
                movesDone.set(movesDone.size()-1, lastMove + "=B");
            }
            case "Rook" -> {
                newPiece = new Rook(playerToPromote.getKing().getColor());
                movesDone.set(movesDone.size()-1, lastMove + "=R");
            }
        }
        gameModel.getBoard().addPiece(currentEndSquare, newPiece);
        playerToPromote.getListOfPieces().add(newPiece);

        gameModel.getBlackPlayer().calculateAllPossibleMoves();
        gameModel.getWhitePlayer().calculateAllPossibleMoves();

    }


}
