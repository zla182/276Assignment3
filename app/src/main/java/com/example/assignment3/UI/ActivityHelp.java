package com.example.assignment3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment3.R;

//Main control code of the Help screen
public class ActivityHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Button back = (Button) findViewById(R.id.Return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHelp.this, MainActivity.class);
                startActivity(intent);
                ActivityHelp.this.finish();
            }
        });

        authorLink();
    }

    private void authorLink() {
        TextView link_tv = (TextView) findViewById(R.id.Author_link);
        link_tv.setMovementMethod(LinkMovementMethod.getInstance());
        String html = "zla182 & jla641：\n";
        html += "<a href='https://opencoursehub.cs.sfu.ca/jackt/grav-cms/cmpt276-2/home'>Home Link</a>";
        link_tv.setText(Html.fromHtml(html));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ActivityHelp.this, MainActivity.class);
        startActivity(intent);
        ActivityHelp.this.finish();
    }
}