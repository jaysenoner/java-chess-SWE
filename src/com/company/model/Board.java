package com.company.model;

import com.company.model.pieces.*;

import java.util.ArrayList;

public class Board {
    private boolean isFirstTurn;
    public Square[][] squares;


    public Board(boolean emptyBoard) {

        this.isFirstTurn = true;
        this.squares = new Square[8][8];
        int count = 0;

        //Assegno i colori alle case

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinate c = new Coordinate(i, j);
                if ((count) % 2 == 0) {
                    squares[i][j] = new Square(c, Color.WHITE);
                } else {
                    squares[i][j] = new Square(c, Color.BLACK);
                }
                count++;
            }
            count++;
        }

        if(!emptyBoard) {
            //costruisco i vari pezzi nelle case di partenza
            for (int i = 2; i < 5; i++) {
                for (int j = 0; j < 8; j++) {
                    squares[i][j].setPiece(null);
                }
            }

            //Posiziono pedoni
            for (int j = 0; j < 8; j++) {
                squares[1][j].setPiece(new Pawn(Color.BLACK));
                squares[6][j].setPiece(new Pawn(Color.WHITE));
            }

            //Posiziono pezzi
            squares[0][0].setPiece(new Rook(Color.BLACK));
            squares[0][1].setPiece(new Knight(Color.BLACK));
            squares[0][2].setPiece(new Bishop(Color.BLACK));
            squares[0][3].setPiece(new Queen(Color.BLACK));
            squares[0][4].setPiece(new King(Color.BLACK));
            squares[0][5].setPiece(new Bishop(Color.BLACK));
            squares[0][6].setPiece(new Knight(Color.BLACK));
            squares[0][7].setPiece(new Rook(Color.BLACK));
            squares[7][0].setPiece(new Rook(Color.WHITE));
            squares[7][1].setPiece(new Knight(Color.WHITE));
            squares[7][2].setPiece(new Bishop(Color.WHITE));
            squares[7][3].setPiece(new Queen(Color.WHITE));
            squares[7][4].setPiece(new King(Color.WHITE));
            squares[7][5].setPiece(new Bishop(Color.WHITE));
            squares[7][6].setPiece(new Knight(Color.WHITE));
            squares[7][7].setPiece(new Rook(Color.WHITE));
        }

    }

    //TODO:controllare metodo changeTurn
    public void changeTurn(){
        this.isFirstTurn = false;
        //ruota scacchiera
        Square s;
        for( int i=0; i<4; i++){
            for(int j = 0; j<8; j++) {
                s= this.squares[i][j] ;
                this.squares[i][j]= this.squares[7-i][7-j];
                this.squares[7-i][7-j] = s;
            }
        }
    }

    public boolean removePiece(Coordinate c){
        if(getSquare(c).isOccupied()) {
            getSquare(c).getPiece().setCaptured();
            getSquare(c).setPiece(null);
            return true;
        }else {
            return false;
        }
    }
    public boolean addPiece(Coordinate c, Piece p){
        Square s= getSquare(c);
        if(s.isOccupied()){
            return false;
        }else{
            s.setPiece(p);
            s.setOccupied(true);
            return true;
            }
    }
    public Square getSquare(Coordinate pos){
        return squares[pos.getRow()][pos.getCol()];
    }
    public Square getSquare(int row, int col) {
        Coordinate c = new Coordinate(row, col);
        if (c.isValid()) {
            return squares[row][col];
        } else {
            return null;
        }
    }

    public void updateBoard(Move move, Piece p){
        move.getStartSquare().getPiece().setHasMoved();
        move.getStartSquare().setPiece(null);
        move.getEndSquare().setPiece(p);
    }
    public Square[][] getSquares(){
        return squares;
    }
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in diagonale, o per tutta la lunghezza
    della diagonale (alfiere), o solo per una casella in diagonale (re). se checkAllDiagonal è true controlla tutta la diagonale.
     */
    public ArrayList<Move> diagonalMoves(Coordinate startPosition, boolean checkAllDiagonal){
        ArrayList<Move> listOfMove= new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = getSquare(startPosition);
        boolean stop = false;
        int i = row, j = col;
        while (!stop) {
            i++;
            j++;
            Move move = new Move(startSquare, getSquare(i,j));
            stop = move.checkMove(listOfMove, checkAllDiagonal);
        }
        stop = false;
        i = row;
        j = col;
        while (!stop) {
            i--;
            j--;
            Move move = new Move(startSquare, getSquare(i,j));
            stop = move.checkMove(listOfMove, checkAllDiagonal);
        }
        stop = false;
        i = row;
        j = col;
        while (!stop) {
            i++;
            j--;
            Move move = new Move(startSquare, getSquare(i,j));
            stop = move.checkMove(listOfMove, checkAllDiagonal);
        }
        stop = false;
        i = row;
        j = col;
        while (!stop) {
            i--;
            j++;
            Move move = new Move(startSquare, getSquare(i,j));
            stop = move.checkMove(listOfMove, checkAllDiagonal);
        }
        return listOfMove;
    }
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in orizzontale,
    o per tutta la lunghezza della riga (torre), o solo per una casella (re). se checkAllRow è true controlla
    tutta la riga.*/
    public ArrayList<Move> horizontalMoves(Coordinate startPosition, boolean checkAllRow){
        ArrayList<Move> listOfMove= new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = getSquare(startPosition);

        boolean stop = false;
        int j = col;
        while(!stop){
            j++;
            Move move = new Move(startSquare, getSquare(row,j));
            stop = move.checkMove(listOfMove, checkAllRow);
        }
        stop = false;
        j = col;
        while (!stop) {
            j--;
            Move move = new Move(startSquare, getSquare(row,j));
            stop = move.checkMove(listOfMove, checkAllRow);
        }
        return listOfMove;
    }
    /* metodo che restituisce un array con tutte le mosse possibili che il pezzo può fare in verticale,
    o per tutta la lunghezza della riga (torre), o solo per una casella (re). se checkAllCol è true controlla
    tutta la colonna.*/
    public ArrayList<Move> verticalMoves(Coordinate startPosition, boolean checkAllCol){
        ArrayList<Move> listOfMove= new ArrayList<>();
        Square startSquare = getSquare(startPosition);

        boolean stop = false;
        int i = startPosition.getRow();
        int col= startPosition.getRow();
        while (!stop) {
            i++;
            Move move = new Move(startSquare, getSquare(i,col));
            stop = move.checkMove(listOfMove, checkAllCol);
        }
        stop = false;
        i = startPosition.getRow();
        while (!stop) {
            i--;
            Move move = new Move(startSquare, getSquare(i,col));
            stop = move.checkMove(listOfMove, checkAllCol);
        }
        return listOfMove;
    }
    /* metodo che restituisce un array con tutte le mosse possibili che il pedono può fare data la sua posizione iniziale.
     * TODO:aggiungere enpassant*/
    public ArrayList<Move> pawnMovement(Coordinate startPosition){
        ArrayList<Move> listOfMove= new ArrayList<>();
        int col = startPosition.getCol();
        int row = startPosition.getRow();
        Square startSquare = getSquare(startPosition);
        //controllo delle mosse in diagonale se sono occupate da un nemico e posso mangiare (solo in avanti),
        //della singola mossa avanti e se sono al primo spostamento anche del doppio spostamento
        if (startSquare.getPiece().getColor() == Color.WHITE){
            //controllo mosse per mangiare
            Move move=new Move(startSquare,getSquare(row-1,col-1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            move=new Move(startSquare,getSquare(row-1,col+1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            //contollo mosse in avanti
            Move move1=new Move(startSquare,getSquare(row-1,col));
            if(move1.isValid() && move1.isFree()){
                listOfMove.add(move1);
                Move move2=new Move(startSquare,getSquare(row-2,col));
                if(startSquare.getPiece().isFirstMove() && move2.isValid() && move2.isFree()){
                    listOfMove.add(move2);
                }
            }
        }else {
            //controllo mosse per mangiare
            Move move=new Move(startSquare,getSquare(row+1,col-1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            move=new Move(startSquare,getSquare(row+1,col+1));
            if (move.isValid() && move.isOccupiedByEnemyPiece()) {
                listOfMove.add(move);
            }
            //controllo mosse in avanti
            Move move1=new Move(startSquare,getSquare(row+1,col));
            if(move1.isValid() && move1.isFree()){
                listOfMove.add(move1);
                Move move2=new Move(startSquare,getSquare(row+2,col));
                if(startSquare.getPiece().isFirstMove() && move2.isValid() && move2.isFree()){
                    listOfMove.add(move2);
                }
            }
        }

        return listOfMove;
    }
    public ArrayList<Move> knightMovement(Coordinate startPosition){
        ArrayList<Move> listOfMove = new ArrayList<>();
        Square startSquare = getSquare(startPosition);
        int row= startPosition.getRow();
        int col = startPosition.getCol();

        Move move= new Move(startSquare,getSquare(row +1, col +2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        move= new Move(startSquare,getSquare(row +2, col +1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        move= new Move(startSquare,getSquare(row -2, col -1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        move= new Move(startSquare,getSquare(row -1, col -2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        move= new Move(startSquare,getSquare(row -2, col +1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        move= new Move(startSquare,getSquare(row -1, col +2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        move= new Move(startSquare,getSquare(row +2, col -1));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        move= new Move(startSquare,getSquare(row +1, col -2));
        if (move.isValid() && !move.isOccupiedByAllyPiece()){
            listOfMove.add(move);
        }
        return listOfMove;
    }
}
