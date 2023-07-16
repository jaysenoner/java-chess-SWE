package com.company.view;
import com.company.model.GameModel;
import com.company.model.Move;
import com.company.model.Square;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class Table {
    public JFrame getChessFrame() {
        return chessFrame;
    }

    private final JFrame chessFrame;
    private final Dimension frameDimension = new Dimension(650, 650);
    private static final String COLS = "ABCDEFGH";
    private JPanel chessBoardPanel;
    private JToolBar toolBar;
    private final JButton pgn;
    private final JButton newGame;
    private Color brown = new Color(74, 33, 0);
    private Color beige = new Color(161, 111, 62);
    public JButton getPgn() {
        return pgn;
    }
    public JButton getNewGame() {
        return newGame;
    }





    public Table() {
        this.chessFrame = new JFrame("Chess");
        this.chessFrame.setSize(frameDimension);
        this.chessFrame.setVisible(true);
        this.chessFrame.setResizable(false);

        this.toolBar = new JToolBar();
        this.newGame = new JButton("New Game");
        this.pgn = new JButton("Download PGN");
        chessFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    public void renderToolBar() {
        toolBar.add(newGame);
        toolBar.add(pgn);
        newGame.setEnabled(true);
        pgn.setEnabled(true);
        toolBar.setRollover(true);
        toolBar.setFloatable(false);
        // Text Area at the Center
        JTextArea ta = new JTextArea("Java Chess Game created by Jay Senoner and Sara Bernini");
        chessFrame.getContentPane().add(BorderLayout.NORTH, toolBar);
        chessFrame.getContentPane().add(BorderLayout.SOUTH, ta);
        chessFrame.setVisible(true);
    }



    public void initializeView(GameModel gameModel){
        renderToolBar();
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
                s.setName("");
                if(s.getPiece() != null) {
                    image = s.getPiece().getImageURL();
                    if(s.getPiece().getColor() == com.company.model.Color.BLACK){
                        s.setEnabled(false);
                        s.setDisabledIcon(new ImageIcon(image));
                    }
                    s.setIcon(new ImageIcon(image));
                }
                else{
                    s.setEnabled(false);
                    s.setDisabledIcon(new ImageIcon(image));
                }



                if(s.getColor() == com.company.model.Color.WHITE){
                    s.setBackground(beige);
                }else{
                    s.setBackground(brown);
                }

                chessBoardPanel.add(s);
            }
        }
        chessBoardPanel.setVisible(true);

    }
    public void repaintChessBoard(Square[][] squares, Boolean isWhite){
        for(Square[] ss : squares){
            for(Square s: ss){
                String image = "";
                if(s.getPiece() != null) {
                    image = s.getPiece().getImageURL();
                    s.setEnabled((!isWhite || s.getPiece().getColor() != com.company.model.Color.BLACK) &&
                            (isWhite || s.getPiece().getColor() != com.company.model.Color.WHITE));
                    s.setDisabledIcon(new ImageIcon(image)); // Aggiunto per far si che l'icona rimanga invariata disabilitando/abilitando un bottone contenente un pezzo
                }else{
                    s.setDisabledIcon(null);    //Se una casa non ha pezzo imposto la sua icona da disabilitato come null
                    s.setEnabled(false);
                }
                s.setIcon(new ImageIcon(image));
            }
        }
    }

    public void renderGrayPossibleEndSquares(ArrayList<Move> legalMoves) {
            for(Move move : legalMoves){
                move.getEndSquare().setBackground(Color.DARK_GRAY);
                move.getEndSquare().setEnabled(true);
            }
    }
    public void resetGraySquares(Square[][] chessBoardSquares){
        for(Square[] ss : chessBoardSquares) {
            for (Square s : ss) {
                if (s.getBackground() == Color.DARK_GRAY) {
                    if (s.getColor() == com.company.model.Color.WHITE) {
                        s.setBackground(beige);
                    } else {
                        s.setBackground(brown);
                    }
                }
            }
        }
    }

    public void showStaleMateAlert() {
        JOptionPane.showMessageDialog(chessFrame, "STALEMATE!");
    }
    public void showCheckMateAlert(Boolean isWhiteWinner) {
        String text= "CHECKMATE!";
        if(isWhiteWinner){
            text= text + " White player wins!";
        }
        else{
            text = text + " Black player wins!";
        }
        JOptionPane.showMessageDialog(chessFrame, text);
    }

    public void stopGame(Square[][] board) {
        for(Square[] squares: board){
            for(Square square: squares){
                square.setEnabled(false);
            }
        }
    }

    public String showPromotionDialog() {
        String[] options = {"Queen", "Knight", "Bishop", "Rook"};
        int x = JOptionPane.showOptionDialog(null, "Select the piece you want to promote into",
                "Promotion!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return options[x];
    }
}