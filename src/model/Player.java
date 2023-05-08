package model;

public class Player {
    String name = "";
    Color color;

    //costruttore con nome
    public Player(String name, boolean white) {
        this.name = name;
        if(white) {
            this.color = Color.WHITE;
        }else{
            this.color = Color.BLACK;
        }

    }
    //costruttore senza nome
    public Player(boolean white) {
        if(white) {
            this.color = Color.WHITE;
        }else{
            this.color = Color.BLACK;
        }

    }

    public boolean isWhite(){
        if(this.color == Color.WHITE){
            return true;
        }else{
            return false;
        }
    }
    public String getName(){
        return this.name;
    }
}
