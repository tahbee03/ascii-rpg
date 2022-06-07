// START DATE: 5/30/22

package com.asciirpg.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.asciirpg.entity.Player;
import com.asciirpg.util.Map;

public class MainActivity extends AppCompatActivity {

    // Data member(s)
    public Map gameMap;
    public Player player;

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
    }

}