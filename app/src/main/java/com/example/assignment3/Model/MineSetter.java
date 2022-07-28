package com.example.assignment3.Model;
import java.util.Random;

//Give basic function of a mine so that can scan remainder mines
public class MineSetter {

        private  int MineSeted=0;
        private int bury[][];
        private int Scan[][];

        public MineSetter(int Row,int Col,int mineNum) {
            bury=new int[Row][Col];
            Scan=new int[Row][Col];
            for(int i=0;i<Row;i++)
            {
                for(int j=0;j<Col;j++)
                {
                    bury[i][j]=0;
                }
            }
            Random random1=new Random();
            Random random2=new Random();
            while(true) {
                int mRow = random1.nextInt(Row);
                int mCol = random2.nextInt(Col);
                if (bury[mRow][mCol] != 1) {
                    bury[mRow][mCol] = 1;
                    MineSeted++;
                }
                if(MineSeted==mineNum)
                {
                    break;
                }
            }
            for(int i=0;i<Row;i++)
            {
                for(int j=0;j<Col;j++)
                {

                    int MineNum1=CountRow(i,Col);
                    int MineNum2=CountCol(j,Row);
                    int Total=MineNum1+MineNum2;
                    Scan[i][j]=Total;
                }
            }
        }

        public int GetOneMine(int row,int col)
        {
            return bury[row][col];
        }

        public void ResetOneMine(int row,int col)
        {
            bury [row][col]=0;
        }

        public int ScanNum(int Row,int Col)
        {

            return Scan[Row][Col];
        }

        public void UpdateMineLeft(int row, int col,int rowNum,int colNum)
        {
            for(int i=0;i<rowNum;i++)
            {
                Scan[i][col]--;
            }
            for(int j=0;j<colNum;j++)
            {
                Scan[row][j]--;
            }
        }


        public int[][] getBury()
        {
            return bury;
        }

        public int[][] getScan()
        {
            return Scan;
        }

        private int CountRow(int row,int ColNum)
        {
            int RMine=0;
            for(int i=0;i<ColNum;i++)
            {
                if(bury[row][i]==1)
                {
                    RMine++;
                }
            }
            return RMine;
        }

        private int CountCol(int col,int RowNum)
        {
            int CMine=0;
            for(int i=0;i<RowNum;i++)
            {
                if(bury[i][col]==1)
                {
                    CMine++;
                }
            }
            return  CMine;
        }

}

