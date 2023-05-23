package com.company.view;

import com.company.model.Board;
import com.company.model.Square;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Table {
    private final JFrame chessFrame;
    private final Dimension frameDimension= new Dimension(600, 600);
    private static final String COLS = "ABCDEFGH";
    private Square[][] chessBoardSquares;
    private JPanel chessBoard;

    public Table(Board board) {
        this.chessFrame = new JFrame("Chess");
        this.chessFrame.setSize(frameDimension);
        this.chessFrame.setVisible(true);
        this.chessBoardSquares= board.getSquares();
        chessFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                s.setIcon(icon);
                if(s.getColor()== com.company.model.Color.WHITE){
                    s.setBackground(Color.WHITE);
                }else{
                    s.setBackground(Color.BLACK);
                }
                s.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(s.getPiece().getClass());
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
}