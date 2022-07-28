package com.example.assignment3.Model;

//Give basic information of the game table
public class Data {
    private int NumRow = 0;
    private int NumColumn = 0;
    private int NumMines = 0;
    private int NumPlayed = 0;
    private int [][]BestScore = new int[3][4];

    private static Data instances;
    private Data(){}

    public static Data getInstances() {
        if (instances == null) {
            instances = new Data();
            instances.Reset();
        }
        return instances;


    }

    public int getNumPlayed() {
        return NumPlayed;
    }

    public void setNumPlayed(int numPlayed) {
        NumPlayed = numPlayed;
    }

    public int getNumRow() {
        return NumRow;
    }

    public void setNumRow(int numRow) {
        NumRow = numRow;
    }

    public int getNumColumn() {
        return NumColumn;
    }

    public void setNumColumn(int numColumn) {
        NumColumn = numColumn;
    }

    public int getNumMines() {
        return NumMines;
    }

    public void setNumMines(int numMines) {
        NumMines = numMines;
    }

    public int getBestScore(int row , int column) {
        int best = BestScore[row][column];
        return best;
    }

    public void setBestScore(int row,int column,int score) {
        BestScore [row][column] = score;
    }

    public void Reset(){

        for(int i = 0 ; i < 3 ; i++){

            for(int j = 0 ; j < 4 ; j++){

                BestScore[i][j] = 0;
            }

        }

    }
}
