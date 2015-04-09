package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

public class Standings extends ActionBarActivity implements View.OnClickListener{

    private Button playButton;
    private TextView nextJudge;
    RecyclerView playerList;
    private ArrayList<Player> players;

    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myManager;

    private Game game;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(this);
        nextJudge = (TextView) findViewById(R.id.next_judge);
        playerList = (RecyclerView) findViewById(R.id.listOfPlayers);



        Intent what = getIntent();
        game = (Game) what.getParcelableExtra("game");
        players = game.players;


        myManager = new LinearLayoutManager(this);
        playerList.setLayoutManager(myManager);
        myAdapter = new StandingsAdapter(players);
        playerList.setAdapter(myAdapter);

        nextJudge.setText(game.findJudge().getName());





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_standings, menu);
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
    public void onClick(View v) {
        if(v == playButton) {
            Intent play = new Intent(Standings.this, AdjectiveSelect.class);
            play.putExtra("game", (android.os.Parcelable) game);
            startActivity(play);

        }

    }
}
