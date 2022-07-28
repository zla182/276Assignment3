package com.example.assignment3.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.assignment3.Model.Data;
import com.example.assignment3.Model.MineSetter;
import com.example.assignment3.Model.TableCreater;
import com.example.assignment3.R;

//Main control code of the game screen
public class ActivityGaming extends AppCompatActivity
{
    private Data data = Data.getInstances();
    private int NumRow = data.getNumRow();
    private int NumColumn = data.getNumColumn();
    Button[][] btn = new Button[NumRow][NumColumn];
    private int NumMines = data.getNumMines();
    private int MineFound = 0;
    private int NumbScanned = 0;
    private int NumPlayed = data.getNumPlayed();

    private SoundPool sound2;
    private int SoundID2;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameing);
        int BestScore = 0;
        NumPlayed++;
        if(NumMines == 6){
            BestScore = data.getBestScore(NumRow-4,0);
        }
        if(NumMines == 10){
            BestScore = data.getBestScore(NumRow-4,1);
        }

        if(NumMines == 15){
            BestScore = data.getBestScore(NumRow-4,2);
        }
        if(NumMines == 20){
            BestScore = data.getBestScore(NumRow-4,3);
        }
        printBestScore(BestScore);
        CreateSound();


        creatMineTable();
        gotNumPlayed(NumPlayed);
        data.setNumPlayed(NumPlayed);
        FoundedMines(0);
    }

    private void FoundedMines(int num) {
        TextView Find=(TextView)findViewById((R.id.NumFunTot));
        Find.setText(getString(R.string.NumMines,num,NumMines));
    }

    private void gotNumPlayed(int numPlayed) {
        TextView play=(TextView)findViewById(R.id.Counter);
        play.setText(""+numPlayed+"");
    }

    private void gridButton(int row,int col){
        Button button=btn[row][col];
        lockButtonSize();
        int newWidth = button.getWidth();
        int newHeight= button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.source_mine);
        Bitmap scaleBitmap=Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeight,true);
        Resources resource=getResources();
        button.setBackground(new BitmapDrawable(resource,scaleBitmap));
    }

    private void lockButtonSize(){
        for(int i=0;i<NumRow;i++)
        {
            for(int j=0;j<NumColumn;j++)
            {
                Button button=btn[i][j];
                int width=button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height=button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void creatMineTable() {
        TableLayout table=(TableLayout)findViewById(R.id.TableForButton);
        final MineSetter mines = new MineSetter(NumRow,NumColumn,NumMines);
        final TableCreater tableCreat = new TableCreater(NumRow,NumColumn);
        final int[][] tempTable = tableCreat.getTable();
        for(int i=0;i<NumRow;i++)
        {
            TableRow tableRow=new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for(int j=0;j<NumColumn;j++)
            {
                final int tempROW=i;
                final int tempCOL=j;
                final Button button=new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mines.GetOneMine(tempROW,tempCOL)==1)
                        {
                            //it means find the mine;
                            gridButton(tempROW,tempCOL);
                            mines.UpdateMineLeft(tempROW,tempCOL,NumRow,NumColumn);
                            mines.ResetOneMine(tempROW,tempCOL);
                            sound2.play(SoundID2,0.8f,0.8f,0,0,2);
                            MineFound++;
                            FoundedMines(MineFound);
                            for(int i=0;i<NumRow;i++)
                            {
                                if(tempTable[i][tempCOL]==1)
                                {
                                    int ShowN= mines.ScanNum(i,tempCOL);
                                    btn[i][tempCOL].setText(""+ShowN+"");
                                }
                            }
                            for(int i=0;i<NumColumn;i++)
                            {
                                if(tempTable[tempROW][i]==1)
                                {
                                    int showN2=mines.ScanNum(tempROW,i);
                                    btn[tempROW][i].setText(""+showN2+"");
                                }
                            }
                            if(MineFound==NumMines)
                            {
                                setupSetMessage();
                                if (NumRow==4)
                                {
                                    UpdataBest(0,NumMines,NumbScanned);
                                }
                                if(NumRow==5)
                                {
                                    UpdataBest(1,NumMines,NumbScanned);
                                }
                                if(NumRow==6)
                                {
                                    UpdataBest(2,NumMines,NumbScanned);
                                }


                            }
                        }
                        else
                        {
                            //playSound1();
                            if(tableCreat.getNum(tempROW,tempCOL)==0)
                            {
                                int Show=mines.ScanNum(tempROW,tempCOL);
                                button.setText(""+Show+"");
                                NumbScanned++;
                                showScan(NumbScanned);
                                tableCreat.setNum(tempROW,tempCOL);
                            }

                        }

                    }
                });
                tableRow.addView(button);
                btn[i][j]=button;
            }
        }

    }

    private void showScan(int used)
    {
        TextView scan=(TextView)findViewById(R.id.ShowScanNum);
        scan.setText(""+used+"");
    }

    private void setupSetMessage()
    {
        FragmentManager manager= getSupportFragmentManager();
        ActivitySuccessMessage dialog= new ActivitySuccessMessage();
        dialog.show(manager,"MessageDialog");

        Log.i("TAG","Just showed the dialog");
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void CreateSound(){
        sound2=new SoundPool.Builder().build();
        SoundID2=sound2.load(this,R.raw.boom,1);
    }

    public void UpdataBest(int row,int Mines,int Scaned){
        int col;
        if (Mines==6){
            col=0;
        }
        else if(Mines==10){
            col=1;
        }
        else if (Mines==15){
            col=2;
        }
        else
        {
            col= 3;
        }
        if (data.getBestScore(row,col)==0){
            data.setBestScore(row,col,Scaned);
        }
        else if (Scaned<data.getBestScore(row,col)){
            data.setBestScore(row,col,Scaned);
        }
    }


    private void printBestScore(int bestScore) {
        TextView best=(TextView)findViewById(R.id.BestScore);
        best.setText(getString(R.string.Best_score,bestScore));
    }
}