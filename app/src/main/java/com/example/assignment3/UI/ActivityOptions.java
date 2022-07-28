package com.example.assignment3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.assignment3.Model.Data;
import com.example.assignment3.R;

//Main control code of the Options screen
public class ActivityOptions extends AppCompatActivity {
    private Data data = Data.getInstances();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        createRadioButtons();
        choseNumOfMine();
        Remove();
        BacktoPrevious();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ActivityOptions.this, MainActivity.class);
        startActivity(intent);
        ActivityOptions.this.finish();
    }

    @SuppressLint({"StringFormatInvalid", "StringFormatMatches"})
    private void choseNumOfMine() {
        RadioGroup group=(RadioGroup)findViewById(R.id.Number_mines);
        getResources().getIntArray(R.array.NumMines);
        int[] MineN=getResources().getIntArray(R.array.NumMines);
        for(int i=0;i<MineN.length;i++){
            final int Num=MineN[i];
            RadioButton button=new RadioButton(this);
            button.setText(getString(R.string.Mines_num,Num));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityOptions.this,"clicked "+Num,Toast.LENGTH_SHORT).show();
                    saveMineInstalled(Num);
                }
            });
            group.addView(button);
            if(Num==getMineInstalled(this))
            {
                button.setChecked(true);
            }
        }
    }

    private void createRadioButtons() {
        RadioGroup group =(RadioGroup) findViewById(R.id.Board_size);
        getResources().getIntArray(R.array.size);
        int[] size=getResources().getIntArray(R.array.size);
        //create buttons
        for(int i=0 ; i<size.length ; i=i+2){
            final int row = size[i];
            final int column = size[i+1];
            RadioButton button=new RadioButton(this);
            button.setText(getString(R.string.rows_and_clumns,row,column));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityOptions.this,"clicked"+row+"by"+column,Toast.LENGTH_SHORT).show();
                    saveRowInstalled(row);
                    saveColInstalled(column);
                }

            });
            group.addView(button);
            if(row==getRowIntalled(this)&&column==getColInstalled(this))
            {
                button.setChecked(true);
            }
        }
    }

    private void Remove()
    {
        Button delete=(Button) findViewById(R.id.Reset);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setNumPlayed(0);
                data.Reset();
            }
        });
    }
    private void BacktoPrevious()
    {
        Button BackToMain = (Button) findViewById(R.id.BackToMain);
        BackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOptions.this, MainActivity.class);
                startActivity(intent);
                ActivityOptions.this.finish();
            }
        });

    }

    private static final String NumRow="Row Num";
    private static final String PR="APPPerferR";
    private void saveRowInstalled(int row)
    {
        SharedPreferences perf=this.getSharedPreferences(PR,MODE_PRIVATE);
        SharedPreferences.Editor editor=perf.edit();
        editor.putInt(NumRow,row);
        editor.apply();
    }

    static public int getRowIntalled(Context context)
    {
        SharedPreferences perf =context.getSharedPreferences(PR,MODE_PRIVATE);
        //change default value
        int DefaultRow=context.getResources().getInteger(R.integer.Default_Row);
        return perf.getInt(NumRow,DefaultRow);
    }
    private static final String NumCol="Column Num";
    private static final String PC="APPPerferC";
    private void saveColInstalled(int col)
    {
        SharedPreferences perf=this.getSharedPreferences(PC,MODE_PRIVATE);
        SharedPreferences.Editor editor=perf.edit();
        editor.putInt(NumCol,col);
        editor.apply();
    }

    static public int getColInstalled(Context context)
    {
        SharedPreferences perf=context.getSharedPreferences(PC,MODE_PRIVATE);
        int DefaultCol=context.getResources().getInteger(R.integer.Default_Col);
        return perf.getInt(NumCol,DefaultCol);
    }

    private static final String NumMine="Mine Num";
    private static final String PM="APPPerferM";
    private void saveMineInstalled(int MineNum)
    {
        SharedPreferences perf=this.getSharedPreferences(PM,MODE_PRIVATE);
        SharedPreferences.Editor editor=perf.edit();
        editor.putInt(NumMine,MineNum);
        editor.apply();
    }

    static public int getMineInstalled(Context context)
    {
        SharedPreferences perf=context.getSharedPreferences(PM,MODE_PRIVATE);
        int DefaultMine=context.getResources().getInteger(R.integer.Default_Mine);
        return perf.getInt(NumMine,DefaultMine);
    }

}