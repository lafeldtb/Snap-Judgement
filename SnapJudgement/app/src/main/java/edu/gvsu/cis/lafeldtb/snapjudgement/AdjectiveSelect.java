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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AdjectiveSelect extends ActionBarActivity implements View.OnClickListener {

    private final String URL = "http://api.wordnik.com:80/v4/words.json/randomWord?hasDictionaryDef=true&includePartOfSpeech=adjective&minCorpusCount=0&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=1&minLength=2&maxLength=-1&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";
    private String adj1 = null, adj2 = null, adj3 = null;
    private Button btn1, btn2, btn3;
    private ArrayList<Integer> scores;
    private ArrayList<String> playerNames;

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

        Intent what = getIntent();
        scores = what.getIntegerArrayListExtra("scores");
        playerNames = what.getStringArrayListExtra("players");

        GetAdjectives myTask = new GetAdjectives();
        while(adj1 == null || adj1.equals(adj2) || adj1.equals(adj3) || adj2.equals(adj3))
            myTask.execute();
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
    public void onClick(View view) {
        if (view == btn1) {
            Intent play = new Intent(AdjectiveSelect.this, ParticipantTurn.class);
            play.putExtra("adjective", btn1.getText().toString());
            play.putExtra("players", playerNames);
            play.putExtra("scores", scores);
            startActivity(play);
        }
        else if (view == btn2) {
            Intent play = new Intent(AdjectiveSelect.this, ParticipantTurn.class);
            play.putExtra("adjective", btn2.getText().toString());
            play.putExtra("players", playerNames);
            play.putExtra("scores", scores);
            startActivity(play);
        }
        else if (view == btn3) {
            Intent play = new Intent(AdjectiveSelect.this, ParticipantTurn.class);
            play.putExtra("adjective", btn3.getText().toString());
            play.putExtra("players", playerNames);
            play.putExtra("scores", scores);
            startActivity(play);
        }
    }

    private class GetAdjectives extends AsyncTask<Void, Void, String>
    {
        // Assigns random adjectives to each of the options the judge has to choose from

        @Override
        protected String doInBackground(Void... params) {
            HttpClient client = new DefaultHttpClient();
            HttpGet hget = new HttpGet(URL);
            try {
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
                return sb.toString();
            }
            catch (IOException e) {
                Log.e("OOPS", "There was an error " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            JSONObject obj = null;
            try {
                obj = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String name = null;
            try {
                name = obj.getString("word");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (adj1 == null)
                adj1 = name;
            else if (adj2 == null || adj2.equals(adj1))
                adj2 = name;
            else if (adj3 == null || adj3.equals(adj1) || adj3.equals(adj2))
                adj3 = name;
        }
    }
}
