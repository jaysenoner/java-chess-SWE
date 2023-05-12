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

    @Test
    void testFromIndexToChessNotation() {
        for (int i = 0; i< 8 ; i++){
            assertEquals(pos.fromIndexToChessNotation(),"a" + (8 - i));
            pos.setRow(i);
        }
        for (int i = 0; i< 8 ; i++){
            assertEquals(pos.fromIndexToChessNotation(),"b" + (8 - i));
            pos.setCol(i);
        }




    }
}