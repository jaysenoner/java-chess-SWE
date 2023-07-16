package com.company.model;
import com.company.model.pieces.King;
import com.company.model.pieces.Pawn;
import com.company.model.pieces.Piece;
import java.util.ArrayList;

enum GameState{START, INPLAY, CHECK, CHECKMATE, STALEMATE}
public class GameModel{
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Player turn;
    private final Board board;
    private ArrayList<String> movesDone;
    private GameState state;


    public GameModel() {
        this.board = new Board(false);
        this.whitePlayer = new Player(board, true);
        this.blackPlayer = new Player(board, false);
        this.turn = whitePlayer ;
        this.movesDone = new ArrayList<>();
        this.state = GameState.START;
    }

    //getter
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getTurn() {
        return turn;
    }

    public Board getBoard() {
        return board;
    }


    public ArrayList<String> getMovesDone() {
        return movesDone;
    }

    public void changeTurn() {
        if(turn.isWhite()){
            turn = blackPlayer;
        }else turn = whitePlayer;
    }


    //metodo che data una mossa legale aggiorna la lista dei pezzi in caso di cattura e aggiorna la scacchiera e cambia il turno
    public void executeMove(Move move){

        if(move.getEndSquare().isOccupied()){
            move.getEndSquare().getPiece().setCaptured();
            if(turn.isWhite()){
                blackPlayer.getListOfPieces().remove(move.getEndSquare().getPiece());
            }else{
                whitePlayer.getListOfPieces().remove(move.getEndSquare().getPiece());
            }
        }
        movesDone.add(move.getMoveInChessNotation());
        board.updateBoard(move);
        changeTurn();
        if(state == GameState.START){
            state = GameState.INPLAY;
        }
        blackPlayer.calculateAllPossibleMoves();
        whitePlayer.calculateAllPossibleMoves();
        blackPlayer.getEnPassantMove().clear();
        whitePlayer.getEnPassantMove().clear();
        if(lastMoveWasDoubleStep(move)){
            addEnPassantIfPossible(move.getEndSquare());
        }
    }

    private boolean lastMoveWasDoubleStep(Move move) {
        Piece movedPiece= move.getEndSquare().getPiece();
        if(movedPiece.getClass() == Pawn.class)
            return( (move.getStartSquare().getPosition().getRow() == 1 && move.getEndSquare().getPosition().getRow() == 3) ||
                (move.getStartSquare().getPosition().getRow() == 6 && move.getEndSquare().getPosition().getRow() == 4));
        return false;
    }

    //Metodo che esegue la mossa dell'arrocco. Non verifica che la mossa sia legale ne possibile.
    public void executeCastlingMove(boolean shortCastle){
        if(turn.isWhite()){
            if(shortCastle){
                board.updateBoard(whitePlayer.getShortCastleMove().get(0));
                board.updateBoard(whitePlayer.getShortCastleMove().get(1));
                movesDone.add("O-O");
            }
            else{
                board.updateBoard(whitePlayer.getLongCastleMove().get(0));
                board.updateBoard(whitePlayer.getLongCastleMove().get(1));
                movesDone.add("O-O-O");
            }
        }
        else {
            if(shortCastle){
                board.updateBoard(blackPlayer.getShortCastleMove().get(0));
                board.updateBoard(blackPlayer.getShortCastleMove().get(1));
                movesDone.add("O-O");
            }
            else{
                board.updateBoard(blackPlayer.getLongCastleMove().get(0));
                board.updateBoard(blackPlayer.getLongCastleMove().get(1));
                movesDone.add("O-O-O");
            }
        }
        changeTurn();
        blackPlayer.calculateAllPossibleMoves();
        whitePlayer.calculateAllPossibleMoves();
    }

    public void printMovesDone(){
        for(String move: movesDone){
            System.out.println(move);
        }
    }

