package com.asciirpg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int score = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = (int) extras.get("score");
        }

        String s = ((TextView) findViewById(R.id.score2)).getText().toString();
        s = s.substring(0, 7) + String.valueOf(score);
        ((TextView) findViewById(R.id.score2)).setText(s);

        Button b = (Button) findViewById(R.id.restart_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOver.this, MainActivity.class));
            }
        });
    }
}