package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Benjamin LaFeldt, Joshua Techentin, Andrew Burns
 */

public class AdjectiveSelect extends ActionBarActivity implements View.OnClickListener {

    private final String URL = "http://api.wordnik.com/v4/words.json/randomWord?";
    private final String URL2 = "hasDictionaryDef=true&includePartOfSpeech=adjective&minCorpusCount=100&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=1&minLength=2&maxLength=8";
    private final String URL3 = "&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";
    private String adj1 = null, adj2 = null, adj3 = null;
    private Button btn1, btn2, btn3;
    private Game game;
    private Set<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjective_select);

        btn1 = (Button) findViewById(R.id.adjective1);
        btn2 = (Button) findViewById(R.id.adjective2);
        btn3 = (Button) findViewById(R.id.adjective3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getParcelable("game");
            adj1 = savedInstanceState.getString("adj1");
            adj2 = savedInstanceState.getString("adj2");
            adj3 = savedInstanceState.getString("adj3");
        }
        else {
            Intent what = getIntent();
            game = (Game) what.getParcelableExtra("game");
        }
        words = new HashSet<String>();

        if (adj3 == null) {
            GetAdjectives myTask = new GetAdjectives();
            myTask.execute(URL + URL2 + URL3);
        }
        else {
            btn1.setText(adj1);
            btn2.setText(adj2);
            btn3.setText(adj3);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adjective_select, menu);
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
        outState.putString("adj1", adj1);
        outState.putString("adj2", adj2);
        outState.putString("adj3", adj3);
    }

    @Override
    public void onClick(View view) {
        if (view == btn1) {
            boolean addPlayers = false;
            ArrayList<Player> nextParticipants = new ArrayList<Player>();
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getJudge())
                    addPlayers = true;
                else if (addPlayers)
                    nextParticipants.add(game.players.get(i));
            }
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getJudge())
                    addPlayers = false;
                else if (addPlayers)
                    nextParticipants.add(game.players.get(i));
            }
            int ID = 0;
            Intent play = new Intent(AdjectiveSelect.this, TurnNotifier.class);
            game.adjective = btn1.getText().toString();
            play.putExtra("game", game);
            play.putExtra("participants", nextParticipants);
            play.putExtra("ID", ID);
            startActivity(play);
        }
        else if (view == btn2) {
            boolean addPlayers = false;
            ArrayList<Player> nextParticipants = new ArrayList<Player>();
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getJudge())
                    addPlayers = true;
                else if (addPlayers)
                    nextParticipants.add(game.players.get(i));
            }
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getJudge())
                    addPlayers = false;
                else if (addPlayers)
                    nextParticipants.add(game.players.get(i));
            }
            int ID = 0;
            Intent play = new Intent(AdjectiveSelect.this, TurnNotifier.class);
            game.adjective = btn2.getText().toString();
            play.putExtra("game", game);
            play.putExtra("participants", nextParticipants);
            play.putExtra("ID", ID);
            startActivity(play);
        }
        else if (view == btn3) {
            boolean addPlayers = false;
            ArrayList<Player> nextParticipants = new ArrayList<Player>();
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getJudge())
                    addPlayers = true;
                else if (addPlayers)
                    nextParticipants.add(game.players.get(i));
            }
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getJudge())
                    addPlayers = false;
                else if (addPlayers)
                    nextParticipants.add(game.players.get(i));
            }
            int ID = 0;
            Intent play = new Intent(AdjectiveSelect.this, TurnNotifier.class);
            game.adjective = btn3.getText().toString();
            play.putExtra("game", game);
            play.putExtra("participants", nextParticipants);
            play.putExtra("ID", ID);
            startActivity(play);
        }
    }

    private class GetAdjectives extends AsyncTask<String, Void, Void>
    {
        // Assigns random adjectives to each of the options the judge has to choose from

        @Override
        protected Void doInBackground(String... params) {
            HttpClient client = new DefaultHttpClient();
/* set up the URL for the GET request */
            try {
                do {
                    HttpGet hget = new HttpGet(params[0]);
                    HttpResponse resp = client.execute(hget);

                    InputStream stream = resp.getEntity().getContent();
                    char[] buffer = new char[1024];

                    InputStreamReader reader = new InputStreamReader(stream);
                    int len;

                    StringBuffer sb = new StringBuffer();
                    len = reader.read(buffer, 0, 1024);
                    while (len != -1) {
                        sb.append(buffer, 0, len);
                        len = reader.read(buffer, 0, 1024);
                    }
                    JSONObject obj = null;
                    String name = null;
                    obj = new JSONObject(sb.toString());
                    name = obj.getString("word");
                    words.add(name);
                } while (words.size() < 3);
            } catch (Exception e) {
                Log.e("OOPS", "There was an error " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            int count = 0;
            for (String s: words) {
                if (count == 0) {
                    btn1.setText(s);
                    adj1 = s;
                }
                else if (count == 1) {
                    btn2.setText(s);
                    adj2 = s;
                }
                else if (count == 2) {
                    btn3.setText(s);
                    adj3 = s;
                }
                count++;
            }
        }
    }
}
