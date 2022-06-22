package com.asciirpg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        String text;
        SpannableString ss;

        // PLAYER (@): The player's character.
        text = ((TextView) findViewById(R.id.player_info)).getText().toString();
        ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(Color.BLUE), 8, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) findViewById(R.id.player_info)).setText(ss);

        // HEALER (*): Increases the player's HP.
        text = ((TextView) findViewById(R.id.healer_info)).getText().toString();
        ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(Color.YELLOW), 8, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) findViewById(R.id.healer_info)).setText(ss);

        // DETRACTOR (%): Decreases the player's HP.
        text = ((TextView) findViewById(R.id.detractor_info)).getText().toString();
        ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(159, 121, 83)), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) findViewById(R.id.detractor_info)).setText(ss);

        // BLOCKER (#): Decreases accessible areas on map.
        text = ((TextView) findViewById(R.id.blocker_info)).getText().toString();
        ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(Color.RED), 9, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) findViewById(R.id.blocker_info)).setText(ss);

        // REMOVER (+): Increases accessible areas on map.
        text = ((TextView) findViewById(R.id.remover_info)).getText().toString();
        ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(Color.GREEN), 9, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) findViewById(R.id.remover_info)).setText(ss);

        Button b = (Button) findViewById(R.id.back_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Info.this, Start.class));
            }
        });
    }
}