package tests;

import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Move;
import com.company.model.pieces.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    public Board b;

    @BeforeEach
    void setUp() {
        b = new Board(false);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void allColorsShouldBeCorrect(){

        for(int i = 0; i < 8 ; i++)
            for(int j = 0; j < 7; j += 2){
                if(i % 2 == 0) {
                    assertEquals(b.squares[i][j].getColor(), Color.WHITE);
                    assertEquals(b.squares[i][j + 1].getColor(), Color.BLACK);
                }
                else {
                    assertEquals(b.squares[i][j].getColor(), Color.BLACK);
                    assertEquals(b.squares[i][j + 1].getColor(), Color.WHITE);
                }
            }
    }
    @Test
    void allPiecesShouldBePlacedCorrectly(){

        assertEquals(b.squares[0][0].getPiece().getClass(), Rook.class);
        assertEquals(b.squares[0][0].getPiece().getColor(), Color.BLACK);

        assertEquals(b.squares[0][1].getPiece().getClass(), Knight.class);
        assertEquals(b.squares[0][1].getPiece().getColor(), Color.BLACK);

        assertEquals(b.squares[0][2].getPiece().getClass(), Bishop.class);
        assertEquals(b.squares[0][2].getPiece().getColor(), Color.BLACK);

        assertEquals(b.squares[0][3].getPiece().getClass(), Queen.class);
        assertEquals(b.squares[0][3].getPiece().getColor(), Color.BLACK);

        assertEquals(b.squares[0][4].getPiece().getClass(), King.class);
        assertEquals(b.squares[0][4].getPiece().getColor(), Color.BLACK);

        assertEquals(b.squares[0][5].getPiece().getClass(), Bishop.class);
        assertEquals(b.squares[0][5].getPiece().getColor(), Color.BLACK);

        assertEquals(b.squares[0][6].getPiece().getClass(), Knight.class);
        assertEquals(b.squares[0][6].getPiece().getColor(), Color.BLACK);

        assertEquals(b.squares[0][7].getPiece().getClass(), Rook.class);
        assertEquals(b.squares[0][7].getPiece().getColor(), Color.BLACK);

        for(int j = 0 ; j < 8 ; j++){
            assertEquals(b.squares[1][j].getPiece().getClass(), Pawn.class);
            assertEquals(b.squares[1][j].getPiece().getColor(), Color.BLACK);
        }

        assertEquals(b.squares[7][0].getPiece().getClass(), Rook.class);
        assertEquals(b.squares[7][0].getPiece().getColor(), Color.WHITE);

        assertEquals(b.squares[7][1].getPiece().getClass(), Knight.class);
        assertEquals(b.squares[7][1].getPiece().getColor(), Color.WHITE);

        assertEquals(b.squares[7][2].getPiece().getClass(), Bishop.class);
        assertEquals(b.squares[7][2].getPiece().getColor(), Color.WHITE);

        assertEquals(b.squares[7][3].getPiece().getClass(), Queen.class);
        assertEquals(b.squares[7][3].getPiece().getColor(), Color.WHITE);

        assertEquals(b.squares[7][4].getPiece().getClass(), King.class);
        assertEquals(b.squares[7][4].getPiece().getColor(), Color.WHITE);

        assertEquals(b.squares[7][5].getPiece().getClass(), Bishop.class);
        assertEquals(b.squares[7][5].getPiece().getColor(), Color.WHITE);

        assertEquals(b.squares[7][6].getPiece().getClass(), Knight.class);
        assertEquals(b.squares[7][6].getPiece().getColor(), Color.WHITE);

        assertEquals(b.squares[7][7].getPiece().getClass(), Rook.class);
        assertEquals(b.squares[7][7].getPiece().getColor(), Color.WHITE);

        for(int j = 0 ; j < 8 ; j++){
            assertEquals(b.squares[6][j].getPiece().getClass(), Pawn.class);
            assertEquals(b.squares[6][j].getPiece().getColor(), Color.WHITE);
        }

    }

    @Test
    void diagonalMoves() {
        //metto un alfire in mezzo alla scacchiera e controllo che tutte le possibili mosse siano giuste
        Bishop bishop =new Bishop(Color.BLACK);
        b.squares[4][4].setPiece(bishop);
        bishop.setPossibleMoves(b);
        ArrayList<Move> listOfMove= bishop.getPossibleMoves();
        assertEquals(listOfMove.get(0).getEndSquare().getPosition().getRow(),5);
        assertEquals(listOfMove.get(0).getEndSquare().getPosition().getCol(),5);
        assertEquals(listOfMove.get(1).getEndSquare().getPosition().getRow(),6);
        assertEquals(listOfMove.get(1).getEndSquare().getPosition().getCol(),6);
        assertNotEquals(listOfMove.get(2).getEndSquare().getPosition().getRow(),7);
        assertNotEquals(listOfMove.get(2).getEndSquare().getPosition().getCol(),7);
        assertEquals(listOfMove.get(2).getEndSquare().getPosition().getRow(),3);
        assertEquals(listOfMove.get(2).getEndSquare().getPosition().getCol(),3);
        assertEquals(listOfMove.get(3).getEndSquare().getPosition().getRow(),2);
        assertEquals(listOfMove.get(3).getEndSquare().getPosition().getCol(),2);
        assertNotEquals(listOfMove.get(4).getEndSquare().getPosition().getRow(),1);
        assertNotEquals(listOfMove.get(4).getEndSquare().getPosition().getCol(),1);
        assertEquals(listOfMove.get(4).getEndSquare().getPosition().getRow(),5);
        assertEquals(listOfMove.get(4).getEndSquare().getPosition().getCol(),3);
        assertEquals(listOfMove.get(5).getEndSquare().getPosition().getRow(),6);
        assertEquals(listOfMove.get(5).getEndSquare().getPosition().getCol(),2);
        assertEquals(listOfMove.get(6).getEndSquare().getPosition().getRow(),3);
        assertEquals(listOfMove.get(6).getEndSquare().getPosition().getCol(),5);
        assertEquals(listOfMove.get(7).getEndSquare().getPosition().getRow(),2);
        assertEquals(listOfMove.get(7).getEndSquare().getPosition().getCol(),6);

    }

    @Test
    void horizontalMoves() {
        //metto una torre in mezzo alla scacchiera e controllo che tutte le possibili
        // mosse orizzontali siano giuste
        Rook rook=new Rook(Color.BLACK);
        b.squares[4][4].setPiece(rook);
        rook.setPossibleMoves(b);
        ArrayList<Move> listOfMove= rook.getPossibleMoves();
        assertEquals(listOfMove.get(0).getEndSquare().getPosition().getRow(),4);
        assertEquals(listOfMove.get(0).getEndSquare().getPosition().getCol(),5);
        assertEquals(listOfMove.get(1).getEndSquare().getPosition().getCol(),6);
        assertEquals(listOfMove.get(2).getEndSquare().getPosition().getCol(),7);
        assertEquals(listOfMove.get(3).getEndSquare().getPosition().getCol(),3);
        assertEquals(listOfMove.get(4).getEndSquare().getPosition().getCol(),2);
        assertEquals(listOfMove.get(5).getEndSquare().getPosition().getCol(),1);
        assertEquals(listOfMove.get(6).getEndSquare().getPosition().getCol(),0);
    }

    @Test
    void verticalMoves() {
        //metto una torre in mezzo alla scacchiera e controllo che tutte le possibili mosse verticali siano giuste
        Rook rook=new Rook(Color.BLACK);
        b.squares[4][4].setPiece(rook);
        rook.setPossibleMoves(b);
        ArrayList<Move> listOfMove= rook.getPossibleMoves();
        assertEquals(listOfMove.get(7).getEndSquare().getPosition().getRow(),5);
        assertEquals(listOfMove.get(8).getEndSquare().getPosition().getRow(),6);
        assertEquals(listOfMove.get(9).getEndSquare().getPosition().getRow(),3);
        assertEquals(listOfMove.get(10).getEndSquare().getPosition().getRow(),2);
    }

    @Test
    void pawnMovement() {
        //verifico che prenda come possibili mosse il mangiare il pedone avversario e l'avanzata
        // di una posizione o di due
        b.squares[2][5].setPiece(new Pawn(Color.WHITE));
        b.squares[2][3].setPiece(new Pawn(Color.BLACK));
        b.squares[1][4].getPiece().setPossibleMoves(b);
        ArrayList<Move> listOfMove= b.squares[1][4].getPiece().getPossibleMoves();
        assertEquals(listOfMove.get(0).getEndSquare().getPosition(), b.squares[2][5].getPosition());
        assertEquals(listOfMove.get(1).getEndSquare().getPosition(), b.squares[2][4].getPosition());
        assertEquals(listOfMove.get(2).getEndSquare().getPosition(), b.squares[3][4].getPosition());
    }

    @Test
    void knightMovement() {
        //aggiungo un cavallo in mezzo alla scacchiera e verifico che prenda tutte le possibili mosse
        Knight knight= new Knight(Color.WHITE);
        b.squares[4][4].setPiece(knight);
        knight.setPossibleMoves(b);
        ArrayList<Move> listOfMove= knight.getPossibleMoves();
        assertEquals(listOfMove.get(0).getEndSquare().getPosition(),b.squares[5][6].getPosition());
        assertEquals(listOfMove.get(1).getEndSquare().getPosition(),b.squares[2][3].getPosition());
        assertEquals(listOfMove.get(2).getEndSquare().getPosition(),b.squares[3][2].getPosition());
        assertEquals(listOfMove.get(3).getEndSquare().getPosition(),b.squares[2][5].getPosition());
        assertEquals(listOfMove.get(4).getEndSquare().getPosition(),b.squares[3][6].getPosition());
        assertEquals(listOfMove.get(5).getEndSquare().getPosition(),b.squares[5][2].getPosition());

        //controllo che consideri come possibile mossa quella di mangiare un avversario
        Pawn pawn = new Pawn(Color.BLACK);
        b.squares[5][6].setPiece(pawn);
        knight.setPossibleMoves(b);
        listOfMove= knight.getPossibleMoves();
        assertEquals(listOfMove.get(0).getEndSquare().getPosition(),b.squares[5][6].getPosition());

        //controllo che non consideri come possibile mossa quella di mangiare un proprio pezzo
        pawn = new Pawn(Color.WHITE);
        b.squares[5][6].setPiece(pawn);
        knight.setPossibleMoves(b);
        listOfMove= knight.getPossibleMoves();
        assertNotEquals(listOfMove.get(0).getEndSquare().getPosition(),b.squares[5][6].getPosition());

    }
}