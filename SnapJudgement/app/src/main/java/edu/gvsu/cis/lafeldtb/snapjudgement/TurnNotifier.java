package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

/**
 * @author Benjamin LaFeldt, Joshua Techentin, Andrew Burns
 */

public class TurnNotifier extends ActionBarActivity implements View.OnClickListener {

    private Game game;
    private ArrayList<Player> nextParticipants;
    private TextView name;
    private Button button;
    private Player person;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_notifier);

        name = (TextView) findViewById(R.id.person);
        button = (Button) findViewById(R.id.startTurn);
        button.setOnClickListener(this);

        Intent what = getIntent();
        game = (Game) what.getParcelableExtra("game");
        nextParticipants = what.getParcelableArrayListExtra("participants");
        ID = what.getIntExtra("ID", 0);
        person = nextParticipants.get(ID);
        ID++;

        name.setText(person.getName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_turn_notifier, menu);
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
        if (view == button) {
            Intent play = new Intent(TurnNotifier.this, ParticipantTurn.class);
            play.putExtra("game", game);
            play.putExtra("person", person);
            play.putExtra("participants", nextParticipants);
            play.putExtra("ID", ID);
            startActivity(play);
        }
    }
}
