// START DATE: 5/30/22

package com.asciirpg.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.asciirpg.entity.Blocker;
import com.asciirpg.entity.Detractor;
import com.asciirpg.entity.Entity;
import com.asciirpg.entity.Healer;
import com.asciirpg.entity.Player;
import com.asciirpg.entity.Remover;
import com.asciirpg.util.Clock;
import com.asciirpg.util.Map;
import com.asciirpg.util.Position;
import java.util.ArrayList;
import java.util.Random;

/*
TODO (Suggestions)
- add sounds
- change button colors
- add a key so people know what each entity does
- remove "header"
 */

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

        // Initializes player
        player = new Player();
        gameMap.draw(player);
        gameMap.processColor();
        // TODO: Clean up code by putting HP and score updates in separate functions
        updateHP();
        updateScore();

        // Initializes frame clock
        clock = new Clock();
        Log.d("FRAME", String.valueOf(clock.getFrame()));
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
            player.setPosition(player.getPos().getRow(),player.getPos().getCol() - 1);
            gameMap.draw(player);
            player.setScore(player.getScore() + 1);
            updateScore();
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
            player.setPosition(player.getPos().getRow(),player.getPos().getCol() + 1);
            gameMap.draw(player);
            player.setScore(player.getScore() + 1);
            updateScore();
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
            player.setPosition(player.getPos().getRow() - 1, player.getPos().getCol());
            gameMap.draw(player);
            player.setScore(player.getScore() + 1);
            updateScore();
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
            player.setPosition(player.getPos().getRow() + 1, player.getPos().getCol());
            gameMap.draw(player);
            player.setScore(player.getScore() + 1);
            updateScore();
        }

        intermission();
    }

    private void intermission() {
        clock.nextFrame();

        player.setHP(player.getHP() - 1);
        updateHP();

        // Switch to GameOver activity when player runs out of HP
        if(player.getHP() <= 0) {
            Intent i = new Intent(MainActivity.this, GameOver.class);
            i.putExtra("score", player.getScore());
            startActivity(i);
        }

        // Entities are spawned every 10 frames
        if(clock.getFrame() % 10 == 0) {
            Random numGen = new Random();
            int entityNum = numGen.nextInt(4) + 1;
            Position p;
            do {
                p = new Position(numGen.nextInt(5) + 1, numGen.nextInt(5) + 1);
            } while(gameMap.occupiedPos(p));

            switch(entityNum) {
                case 1:
                    Log.d("SPAWN", "Spawning new Blocker!");
                    Blocker b = new Blocker(p);
                    gameMap.draw(b);
                    break;
                case 2:
                    Log.d("SPAWN", "Spawning new Remover!");
                    Remover r = new Remover(p);
                    gameMap.draw(r);
                    break;
                case 3:
                    Log.d("SPAWN", "Spawning new Detractor!");
                    Detractor d = new Detractor(p);
                    gameMap.draw(d);
                    break;
                case 4:
                    Log.d("SPAWN", "Spawning new Healer!");
                    Healer h = new Healer(p);
                    gameMap.draw(h);
                    break;
            }
        }

        gameMap.processColor();
        Log.d("FRAME", String.valueOf(clock.getFrame()));
    }

    // Transfers new HP value to TextView
    private void updateHP() {
        String s = ((TextView) findViewById(R.id.hp)).getText().toString();
        s = s.substring(0, 4) + String.valueOf(player.getHP());
        ((TextView) findViewById(R.id.hp)).setText(s);
    }

    // Transfers new score value to TextView
    private void updateScore() {
        String s = ((TextView) findViewById(R.id.score)).getText().toString();
        s = s.substring(0, 7) + String.valueOf(player.getScore());
        ((TextView) findViewById(R.id.score)).setText(s);
    }

}