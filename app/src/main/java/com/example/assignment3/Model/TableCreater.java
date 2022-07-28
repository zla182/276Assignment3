package com.example.assignment3.Model;

//Create the controllable game table
public class TableCreater {
    private int[][] Table;
    int Counter=0;
    public TableCreater(int row,int col)
    {
        Table=new int[row][col];
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                Table[i][j]=0;
            }
        }
    }

    public void setNum(int row,int col) {
        Table[row][col]=1;
    }

    public int getNum(int row,int col)
    {
        return Table[row][col];
    }

    public int[][] getTable()
    {
        return Table;
    }
}
