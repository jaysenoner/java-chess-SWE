package com.company.model;
import java.util.Arrays;

public class Coordinate {
    private int row;
    private int col;
    private static final String[] letters = {"a", "b", "c", "d", "e", "f", "g" ,"h"};
    private static final String[] numbers = {"8", "7", "6", "5", "4", "3", "2", "1"};

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static String[] getLetters() {
        return letters;
    }

    public static String[] getNumbers() {
        return numbers;
    }

    public Coordinate(String letter, String number){
        if(Arrays.asList(letters).contains(letter) && Arrays.asList(numbers).contains(number)){
            this.row = Arrays.asList(numbers).indexOf(number);
            this.col = Arrays.asList(letters).indexOf(letter);
        }
        else throw new IllegalArgumentException();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isValid(){
        return (this.col >=0 && this.col<8 && this.row>=0 && this.row<8);

    }
    public String fromIndexToChessNotation(){

        return letters[col] + numbers[row];
    }

}
