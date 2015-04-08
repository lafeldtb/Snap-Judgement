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

import com.google.android.gms.games.*;

import java.util.ArrayList;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

public class OfflinePlayerSelect extends ActionBarActivity implements View.OnClickListener {

    private Button newPlayer, newGame;
    private ArrayList<Player> players;


    private final int BCOLOR = 0xffdedc00;
    private final int ACOLOR = 0xff9c9c9c;
    final Context context = this;
    RecyclerView playerList;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myManager;




    private int numTurns, numPlayers, scoreLimit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_player_select);




        players = new ArrayList<Player>();

        if(savedInstanceState != null) {
            players = savedInstanceState.getParcelableArrayList("players");

        }

        newPlayer = (Button) findViewById(R.id.newPlayer);
        newGame = (Button) findViewById(R.id.newGame);
        playerList = (RecyclerView) findViewById(R.id.listOfPlayers);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent toStandings = new Intent(OfflinePlayerSelect.this, Standings.class);
                toStandings.putParcelableArrayListExtra("players", players);
                toStandings.putExtra("ScoreLimit", scoreLimit);
                startActivity(toStandings);

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
                                players.add(new Player(userInput.getText().toString()));

                                myAdapter.notifyDataSetChanged();
                                //lets the button work if there are 3 or more players
                                if (players.size() >= 3) {

                                    newGame.setBackgroundColor(BCOLOR);
                                    newGame.setClickable(true);
                                } else {
                                    newGame.setBackgroundColor(ACOLOR);
                                    newGame.setClickable(false);
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



        Intent what = getIntent();

        numPlayers = what.getIntExtra("players", 3);
        numTurns = what.getIntExtra("turns", 8);
        scoreLimit = what.getIntExtra("ScoreLimit", 5);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("players", players);
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
