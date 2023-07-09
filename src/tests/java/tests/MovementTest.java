package tests;

import com.company.model.Board;
import com.company.model.Color;
import com.company.model.Move;
import com.company.model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {

    public Board b;
    @BeforeEach
    void setUp() {
        b = new Board(false);
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
        //metto una torre in mezzo alla scacchiera e controllo che tutte le possibili mosse orizzontali siano giuste
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
    @Test
    void queenMovement(){
        //aggiungo una regina in mezzo alla scacchiera e verifico che effettui tutte le mosse giuste
        Queen queen= new Queen(Color.BLACK);
        b.squares[4][4].setPiece(queen);
        queen.setPossibleMoves(b);
        ArrayList<Move> listOfMove= queen.getPossibleMoves();
        assertEquals(listOfMove.get(6).getEndSquare().getPosition(),b.squares[3][5].getPosition());
        assertEquals(listOfMove.get(14).getEndSquare().getPosition(),b.squares[4][0].getPosition());
        assertEquals(listOfMove.get(16).getEndSquare().getPosition(),b.squares[6][4].getPosition());
        for(Move move : listOfMove){
            System.out.println(move.getEndSquare().getPosition().getRow() + ""+ move.getEndSquare().getPosition().getCol());
        }

    }

}