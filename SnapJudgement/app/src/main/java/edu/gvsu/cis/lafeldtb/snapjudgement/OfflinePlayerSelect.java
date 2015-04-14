package edu.gvsu.cis.lafeldtb.snapjudgement;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.games.*;

import java.util.ArrayList;
import java.util.HashMap;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

/**
 * @author Benjamin LaFeldt, Joshua Techentin, Andrew Burns
 */

public class OfflinePlayerSelect extends ActionBarActivity implements View.OnClickListener {

    private Button newPlayer, newGame;
    private ArrayList<Player> players;



    private final int BCOLOR = 0xff9c9c9c;
    private final int ACOLOR = 0x86D618;
    private boolean clickable = true;
    final Context context = this;
    RecyclerView playerList;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myManager;    private Game game;
    private int  scoreLimit, count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_player_select);




        players = new ArrayList<Player>();

        if(savedInstanceState != null) {
            players = savedInstanceState.getParcelableArrayList("players");
            game = savedInstanceState.getParcelable("game");
            clickable = savedInstanceState.getBoolean("clickable");

        }

        newPlayer = (Button) findViewById(R.id.newPlayer);
        newGame = (Button) findViewById(R.id.newGame);
        playerList = (RecyclerView) findViewById(R.id.listOfPlayers);

        Intent what = getIntent();
        scoreLimit = what.getIntExtra("ScoreLimit", 5);
        game = new Game(players, scoreLimit, "null");


        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(game.players.size() >= 3) {
                    Intent toStandings = new Intent(OfflinePlayerSelect.this, Standings.class);
                    toStandings.putExtra("game", game);
                    startActivity(toStandings);
                } else {
                    Toast.makeText(OfflinePlayerSelect.this, "Need 3+ players to continue", Toast.LENGTH_LONG).show();
                }


            }
        });

        myManager = new LinearLayoutManager(this);
        playerList.setLayoutManager(myManager);
        myAdapter = new MyWordAdapter(players);
        playerList.setAdapter(myAdapter);
        newPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        //gets the popup.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.popup, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        //sets popup.xml to alertDialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextPopup);

        //set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //get user input and set it to a new student
                                //edit text
                                //creates a new player
                                boolean used = false;
                                if(userInput.getText().length() > 0) {
                                    for(Player p: players) {
                                        if(userInput.getText().toString().equals(p.getName())) {
                                           used = true;
                                        }
                                    }
                                    if(!used) {
                                    players.add(new Player(userInput.getText().toString()));
                                    myAdapter.notifyDataSetChanged();
                                    }
                                }

                                //lets the button work if there are 3 or more players
                                if (players.size() >= 3) {
                                    clickable = true;

                                    newGame.setClickable(clickable);
                                } else {
                                    clickable = false;

                                    newGame.setClickable(clickable);
                                }


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        //create Alert Dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        //show it
        alertDialog.show();

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("players", players);
        outState.putParcelable("game", game);
        outState.putBoolean("clickable", clickable);
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
        /** This method checks to see if the user wants to add a new player or start the game

        If the user adds a new player, then AddPlayer will run and add an EditText to the
        RecyclerView, allowing the user to input a name for the player.

        When at least three players are created, then the user will be able to push the start game button

        If the user pushes the button newGame, then start a new game

        If there are less than three players, then the start game button will not work
         */
    }
}
