package model;
enum Color{BLACK, WHITE};
public class Board {
    int width;
    int height;
    boolean isFirstTurn;
    Square[][] squares;
    //Piece[] whiteDie;
    //Piece[] blackDie;
    //TODO: decidere se altezzza e larghezza sono pixel, cm o semplicemente #caselle quindi 8
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.isFirstTurn = true;
        this.squares= new Square[8][8];
        for(int i=2; i<5; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = null;
            }
        }
        for(int j = 0; j<8; j++){
            squares[1][j].setPiece(new Pawn());
            squares[6][j].setPiece(new Pawn());
        }
        squares[0][0].setPiece(new Rook());
        squares[7][7].setPiece(new Rook());
        squares[0][7].setPiece(new Rook());
        squares[7][0].setPiece(new Rook());
        squares[0][1].setPiece(new Knight());
        squares[0][6].setPiece(new Knight());
        squares[7][1].setPiece(new Knight());
        squares[7][6].setPiece(new Knight());
        squares[0][2].setPiece(new Bishop());
        squares[0][5].setPiece(new Bishop());
        squares[7][2].setPiece(new Bishop());
        squares[7][5].setPiece(new Bishop());
        squares[0][3].setPiece(new King());
        squares[7][4].setPiece(new King());
        squares[0][4].setPiece(new Queen());
        squares[7][3].setPiece(new Queen());

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
        Square s= getSquare(c);
        if(s.isOccupied()) {
            s.piece.ate();
            s = null;
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
    public boolean update(Move move, Piece p){
        if(!move.startSquare.isOccupied() ||move.endSquare.isOccupied() ){
            return false;
        }else{
            move.startSquare.piece.setHasMoved(true);
            move.startSquare.piece= null;
            move.endSquare.piece = p;
            return true;
        }
    }


}
