package tests;

import com.company.model.Board;
import com.company.model.Color;
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
                    assertEquals(b.squares[i][j].color, Color.WHITE);
                    assertEquals(b.squares[i][j + 1].color, Color.BLACK);
                }
                else {
                    assertEquals(b.squares[i][j].color, Color.BLACK);
                    assertEquals(b.squares[i][j + 1].color, Color.WHITE);
                }
            }
    }
    @Test
    void allPiecesShouldBePlacedCorrectly(){

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