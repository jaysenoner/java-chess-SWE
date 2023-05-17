package tests;

import com.company.model.GameModel;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    GameModel g;
    @BeforeEach
    void setUp() {
        g = new GameModel();
    }

}