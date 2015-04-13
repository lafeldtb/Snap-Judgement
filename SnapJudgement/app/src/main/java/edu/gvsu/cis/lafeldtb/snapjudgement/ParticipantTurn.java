package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.content.Intent;
import android.graphics.Bitmap;
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

import java.io.File;

import edu.gvsu.cis.lafeldtb.snapjudgement.R;

public class ParticipantTurn extends ActionBarActivity implements View.OnClickListener {

    private Button captureButton, acceptButton;
    //Button shareButton;
    static int PHOTO_REQUEST = 1;
    private ImageView image;
    private Integer currentPlayer = 0, currentRound = 0;
    private TextView currentPlayerText;
    public Game game;

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
                    File imgDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    Drawable imgDrwbl = Drawable.createFromPath(imgDir.getAbsolutePath() + ("/" + currentRound.toString() + "-" + currentPlayer.toString() + ".jpg"));
                    image.setImageDrawable(imgDrwbl);

                }
            }
        }
    }

    private Intent createImageShareIntent(String imgName) {
    /* ACTION_SEND = share */
        Intent shareImg = new Intent(Intent.ACTION_SEND);


    /* determine the file location */
        File imgDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imgFile = new File(imgDir, imgName);

    /* attach the file to the share intent */
        shareImg.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imgFile));
        shareImg.setType("image/jpeg");
        return shareImg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_turn);

        captureButton = (Button) findViewById(R.id.capture_button);
        image = (ImageView) findViewById(R.id.imageView);
        acceptButton = (Button) findViewById(R.id.accept_button);
        currentPlayerText = (TextView) findViewById(R.id.accept_button);

        //gets game info that's hopefully been passed correctly
        Intent what = getIntent();
        game = what.getParcelableExtra("game");

        currentPlayerText.setText(game.players.get(currentPlayer - 1).toString() + "'s Turn");

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (captureIntent.resolveActivity(getPackageManager()) != null) {
                    File imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File imageFile = new File(imageDir, (currentRound.toString() + currentPlayer.toString() + ".jpg"));
                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                    startActivityForResult(captureIntent, PHOTO_REQUEST);
                }
            }
        });


        /*This button does not work yet. We need to determine how to pass from one player's turn
        to the next player's turn. My current idea is just to start an intent that goes into the
        same class, but I don't think it'll work. Another idea is to create a separate activity
        that tracks everyones turns where each player can tap their cell and take their picture
        in any order and then a "Judge" button that the Judge taps once everyone has  taken a picture.
         Just an idea, but it could work easier/better.
        */

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPlayer = new Intent(ParticipantTurn.this, ParticipantTurn.class);
                currentPlayer++;
                nextPlayer.putExtra("currentPlayer", currentPlayer);
                nextPlayer.putExtra("game", game);
                startActivity(nextPlayer);
            }
        });
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

    }
}
