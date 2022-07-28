package com.example.assignment3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.assignment3.Model.Data;
import com.example.assignment3.R;

//Main control code of the Menu screen
public class MainActivity extends AppCompatActivity {
    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = Data.getInstances();
        ClickToPlay();
        ClickToOption();
        ShowUpdate();
        ClickHelp();
        ClickToFinish();
    }
    private void ClickToPlay() {
        Button Begin = (Button) findViewById(R.id.PlayGame);
        Begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityGaming.class);
                startActivity(intent);

            }
        });
    }

    private void ClickToOption() {
        Button Begin = (Button) findViewById(R.id.Options);
        Begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityOptions.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    private void ClickToFinish()
    {
        Button delete =(Button) findViewById(R.id.Game_over);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    private void ClickHelp()
    {
        Button btn=(Button)findViewById(R.id.Help);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityHelp.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    private void ShowUpdate()
    {
        int Row=ActivityOptions.getRowIntalled(this);
        int Col=ActivityOptions.getColInstalled(this);
        int MineNum=ActivityOptions.getMineInstalled(this);
        data.setNumMines(MineNum);
        data.setNumRow(Row);
        data.setNumColumn(Col);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowUpdate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}