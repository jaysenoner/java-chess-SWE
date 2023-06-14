package com.company.control;

import com.company.model.Board;
import com.company.model.GameModel;

public abstract class Controller {
    protected GameModel gameModel;
    public Controller(GameModel gameModel){
        this.gameModel= gameModel;
    }
}



