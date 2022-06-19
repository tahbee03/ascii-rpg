// START DATE: 5/30/22

package com.asciirpg.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.asciirpg.entity.Enemy;
import com.asciirpg.entity.Entity;
import com.asciirpg.entity.Player;
import com.asciirpg.util.Clock;
import com.asciirpg.util.Map;
import com.asciirpg.util.Position;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Data member(s)
    public Map gameMap;
    public Player player;
    public Clock clock;
    public ArrayList<Entity> entities;

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
        gameMap.draw(player);
        gameMap.processColor();
        String s = ((TextView) findViewById(R.id.hp)).getText().toString();
        s = s.substring(0, 4) + String.valueOf(player.getHP());
        ((TextView) findViewById(R.id.hp)).setText(s);

        // Initializes frame clock
        clock = new Clock();
        Log.d("FRAME", String.valueOf(clock.getFrame()));

        // Initializes entity list
        entities = new ArrayList<>();
    }

    public void moveLeft(View v) {
        Log.d("MOVEMENT", "Moving left...");

        if(player.getPos().getCol() == 1) {
            // Player attempting to exceed map bounds
            Log.d("MOVEMENT", "Boundary reached!");
            return;
        } else if(gameMap.getPosState(new Position(player.getPos().getRow(),player.getPos().getCol() - 1))) {
            // Player attempting to enter a position that is occupied by another entity
            Log.d("MOVEMENT", "Obstacle encountered!");
            return;
        } else {
            // Valid movement
            gameMap.draw('-', player.getPos());
            player.setPosition(player.getPos().getRow(),player.getPos().getCol() - 1);
            gameMap.draw(player);
        }

        gameMap.processColor();
        clock.nextFrame();
        Log.d("FRAME", String.valueOf(clock.getFrame()));

        intermission();
    }

    public void moveRight(View v) {
        Log.d("MOVEMENT", "Moving right...");

        /*
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

         */

        if(player.getPos().getCol() == 5) {
            // Player attempting to exceed map bounds
            Log.d("MOVEMENT", "Boundary reached!");
            return;
        } else if(gameMap.getPosState(new Position(player.getPos().getRow(),player.getPos().getCol() + 1))) {
            // Player attempting to enter a position that is occupied by another entity
            Log.d("MOVEMENT", "Obstacle encountered!");
            return;
        } else {
            // Valid movement
            gameMap.draw('-', player.getPos());
            player.setPosition(player.getPos().getRow(),player.getPos().getCol() + 1);
            gameMap.draw(player);
        }

        gameMap.processColor();
        clock.nextFrame();
        Log.d("FRAME", String.valueOf(clock.getFrame()));

        intermission();
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
        gameMap.processColor();

        clock.nextFrame();
        Log.d("FRAME", String.valueOf(clock.getFrame()));

        intermission();
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
        gameMap.processColor();

        clock.nextFrame();
        Log.d("FRAME", String.valueOf(clock.getFrame()));

        intermission();
    }

    private void intermission() {
        /*
        TODO: Use to process data and actions between frames
        - call function after every player movement
        - spawn items and enemies
        - carry out enemy actions
         */

        if(clock.getFrame() % 10 == 0) {
            Random r = new Random();
            int row;
            int col;
            do {
                row = r.nextInt(5) + 1;
                col = r.nextInt(5) + 1;
            } while(row != player.getPos().getRow() && col != player.getPos().getCol());
            Enemy e = new Enemy(30, new Position(row, col));
            entities.add(e);
            gameMap.draw(e);
            gameMap.processColor();
        }
    }

}