package tests;

import com.company.model.Board;
import com.company.model.Color;
import com.company.model.pieces.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    public Board b;

    @BeforeEach
    void setUp() {
        b = new Board();

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

    }

    @Test
    void diagonalMoves() {
    }

    @Test
    void horizontalMoves() {
    }

    @Test
    void verticalMoves() {
    }

    @Test
    void pawnMovement() {
    }

    @Test
    void knightMovement() {
    }
}