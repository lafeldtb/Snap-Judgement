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
    private ArrayList<Drawable> photos;
    private ArrayList<HashMap<String, Integer>> players;
    private int currentPhoto = 0;

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

        Intent what = getIntent();
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
    public void onClick(View view) {
        if (view == next) {
            if (currentPhoto == photos.size() - 1)
                currentPhoto = 0;
            else
                currentPhoto++;
        }
        else if (view == prev) {
            if (currentPhoto == 0)
                currentPhoto = photos.size() - 1;
            else
                currentPhoto--;
        }
        else if (view == select) {

        }
    }
}
