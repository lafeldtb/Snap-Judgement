package edu.gvsu.cis.lafeldtb.snapjudgement;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AdjectiveSelect extends ActionBarActivity implements View.OnClickListener {

    private final String URL = "http://www.randomlists.com/random-adjectives";
    private String adj1 = null, adj2 = null, adj3 = null;
    private Button btn1, btn2, btn3;

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
        // Will check which button you clicked and will send the current text of the button to the next activity
    }

    private class GetAdjectives extends AsyncTask<Void, Void, String>
    {
        // Assigns random adjectives to each of the options the judge has to choose from

        @Override
        protected String doInBackground(Void... params) {
            HttpClient client = new DefaultHttpClient();
/* set up the URL for the GET request */
            HttpGet hget = new HttpGet(URL); /* params is the variable-args of
                                                doInBackground */
            try {
  /* send the request to rhymebrain server, and get its response */
                HttpResponse resp = client.execute(hget);

  /* use an input stream to read the text response */
                InputStream stream = resp.getEntity().getContent();
                char[] buffer = new char[1024];

                InputStreamReader reader = new InputStreamReader(stream);
                int len;

                StringBuffer sb = new StringBuffer();
  /* read 1K chunk at a time and append it to a StringBuffer */
                len = reader.read(buffer, 0, 1024);

  /* "len" tells us how many bytes are actually read */
                while (len != -1) { /* len is -1 when no more data to read */
                    sb.append(buffer, 0, len);
                    len = reader.read(buffer, 0, 1024); /* read the next chunk */
                }
                String result = sb.toString();
                return result; /* pass the entire buffer to onPostExecute */
            }
            catch (IOException e) {
                Log.e("OOPS", "There was an error " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (adj1 == null)
                adj1 = s;
            else if (adj2 == null || adj2.equals(adj1))
                adj2 = s;
            else if (adj3 == null || adj3.equals(adj1) || adj3.equals(adj2))
                adj3 = s;
        }
    }
}
