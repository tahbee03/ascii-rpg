// START DATE: 5/30/22

package com.asciirpg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.asciirpg.entity.Blocker;
import com.asciirpg.entity.Entity;
import com.asciirpg.entity.Player;
import com.asciirpg.util.Clock;
import com.asciirpg.util.Map;
import com.asciirpg.util.Position;
import java.util.ArrayList;
import java.util.Random;

/*
TODO (Suggestions)
- add sounds
- change button colors
 */

public class MainActivity extends AppCompatActivity {

    // Data member(s)
    public Map gameMap;
    public Player player;
    public Clock clock;
    public ArrayList<Entity> entities; // TODO: Remove

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

        // Initializes player
        player = new Player();
        gameMap.draw(player);
        gameMap.processColor();
        String s = ((TextView) findViewById(R.id.hp)).getText().toString();
        s = s.substring(0, 4) + String.valueOf(player.getHP());
        ((TextView) findViewById(R.id.hp)).setText(s);
        s = ((TextView) findViewById(R.id.score)).getText().toString();
        s = s.substring(0, 7) + String.valueOf(player.getScore());
        ((TextView) findViewById(R.id.score)).setText(s);

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
        } else if(gameMap.occupiedPos(new Position(player.getPos().getRow(),player.getPos().getCol() - 1))) {
            // Player attempting to enter a position that is occupied by another entity
            Log.d("MOVEMENT", "Obstacle encountered!");
        } else {
            // Valid movement
            gameMap.draw('-', player.getPos());
            player.storePos();
            player.setPosition(player.getPos().getRow(),player.getPos().getCol() - 1);
            Log.d("PREV POS", player.getPrevPos().toString());
            Log.d("POS", player.getPos().toString());
            if(!player.getPos().equals(player.getPrevPos())) player.updateScore();
            String s = ((TextView) findViewById(R.id.score)).getText().toString();
            s = s.substring(0, 7) + String.valueOf(player.getScore());
            ((TextView) findViewById(R.id.score)).setText(s);
            gameMap.draw(player);
        }

        intermission();
    }

    public void moveRight(View v) {
        Log.d("MOVEMENT", "Moving right...");

        if(player.getPos().getCol() == 5) {
            // Player attempting to exceed map bounds
            Log.d("MOVEMENT", "Boundary reached!");
        } else if(gameMap.occupiedPos(new Position(player.getPos().getRow(),player.getPos().getCol() + 1))) {
            // Player attempting to enter a position that is occupied by another entity
            Log.d("MOVEMENT", "Obstacle encountered!");
        } else {
            // Valid movement
            gameMap.draw('-', player.getPos());
            player.storePos();
            player.setPosition(player.getPos().getRow(),player.getPos().getCol() + 1);
            Log.d("PREV POS", player.getPrevPos().toString());
            Log.d("POS", player.getPos().toString());
            if(!player.getPos().equals(player.getPrevPos())) player.updateScore();
            String s = ((TextView) findViewById(R.id.score)).getText().toString();
            s = s.substring(0, 7) + String.valueOf(player.getScore());
            ((TextView) findViewById(R.id.score)).setText(s);
            gameMap.draw(player);
        }

        intermission();
    }

    public void moveUp(View v) {
        Log.d("MOVEMENT", "Moving up...");

        if(player.getPos().getRow() == 1) {
            // Player attempting to exceed map bounds
            Log.d("MOVEMENT", "Boundary reached!");
        } else if(gameMap.occupiedPos(new Position(player.getPos().getRow() - 1, player.getPos().getCol()))) {
            // Player attempting to enter a position that is occupied by another entity
            Log.d("MOVEMENT", "Obstacle encountered!");
        } else {
            // Valid movement
            gameMap.draw('-', player.getPos());
            player.storePos();
            player.setPosition(player.getPos().getRow() - 1, player.getPos().getCol());
            Log.d("PREV POS", player.getPrevPos().toString());
            Log.d("POS", player.getPos().toString());
            if(!player.getPos().equals(player.getPrevPos())) player.updateScore();
            String s = ((TextView) findViewById(R.id.score)).getText().toString();
            s = s.substring(0, 7) + String.valueOf(player.getScore());
            ((TextView) findViewById(R.id.score)).setText(s);
            gameMap.draw(player);
        }

        intermission();
    }

    public void moveDown(View v) {
        Log.d("MOVEMENT", "Moving down...");

        if(player.getPos().getRow() == 5) {
            // Player attempting to exceed map bounds
            Log.d("MOVEMENT", "Boundary reached!");
        } else if(gameMap.occupiedPos(new Position(player.getPos().getRow() + 1, player.getPos().getCol()))) {
            // Player attempting to enter a position that is occupied by another entity
            Log.d("MOVEMENT", "Obstacle encountered!");
        } else {
            // Valid movement
            gameMap.draw('-', player.getPos());
            player.storePos();
            player.setPosition(player.getPos().getRow() + 1, player.getPos().getCol());
            if(!player.getPos().equals(player.getPrevPos())) player.updateScore();
            Log.d("PREV POS", player.getPrevPos().toString());
            Log.d("POS", player.getPos().toString());
            String s = ((TextView) findViewById(R.id.score)).getText().toString();
            s = s.substring(0, 7) + String.valueOf(player.getScore());
            ((TextView) findViewById(R.id.score)).setText(s);
            gameMap.draw(player);
        }

        intermission();
    }

    private void intermission() {
        /*
        TODO: Use to process data and actions between frames
        - call function after every player movement
        - spawn items and enemies
        - carry out enemy actions
         */

        clock.nextFrame();

        player.setHP(player.getHP() - 1);
        String s = ((TextView) findViewById(R.id.hp)).getText().toString();
        s = s.substring(0, 4) + String.valueOf(player.getHP());
        ((TextView) findViewById(R.id.hp)).setText(s);

        if(player.getHP() <= 0) {
            Intent i = new Intent(MainActivity.this, GameOver.class);
            i.putExtra("score", player.getScore());
            startActivity(i);
        }

        if(clock.getFrame() % 10 == 0) {
            Random numGen = new Random();
            // int entityNum = numGen.nextInt(4) + 1;
            int entityNum = 1;
            Position p;
            do {
                p = new Position(numGen.nextInt(5) + 1, numGen.nextInt(5) + 1);
            } while(gameMap.occupiedPos(p));

            switch(entityNum) {
                case 1:
                    Log.d("SPAWN", "Spawning new blocker!");
                    Blocker b = new Blocker(p);
                    gameMap.draw(b);
                    break;
                case 2:
                    // TODO: Add Remover spawn
                case 3:
                    // TODO: Add Detractor spawn
                case 4:
                    // TODO: Add Healer spawn
                    break;
            }
        }

        gameMap.processColor();
        Log.d("FRAME", String.valueOf(clock.getFrame()));
    }

}