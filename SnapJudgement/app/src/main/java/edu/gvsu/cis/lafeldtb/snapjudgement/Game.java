package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import edu.gvsu.cis.lafeldtb.snapjudgement.Player;

/**
 * Started by Josh Techentin on 3/28/2015.
 */
public class Game implements Parcelable {


    public ArrayList<Player> players = new ArrayList<Player>();
    public int scoreLimit;
    public String adjective, fingerprint;
    public int currentRound = 0;

    public Game( ArrayList<Player> players, int scoreLimit, String adjective) {

        this.players = players;
        this.scoreLimit = scoreLimit;
        this.adjective = adjective;

        //sets a (hopefully) unique fingerprint for this game based on current time
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");
        fingerprint = sdf.format(cal.getTime());


    }

    public void nextJudge() {
        if (players.get(players.size() - 1).getJudge()) {
            players.get(players.size() - 1).setJudge(false);
            players.get(0).setJudge(true);
        }
        else {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getJudge()) {
                    players.get(i).setJudge(false);
                    players.get(i + 1).setJudge(true);
                    break;
                }
            }
        }
    }

    public Player findJudge() {
        for(Player p: players) {
            if(p.getJudge()) return p;
        }
        players.get(0).setJudge(true);
        return players.get(0);
    }

    public boolean gameEnded() {
        for(Player p: players) {
            if(p.getScore() == scoreLimit) {
                return true;
            }
        }

        return false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(scoreLimit);
        dest.writeTypedList(players);
        dest.writeString(adjective);
        dest.writeString(fingerprint);
        dest.writeInt(currentRound);
    }

    public static final Parcelable.Creator<Game> CREATOR
            = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    /** recreate object from parcel */
    private Game(Parcel in) {
        scoreLimit = in.readInt();
        in.readTypedList(players, Player.CREATOR);
        adjective = in.readString();
        fingerprint = in.readString();
        currentRound = in.readInt();
    }

    public int getRound(){
        return currentRound;
    }

    public String getAdjective(){
        return adjective;
    }
}
