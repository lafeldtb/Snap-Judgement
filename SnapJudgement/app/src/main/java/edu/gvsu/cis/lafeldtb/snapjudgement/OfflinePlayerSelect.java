package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

public class OfflinePlayerSelect extends ActionBarActivity implements View.OnClickListener {

    private Button newPlayer, newGame;
    private RecyclerView playerList;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myManager;
    private ArrayList<String> playerNames;
    private ArrayList<Integer> scores;
    private int numTurns, numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This method creates the RecyclerView and instantiates the newPlayer button
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_player_select);

        newPlayer = (Button) findViewById(R.id.newPlayer);
        playerList = (RecyclerView) findViewById(R.id.listOfPlayers);

        newPlayer.setOnClickListener(this);

        myManager = new LinearLayoutManager(this);
        playerList.setLayoutManager(myManager);

        myAdapter = new MyWordAdapter(playerNames);
        playerList.setAdapter(myAdapter);

        Intent what = getIntent();

        numPlayers = what.getIntExtra("players", 3);
        numTurns = what.getIntExtra("turns", 8);

        for (int i = 0; i < numPlayers; i++) {
            scores.add(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_offline_player_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        /* This method checks to see if the user wants to add a new player or start the game

        If the user adds a new player, then AddPlayer will run and add an EditText to the
        RecyclerView, allowing the user to input a name for the player.

        When three players are created, then the user will be able to push the start game button

        If the user pushes the button newGame, then start a new game

        If there are less than three players, then the start game button will not work
         */
        if (view == newPlayer) {
            myAdapter.notifyDataSetChanged();
            if (playerNames.size() == 3) {
                newGame = (Button) findViewById(R.id.newGame);
                newGame.setOnClickListener(this);
                newGame.setText("START NEW GAME");
            }
        }
        else if (view == newGame) {
            Intent play = new Intent(OfflinePlayerSelect.this, AdjectiveSelect.class);
            play.putExtra("players", playerNames);
            play.putExtra("scores", scores);
            startActivity(play);
        }
    }
}
