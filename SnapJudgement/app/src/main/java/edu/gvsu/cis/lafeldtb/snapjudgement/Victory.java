package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;


/**
 * @author Benjamin LaFeldt, Joshua Techentin, Andrew Burns
 */

public class Victory extends ActionBarActivity implements View.OnClickListener {

    private TextView text;
    private Button button;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        text = (TextView) findViewById(R.id.victory);

        if (savedInstanceState != null)
            game = (Game) savedInstanceState.getParcelable("game");
        else {
            Intent what = getIntent();
            game = (Game) what.getParcelableExtra("game");
        }

        Player player = new Player("null");
        for (Player p: game.players) {
            if (p.getScore() > player.getScore())
                player = p;
        }
        text = (TextView) findViewById(R.id.victory);
        text.setText(player.getName() + " wins the match with " + player.getScore() + " points");
        button = (Button) findViewById(R.id.ok);
        button.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_victory, menu);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("game", game);
    }

    @Override
    public void onClick(View view) {
        if (view == button) {
            Intent play = new Intent(Victory.this, TitleScreen.class);
            startActivity(play);
        }
    }
}
