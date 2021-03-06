package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

/**
 * @author Benjamin LaFeldt, Joshua Techentin, Andrew Burns
 */

public class ParticipantTurn extends ActionBarActivity implements View.OnClickListener {

    private Button captureButton, acceptButton;
    //Button shareButton;
    static int PHOTO_REQUEST = 1;
    ImageView image;
    Integer currentPlayer = 0;
    private TextView text;
    private Game game;
    private Player person;
    private ArrayList<Player> nextParticipants;
    private int ID;
    private TextView currentPlayerText;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST) {
            if (resultCode == RESULT_OK) {
                // grab the image only after the user selects "ok"
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap myphoto = (Bitmap) extras.get("data");
                    image.setImageBitmap(myphoto);
                } else {
                    /* get the public dir for images */

                    //For some reason, the code cannot find the file even though it should be able to. It just gets set as null.
                    File imgDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    String t = imgDir.getAbsolutePath() + "/" + game.fingerprint + "-" + game.currentRound + "-" + person.getName() + ".jpg";
                    Drawable imgDrwbl = Drawable.createFromPath(t);
                    image.setImageDrawable(imgDrwbl);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_turn);

        captureButton = (Button) findViewById(R.id.capture_button);
        image = (ImageView) findViewById(R.id.imageView);
        acceptButton = (Button) findViewById(R.id.accept_button);
        text = (TextView) findViewById(R.id.adj);

        Intent what = getIntent();
        game = (Game) what.getParcelableExtra("game");
        person = (Player) what.getParcelableExtra("person");
        nextParticipants = what.getParcelableArrayListExtra("participants");
        ID = what.getIntExtra("ID", 0);

        text.setText(game.getAdjective());


        captureButton.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
        //currentPlayerText = (TextView) findViewById(R.id.accept_button);

       // currentPlayerText.setText(person.getName().toString() + "'s Turn");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View v) {
        if (v == acceptButton) {
            if (ID >= nextParticipants.size()) {
                Intent play = new Intent(ParticipantTurn.this, JudgeTurn.class);
                play.putExtra("game", game);
                play.putExtra("participants", nextParticipants);
                startActivity(play);
            } else {
                Intent play = new Intent(ParticipantTurn.this, TurnNotifier.class);
                play.putExtra("game", game);
                play.putExtra("participants", nextParticipants);
                play.putExtra("ID", ID);
                startActivity(play);
            }
        } else if (v == captureButton) {
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (captureIntent.resolveActivity(getPackageManager()) != null) {
                File imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String s = game.fingerprint + "-" + game.currentRound + "-" + person.getName() + ".jpg";
                File imageFile = new File(imageDir, s);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(captureIntent, PHOTO_REQUEST);
            }
        }
    }
}
