package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class JudgeTurn extends ActionBarActivity implements View.OnClickListener {

    private Button next, prev, select;
    private ImageView image;
    private TextView text;
    private int currentPhoto = 0;
    private Game game;
    private ArrayList<Player> participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge_turn);

        next = (Button) findViewById(R.id.ok);
        prev = (Button) findViewById(R.id.prev);
        select = (Button) findViewById(R.id.choose);
        image = (ImageView) findViewById(R.id.currentImage);
        text = (TextView) findViewById(R.id.winnerText);

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        select.setOnClickListener(this);

        if (savedInstanceState != null) {
            currentPhoto = savedInstanceState.getInt("current photo");
            game = (Game) savedInstanceState.getParcelable("game");
        }
        else {
            Intent what = getIntent();
            game = (Game) what.getParcelableExtra("game");
            participants = what.getParcelableArrayListExtra("participants");
        }
        image.setImageBitmap(participants.get(0).image);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_judge_turn, menu);
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

        outState.putInt("current photo", currentPhoto);
        outState.putParcelable("game", game);
    }

    @Override
    public void onClick(View view) {
        if (view == next) {
            if (currentPhoto == participants.size() - 1)
                currentPhoto = 0;
            else
                currentPhoto++;
            image.setImageBitmap(participants.get(currentPhoto).image);
        }
        else if (view == prev) {
            if (currentPhoto == 0)
                currentPhoto = participants.size() - 1;
            else
                currentPhoto--;
            image.setImageBitmap(participants.get(currentPhoto).image);
        }
        else if (view == select) {
            text.setText(participants.get(currentPhoto).getName() + " wins a point");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            participants.get(currentPhoto).setScore(participants.get(currentPhoto).getScore() + 1);
            if (game.gameEnded()) {
                Intent play = new Intent(JudgeTurn.this, Victory.class);
                play.putExtra("game", game);
                startActivity(play);
            }
            else {
                game.nextJudge();
                Intent play = new Intent(JudgeTurn.this, Standings.class);
                play.putExtra("game", game);
                startActivity(play);
            }
        }
    }
}