    public ArrayList<Move> filterLegalMoves(ArrayList<Move> possibleMoves){
        ArrayList<Move> illegalMovement= new ArrayList<>();
        for(Move move: possibleMoves) {
            //try movement
            Piece pieceToReinsert = this.getBoard().simulateMoveOnBoard(move);
            if(pieceToReinsert != null){
                if(pieceToReinsert.getColor()==Color.WHITE){
                    whitePlayer.getListOfPieces().remove(pieceToReinsert);
                }
                else{
                    blackPlayer.getListOfPieces().remove(pieceToReinsert);
                }
            }
            if(kingIsChecked()){
                illegalMovement.add(move);
            }
            if(pieceToReinsert != null){
                if(pieceToReinsert.getColor()==Color.WHITE){
                    whitePlayer.getListOfPieces().add(pieceToReinsert);
                }
                else{
                    blackPlayer.getListOfPieces().add(pieceToReinsert);
                }
            }
            this.getBoard().reverseSimulatedMove(move, pieceToReinsert);
        }
        for(Move m : illegalMovement) {
            possibleMoves.remove(m);
        }
        return possibleMoves;
    }

    //Calcolo tutte le mosse possibili del player avversario per determinare se esiste una mossa che attacca direttamente
    // il re del player corrente
    public boolean kingIsChecked(){
        if (this.turn.isWhite()) {
            this.blackPlayer.calculateAllPossibleMoves();
            for (Move move : this.blackPlayer.getListOfPossibleMoves()) {
                if (move.getEndSquare().getPiece() != null && move.getEndSquare().getPiece().getClass() == King.class) {
                    state = GameState.CHECK;
                    return true;
                }
            }
        }else{
            this.whitePlayer.calculateAllPossibleMoves();
            for (Move move : this.getWhitePlayer().getListOfPossibleMoves()) {
                if(move.getEndSquare().getPiece() != null)
                    if (move.getEndSquare().getPiece().getClass() == King.class) {
                    state = GameState.CHECK;
                    return true;
                }
            }
        }
        state= GameState.INPLAY;
        return false;
    }

    public boolean kingIsCheckMated(){
        if(kingIsChecked() && filterLegalMoves(this.getTurn().getListOfPossibleMoves()).isEmpty()){
            state = GameState.CHECKMATE;
            return true;
        }else{
            state = GameState.INPLAY;
            return false;
        }
    }

    public boolean isStaleMate(){
        if(!kingIsChecked() && filterLegalMoves(this.getTurn().getListOfPossibleMoves()).isEmpty()){
            state= GameState.STALEMATE;
            return true;
        }else{
            state = GameState.INPLAY;
            return false;
        }
    }

    public boolean isShortCastlingLegal(){
        Player currentPlayer = this.turn;
        Square[][] squares = board.squares;
        boolean castle = !currentPlayer.getKing().HasMoved() && !currentPlayer.getShortCastleRook().HasMoved();
        if(currentPlayer.isWhite())
        {
            for(Move move: blackPlayer.getListOfPossibleMoves()){
                if(move.getEndSquare()==squares[7][6] || move.getEndSquare()==squares[7][5]){
                    return false;
                }
            }
            return castle && (!squares[7][6].isOccupied() && !squares[7][5].isOccupied());
        }
        else {
            for(Move move: whitePlayer.getListOfPossibleMoves()){
                if(move.getEndSquare()==squares[0][6] || move.getEndSquare()==squares[0][5]){
                    return false;
                }
            }
            return castle && (!squares[0][6].isOccupied() && !squares[0][5].isOccupied());
        }
    }

    public boolean isLongCastlingLegal(){
        Player currentPlayer = this.turn;
        Square[][] squares = board.squares;
        boolean castle = !currentPlayer.getKing().HasMoved() && !currentPlayer.getLongCastleRook().HasMoved();
        if(currentPlayer.isWhite())
        {
            for(Move move: blackPlayer.getListOfPossibleMoves()){
                if(move.getEndSquare()==squares[7][1] || move.getEndSquare()==squares[7][2]||move.getEndSquare()==squares[7][3]){
                    return false;
                }
            }
            return castle && (!squares[7][1].isOccupied() && !squares[7][2].isOccupied() && !squares[7][3].isOccupied());
        }
        else {
            for(Move move: whitePlayer.getListOfPossibleMoves()){
                if(move.getEndSquare()==squares[0][1] || move.getEndSquare()==squares[0][2]||move.getEndSquare()==squares[0][3]){
                    return false;
                }
            }
            return castle && (!squares[0][1].isOccupied() && !squares[0][2].isOccupied() && !squares[0][3].isOccupied());
        }
    }

