package tests;

import com.company.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    Coordinate pos;

    @BeforeEach
    void setUp() {
        pos = new Coordinate(0,0);
    }

    //Test che verifica per tutte le caselle della scacchiera se la funzione di traduzione in notazione scacchistica a partire
    // da una coppia di coordinate intere restituisce la corretta coordinata scacchistica
    @Test
    void testFromIndexToChessNotation() {
        for(int j = 0; j < 8; j++) {
            pos.setRow(j);
            for (int i = 0; i < 8; i++) {
                pos.setCol(i);
                assertEquals(
                        Coordinate.getLetters()[i] + (8 - j), pos.fromIndexToChessNotation() );
            }
        }
    }

    @Test
    void testConstructorFromChessNotation() {

            Coordinate position = new Coordinate("a","8");
            Coordinate position2 = new Coordinate("c","4");
            assertEquals(position.getRow(),0);
            assertEquals(position.getCol(), 0);
            assertEquals(position2.getRow(),4);
            assertEquals(position2.getCol(), 2);

            try{
                new Coordinate("k","23");
            }catch(IllegalArgumentException e)
            {
                assertEquals(e.getClass(), IllegalArgumentException.class);
            }



    }


}