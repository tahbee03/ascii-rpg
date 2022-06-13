// START DATE: 5/30/22

package com.asciirpg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.asciirpg.entity.Player;
import com.asciirpg.util.Clock;
import com.asciirpg.util.Map;

public class MainActivity extends AppCompatActivity {

    // Data member(s)
    public Map gameMap;
    public Player player;
    public Clock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes map
        gameMap = new Map();
        gameMap.pushRow(findViewById(R.id.row1));
        gameMap.pushRow(findViewById(R.id.row2));
        gameMap.pushRow(findViewById(R.id.row3));
        gameMap.pushRow(findViewById(R.id.row4));
        gameMap.pushRow(findViewById(R.id.row5));

        // Initializes player position
        player = new Player();
        gameMap.draw(player, player.getPos());
        processColor();

        // Initializes frame clock
        clock = new Clock();
        String s  = ((TextView) findViewById(R.id.currFrame)).getText().toString();
        s = s.substring(0, 7) + String.valueOf(clock.getFrame());
        ((TextView) findViewById(R.id.currFrame)).setText(s);
    }

    public void moveLeft(View v) {
        Log.d("MOVEMENT", "Moving left...");

        String currRow = gameMap.getRow(player.getPos().getRow()).getText().toString();
        int currColNum = player.getPos().getCol();
        if(currColNum != 1) { // Checks if player is in the first column
            currRow = currRow.substring(0, currColNum - 2)
                    + player.getIcon()
                    + "-"
                    + currRow.substring(currColNum);
            player.setPosition(player.getPos().getRow(), player.getPos().getCol() - 1);
        } else {
            Log.d("MOVEMENT", "Boundary reached!");
        }
        gameMap.getRow(player.getPos().getRow()).setText(currRow);
        processColor();
        processFrame();
        // player.getPos().getNeighbors();
    }

    public void moveRight(View v) {
        Log.d("MOVEMENT", "Moving right...");

        String currRow = gameMap.getRow(player.getPos().getRow()).getText().toString();
        int currColNum = player.getPos().getCol();
        if(currColNum != 5) { // Checks if player is in the last column
            currRow = currRow.substring(0, currColNum - 1)
                    + "-"
                    + player.getIcon()
                    + currRow.substring(currColNum + 1);
            player.setPosition(player.getPos().getRow(), player.getPos().getCol() + 1);
        } else {
            Log.d("MOVEMENT", "Boundary reached!");
        }
        gameMap.getRow(player.getPos().getRow()).setText(currRow);
        processColor();
        processFrame();
        // player.getPos().getNeighbors();
    }

    public void moveUp(View v) {
        Log.d("MOVEMENT", "Moving up...");

        int currRowNum = player.getPos().getRow();
        String currRow = gameMap.getRow(currRowNum).getText().toString();
        String nextRow;
        if(!currRow.equals(gameMap.getRow(1).getText().toString())) { // Checks if player is in the first row
            nextRow = gameMap.getRow(currRowNum - 1).getText().toString();
            int currColNum = player.getPos().getCol();
            nextRow = nextRow.substring(0, currColNum - 1)
                    + player.getIcon()
                    + nextRow.substring(currColNum);
            currRow = currRow.substring(0, currColNum - 1)
                    + "-"
                    + currRow.substring(currColNum);
            gameMap.getRow(currRowNum - 1).setText(nextRow);
            player.setPosition(currRowNum - 1, player.getPos().getCol());
        } else {
            Log.d("MOVEMENT", "Boundary reached!");
        }
        gameMap.getRow(currRowNum).setText(currRow);
        processColor();
        processFrame();
        // player.getPos().getNeighbors();
    }

    public void moveDown(View v) {
        Log.d("MOVEMENT", "Moving down...");

        int currRowNum = player.getPos().getRow();
        String currRow = gameMap.getRow(currRowNum).getText().toString();
        String nextRow;
        if(!currRow.equals(gameMap.getRow(5).getText().toString())) { // Checks if the player is in the last row
            nextRow = gameMap.getRow(currRowNum + 1).getText().toString();
            int currColNum = player.getPos().getCol();
            nextRow = nextRow.substring(0, currColNum - 1)
                    + "@"
                    + nextRow.substring(currColNum);
            currRow = currRow.substring(0, currColNum - 1)
                    + "-"
                    + currRow.substring(currColNum);
            gameMap.getRow(currRowNum + 1).setText(nextRow);
            player.setPosition(currRowNum + 1, player.getPos().getCol());
        } else {
            Log.d("MOVEMENT", "Boundary reached!");
        }
        gameMap.getRow(currRowNum).setText(currRow);
        processColor();
        processFrame();
        // player.getPos().getNeighbors();
    }

    private void processColor() {
        String text = gameMap.getRow(player.getPos().getRow()).getText().toString();

        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcs, player.getPos().getCol() - 1, player.getPos().getCol(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        gameMap.getRow(player.getPos().getRow()).setText(ss);
    }

    private void processFrame() {
        clock.nextFrame();
        String s  = ((TextView) findViewById(R.id.currFrame)).getText().toString();
        s = s.substring(0, 7) + String.valueOf(clock.getFrame());
        ((TextView) findViewById(R.id.currFrame)).setText(s);
    }

}