    public ArrayList<String> getMovesInPgn(){
        ArrayList<String> pgn = new ArrayList<>();
        int moveNumber = 1;
        if(movesDone.size()%2 ==0)
        for(int i = 0; i < movesDone.size(); i+=2){
            pgn.add(moveNumber + ". " + movesDone.get(i) + " " +movesDone.get(i+1));
            moveNumber++;
        }
        else{
            for(int i = 0; i < (movesDone.size()-1); i+=2){
                pgn.add(moveNumber + ". " + movesDone.get(i) + " " +movesDone.get(i+1));
                moveNumber++;
            }
            pgn.add( (moveNumber) + ". "+ movesDone.get(movesDone.size()-1)+ " ");
        }
        return pgn;
    }

    public void addEnPassantIfPossible(Square squereOfPieceToEat) {
        int col = squereOfPieceToEat.getPosition().getCol();
        Square near;
        if(squereOfPieceToEat.getPiece().getColor() == Color.WHITE) {
            if(col-1 >=0){
                near = board.getSquares()[4][col-1];
                if(near.isOccupied() && near.getPiece().getClass() == Pawn.class && near.getPiece().getColor() == Color.BLACK){
                    Move move = new Move(near, board.getSquares()[5][col]);
                    turn.getEnPassantMove().add(move);
                }
            }
            if(col +1 <8){
                near = board.getSquares()[4][col+1];
                if(near.isOccupied() && near.getPiece().getClass() == Pawn.class && near.getPiece().getColor() == Color.BLACK){
                    Move move = new Move(near, board.getSquares()[5][col]);
                    turn.getEnPassantMove().add(move);
                }
            }
        }else{
            if(col-1 >=0){
                near = board.getSquares()[3][col-1];
                if(near.isOccupied() && near.getPiece().getClass() == Pawn.class && near.getPiece().getColor() == Color.WHITE){
                    Move move = new Move(near, board.getSquares()[2][col]);
                    turn.getEnPassantMove().add(move);
                }
            }
            if(col +1 <8){
                near = board.getSquares()[3][col+1];
                if(near.isOccupied() && near.getPiece().getClass() == Pawn.class && near.getPiece().getColor() == Color.WHITE){
                    Move move = new Move(near, board.getSquares()[2][col]);
                    turn.getEnPassantMove().add(move);
                }
            }
        }
    }

    public void executeEnPassant(Square endSquare) {
        Square squareWithPieceToRemove;
        if(endSquare.getPiece().getColor() == Color.WHITE ){
            squareWithPieceToRemove = board.getSquares()[3][endSquare.getPosition().getCol()];
            blackPlayer.getListOfPieces().remove(squareWithPieceToRemove.getPiece());
            board.removePiece(squareWithPieceToRemove);
        }else{
            squareWithPieceToRemove = board.getSquares()[4][endSquare.getPosition().getCol()];
            whitePlayer.getListOfPieces().remove(squareWithPieceToRemove.getPiece());
            board.removePiece(squareWithPieceToRemove);
        }
        //TODO: CONTROLLARE NOTAZIONE PGN ENPASSANT
    }
    public ArrayList<Move> checkLegacyEnPassant(ArrayList<Move> enPassantMoves) {
        ArrayList<Move> illegalMovement = new ArrayList<>();
        for (Move move : enPassantMoves) {
            Piece pieceToReinsert;
            if (move.getStartSquare().getPiece().getColor() == Color.WHITE)
                pieceToReinsert = board.squares[3][move.getEndSquare().getPosition().getCol()].getPiece();
            else
                pieceToReinsert = board.squares[4][move.getEndSquare().getPosition().getCol()].getPiece();
            executeEnPassant(move.getEndSquare());
            if (kingIsChecked()) {
                illegalMovement.add(move);
            }
            if (pieceToReinsert.getColor() == Color.WHITE) {
                whitePlayer.getListOfPieces().add(pieceToReinsert);
                board.squares[4][move.getEndSquare().getPosition().getCol()].setPiece(pieceToReinsert);
            } else {
                blackPlayer.getListOfPieces().add(pieceToReinsert);
                board.squares[3][move.getEndSquare().getPosition().getCol()].setPiece(pieceToReinsert);
            }
            move.getStartSquare().setPiece(move.getEndSquare().getPiece());
            move.getEndSquare().setPiece(null);
        }

        enPassantMoves.removeAll(illegalMovement);
        return enPassantMoves;
    }
}




