package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

public class GameTypeSelect extends ActionBarActivity implements View.OnClickListener{

    private Button newOff, newOn, oldOff, oldOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type_select);

        newOff = (Button) findViewById(R.id.newOffline);
        newOn = (Button) findViewById(R.id.newOnline);
        oldOff = (Button) findViewById(R.id.oldOffline);
        oldOn = (Button) findViewById(R.id.oldOnline);

        newOff.setOnClickListener(this);
        newOn.setOnClickListener(this);
        oldOff.setOnClickListener(this);
        oldOn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_type_select, menu);
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
        if (view == oldOff) {
            Intent play = new Intent(GameTypeSelect.this, OfflinePlayerSelect.class);
            startActivity(play);
        }
        else if (view == oldOn) {
            Intent play = new Intent(GameTypeSelect.this, PlayerSelect.class);
            startActivity(play);
        }
        else if (view == oldOff) {
            Intent play = new Intent(GameTypeSelect.this, OfflinePlayerSelect.class);
            startActivity(play);
        }
        else if (view == oldOff) {
            Intent play = new Intent(GameTypeSelect.this, OfflinePlayerSelect.class);
            startActivity(play);
        }
    }
}
