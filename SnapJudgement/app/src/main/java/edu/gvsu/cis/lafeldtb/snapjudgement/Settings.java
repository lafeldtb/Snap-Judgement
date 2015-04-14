package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

/**
 * @author Benjamin LaFeldt, Joshua Techentin, Andrew Burns
 */

public class Settings extends ActionBarActivity implements View.OnClickListener {

    private Button accept;
    private EditText scoreLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        accept = (Button) findViewById(R.id.acceptChangesButton);
        scoreLimit = (EditText) findViewById(R.id.score_limit);


        accept.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
        if (view == accept) {
            Intent play = new Intent(Settings.this, OfflinePlayerSelect.class);
            int scoreLim = Integer.parseInt(scoreLimit.getText().toString());
            play.putExtra("scoreLimit", scoreLim);
            startActivity(play);
        }
    }
}
