package edu.gvsu.cis.lafeldtb.snapjudgement;

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

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

public class OfflinePlayerSelect extends ActionBarActivity implements View.OnClickListener {

    private Button newPlayer, newGame;
    private RecyclerView playerList;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myManager;
    private ArrayList<String> players;
    private AddPlayer task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_player_select);

        newPlayer = (Button) findViewById(R.id.newPlayer);
        playerList = (RecyclerView) findViewById(R.id.listOfPlayers);

        newPlayer.setOnClickListener(this);

        myManager = new LinearLayoutManager(this);
        playerList.setLayoutManager(myManager);

        myAdapter = new MyWordAdapter(players);
        playerList.setAdapter(myAdapter);

        task = new AddPlayer();
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
        if (view == newPlayer) {
            task.execute();
            if (players.size() == 3) {
                newGame = (Button) findViewById(R.id.newGame);
                newGame.setOnClickListener(this);
                newGame.setText("START NEW GAME");
            }
        }
        else if (view == newGame) {

        }
    }

    public class AddPlayer extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            myAdapter.notifyDataSetChanged();
        }
    }
}
