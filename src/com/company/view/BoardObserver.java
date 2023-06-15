package com.company.view;

import com.company.control.GameController;
import com.company.control.MoveController;
import com.company.model.Move;
import com.company.model.Square;
import com.company.model.Subject;

import java.awt.*;

public class BoardObserver extends Observer {
    //attributi
    private Subject model;
    private Table table;

    //costruttori
    public BoardObserver(Subject model, Table table) {
        this.model = model;
        this.table = table;
    }

    @Override
    public void update(Square s) {
        Square start= null, end;
        if( s.getPiece() != null){
            start = s;
            table.reset();
            table.seePossibleMovement(s);
        }
        if(s.getBackground() == Color.DARK_GRAY){
            end = s;
            Move move = new Move(start, end);

        }
        //TODO: implementare metodo
    }
}
