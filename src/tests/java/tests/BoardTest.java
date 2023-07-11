package tests;

import com.company.model.*;
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
    void deepCopyPieceTest(){
        Piece originalPiece = b.squares[1][0].getPiece();
        Piece copiedPiece = b.squares[1][0].getPiece().copy();
        assertEquals(originalPiece.getClass(), copiedPiece.getClass());
        assertEquals(originalPiece.getColor(), copiedPiece.getColor());
        assertEquals(originalPiece.getPosition().getRow(), copiedPiece.getPosition().getRow());
        assertEquals(originalPiece.getPosition().getCol(), copiedPiece.getPosition().getCol());
        assertEquals(originalPiece.getPossibleMoves(), copiedPiece.getPossibleMoves());
        assertEquals(originalPiece.getListOfMovementRules(), copiedPiece.getListOfMovementRules());

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

    //Testa tutte le caselle della scacchiera
    @Test
    void getSquareByChessNotationShouldReturnTheCorrectSquare(){
        for(int i= 0; i< 8 ; i++)
            for(int j = 0; j< 8;j++)
                assertEquals(b.squares[i][j],b.getSquare(Coordinate.getLetters()[j],Integer.toString(8-i)));

    }

    void updateBoardShouldModifyPiecesPositions(){
        b.updateBoard(new Move(b.getSquare("a","2"), b.getSquare("a","4")));
        b.updateBoard(new Move(b.getSquare("b","2"), b.getSquare("b","4")));
        b.updateBoard(new Move(b.getSquare("c","2"), b.getSquare("c","4")));
        b.updateBoard(new Move(b.getSquare("d","2"), b.getSquare("d","4")));
        b.updateBoard(new Move(b.getSquare("e","2"), b.getSquare("e","4")));
        b.updateBoard(new Move(b.getSquare("f","2"), b.getSquare("f","4")));
        b.updateBoard(new Move(b.getSquare("g","2"), b.getSquare("g","4")));
        b.updateBoard(new Move(b.getSquare("h","2"), b.getSquare("h","4")));
        assertNull(b.getSquare("a","2").getPiece());
        assertNull(b.getSquare("b","2").getPiece());
        assertNull(b.getSquare("c","2").getPiece());
        assertNull(b.getSquare("d","2").getPiece());
        assertNull(b.getSquare("e","2").getPiece());
        assertNull(b.getSquare("f","2").getPiece());
        assertNull(b.getSquare("g","2").getPiece());
        assertNull(b.getSquare("h","2").getPiece());

        assertEquals(b.getSquare("a","2").getPiece().getClass(),Pawn.class);
        assertEquals(b.getSquare("c","2").getPiece().getClass(),Pawn.class);
        assertEquals(b.getSquare("d","2").getPiece().getClass(),Pawn.class);
        assertEquals(b.getSquare("e","2").getPiece().getClass(),Pawn.class);
        assertEquals(b.getSquare("b","2").getPiece().getClass(),Pawn.class);
        assertEquals(b.getSquare("f","2").getPiece().getClass(),Pawn.class);
        assertEquals(b.getSquare("g","2").getPiece().getClass(),Pawn.class);
        assertEquals(b.getSquare("h","2").getPiece().getClass(),Pawn.class);


    }



}