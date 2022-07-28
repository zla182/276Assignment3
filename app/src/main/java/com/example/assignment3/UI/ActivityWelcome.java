package com.example.assignment3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


import com.example.assignment3.R;

//Main control code of the welcome screen
public class ActivityWelcome extends AppCompatActivity {
    private final int Skipping = 4000;//will skip in 4s
    private boolean Skiped=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        H2Animation();
        H3Animation();
        ClickToSkip();
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(Skiped==false) {
                    Intent mainIntent = new Intent(ActivityWelcome.this, MainActivity.class);
                    ActivityWelcome.this.startActivity(mainIntent);
                    ActivityWelcome.this.finish();
                }
            }
        }, Skipping);
    }

    private void H2Animation(){
        AnimationSet animationSet=new AnimationSet(true);
        ImageView minePic=(ImageView) findViewById(R.id.image_mine2);
        Animation alphaAnimation = new AlphaAnimation(1, (float) 0.1);
        alphaAnimation.setDuration(4000);
        alphaAnimation.setFillAfter(true);
        Animation translateAnimation=new TranslateAnimation(100, 0, 0, 0);
        translateAnimation.setDuration(4000);
        translateAnimation.setInterpolator(this, android.R.anim.cycle_interpolator);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        minePic.startAnimation(animationSet);
    }

    private void H3Animation(){
        AnimationSet animationSet=new AnimationSet(true);
        ImageView minePic=(ImageView) findViewById(R.id.image_mine3);
        Animation alphaAnimation = new AlphaAnimation(1, (float) 0.1);
        alphaAnimation.setDuration(4000);
        alphaAnimation.setFillAfter(true);
        Animation translateAnimation=new TranslateAnimation(0, 100, 0, 0);
        translateAnimation.setDuration(4000);
        translateAnimation.setInterpolator(this, android.R.anim.cycle_interpolator);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        minePic.startAnimation(animationSet);
    }

    private void ClickToSkip() {
        Button Skip = (Button) findViewById(R.id.Skip);
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skiped=true;
                Intent intent = new Intent(ActivityWelcome.this, MainActivity.class);
                startActivity(intent);
                ActivityWelcome.this.finish();
            }
        });
    }
}