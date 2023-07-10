package com.company.view;
import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.Square;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class Table {
    private final JFrame chessFrame;
    private final Dimension frameDimension = new Dimension(800, 800);
    private static final String COLS = "ABCDEFGH";
    private JPanel chessBoardPanel;
    private JMenuBar menuBar;
    private JMenu pgn;
    private JMenu newGame;
    private JButton[][] chessBoard;



    public JMenu getPgn() {
        return pgn;
    }

    public void setPgn(JMenu pgn) {
        this.pgn = pgn;
    }

    public JMenu getNewGame() {
        return newGame;
    }

    public void setNewGame(JMenu newGame) {
        this.newGame = newGame;
    }



    public Table() {
        this.chessFrame = new JFrame("Chess");
        this.chessFrame.setSize(frameDimension);
        this.chessFrame.setVisible(true);

        this.menuBar = new JMenuBar();
        this.newGame = new JMenu("New Game");
        this.pgn = new JMenu("Download PGN");
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void renderMenuBar() {
        menuBar.add(newGame);
        menuBar.add(pgn);

        // Text Area at the Center
        JTextArea ta = new JTextArea("Java Chess Game created by Jay Senoner and Sara Bernini");
        chessFrame.getContentPane().add(BorderLayout.NORTH, menuBar);
        chessFrame.getContentPane().add(BorderLayout.SOUTH, ta);
        chessFrame.setVisible(true);
    }



    public void initializeView(GameModel gameModel){
        renderMenuBar();
        renderChessBoardPanel(gameModel);
    }

    public void renderChessBoardPanel(GameModel gameModel){
        chessBoardPanel = new JPanel(new GridLayout(0, 9));
        chessBoardPanel.setBorder(new LineBorder(Color.BLACK));
        chessFrame.add(chessBoardPanel);

        renderChessBoard(gameModel);

        chessBoardPanel.add(new JLabel(""));
        for (int i = 0; i < 8; i++) {
            chessBoardPanel.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }
        chessFrame.setVisible(true);

    }

    public void renderChessBoard(GameModel gameModel){
        Square[][] squares = gameModel.getBoard().getSquares();
        Insets buttonMargin = new Insets(0,0,0,0);
        int n = 8;
        for(Square[] ss : squares){
            chessBoardPanel.add(new JLabel("" + (n--), SwingConstants.CENTER));
            for(Square s: ss){
                s.setMargin(buttonMargin);
                String image = "";
                if(s.getPiece() != null) {
                    image = s.getPiece().getImageURL();
                }else{
                    s.setEnabled(false);
                }
                ImageIcon icon = new ImageIcon(image);
                s.setIcon(icon);
                if(s.getColor() == com.company.model.Color.WHITE){
                    s.setBackground(Color.WHITE);
                }else{
                    s.setBackground(Color.BLACK);
                }

                chessBoardPanel.add(s);
            }
        }
        chessBoardPanel.setVisible(true);


    }
    public void repaintChessBoard(GameModel gameModel){
        Square[][] squares = gameModel.getBoard().getSquares();
        for(Square[] ss : squares){
            for(Square s: ss){
                String image = "";
                if(s.getPiece() != null) {
                    image = s.getPiece().getImageURL();
                }else{
                    s.setEnabled(false);
                }
                s.setIcon(new ImageIcon(image));
            }
        }
    }
    //TODO: Problema enorme con mosse legali.
    // se faccio ArrayList<Move> legalMoves = s.getPiece().getPossibleMoves(); allora funziona tutto(catture, mosse corrette ecc)
    // Mettendo il filterlegalMoves non funziona più niente che riguardi pedoni( e non solo )

    public void renderGrayPossibleEndSquares(Square s, GameModel gameModel) {
            s.getPiece().setPossibleMoves(gameModel.getBoard());

            ArrayList<Move> legalMoves = gameModel.filterLegalMoves(s.getPiece().getPossibleMoves());
            //ArrayList<Move> legalMoves = s.getPiece().getPossibleMoves();
            //TODO: JHIDCBFQERIU
            for(Move move : legalMoves){
                move.getEndSquare().setBackground(Color.DARK_GRAY);
                move.getEndSquare().setEnabled(true);
            }
    }
    public void resetGraySquares(GameModel gameModel){
        Square[][] chessBoardSquares = gameModel.getBoard().getSquares();
        for(Square[] ss : chessBoardSquares) {
            for (Square s : ss) {
                if (s.getBackground() == Color.DARK_GRAY) {
                    if (s.getColor() == com.company.model.Color.WHITE) {
                        s.setBackground(Color.WHITE);
                    } else {
                        s.setBackground(Color.BLACK);
                    }
                }
            }
        }
    }

}