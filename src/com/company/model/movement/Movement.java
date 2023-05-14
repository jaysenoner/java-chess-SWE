package com.company.model.movement;

import com.company.model.Board;
import com.company.model.Coordinate;
import com.company.model.Move;

import java.util.ArrayList;

public abstract class Movement {

    public abstract ArrayList<Move> getMovesFromMovementRule(Coordinate startPosition, Board chessBoard);
}


