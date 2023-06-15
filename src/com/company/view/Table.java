package com.company.view;

import com.company.model.Board;
import com.company.model.Move;
import com.company.model.Square;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private final JFrame chessFrame;
    private final Dimension frameDimension= new Dimension(600, 600);
    private static final String COLS = "ABCDEFGH";
    private Square[][] chessBoardSquares;
    private JPanel chessBoard;
    private Observer observer;

    public Table(Board board) {
        this.chessFrame = new JFrame("Chess");
        this.chessFrame.setSize(frameDimension);
        this.chessFrame.setVisible(true);
        this.chessBoardSquares= board.getSquares();
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: this.observer = new BoardObserver();

    }
    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu m1 = new JMenu("New Game");
        JMenu m2 = new JMenu("Download PGN");
        menuBar.add(m1);
        menuBar.add(m2);
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Game");
            }
        });
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This is a PGN file");
            }
        });
        // Text Area at the Center
        JTextArea ta = new JTextArea("Java Chess Game created by Jay Senoner and Sara Bernini");
        chessFrame.getContentPane().add(BorderLayout.NORTH, menuBar);
        chessFrame.getContentPane().add(BorderLayout.SOUTH, ta);
        chessFrame.setVisible(true);
    }
    public void createChessBoard(){
        //scacchiera matrice di bottoni
        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        chessFrame.add(chessBoard);

        Insets buttonMargin = new Insets(0,0,0,0);
        int n=8;
        for(Square[] ss : chessBoardSquares){
            chessBoard.add(new JLabel("" + (n--), SwingConstants.CENTER));
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
                if(s.getColor()== com.company.model.Color.WHITE){
                    s.setBackground(Color.WHITE);
                }else{
                    s.setBackground(Color.BLACK);
                }
                s.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        observer.update(s);
                    }
                });
                chessBoard.add(s);
            }
        }
        chessBoard.add(new JLabel(""));
        for (int i = 0; i < 8; i++) {
            chessBoard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }
        chessFrame.setVisible(true);
    }

    public void seePossibleMovement(Square s) {
            for(Move move : s.getPiece().getPossibleMoves()){
                move.getEndSquare().setBackground(Color.DARK_GRAY);
                move.getEndSquare().setEnabled(true);
            }
    }
    public void reset(){
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