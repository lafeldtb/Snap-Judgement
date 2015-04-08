package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

/**
 * Created by Josh Techentin on 4/1/2015.
 */
public class Player implements Parcelable{

    private int score = 0;
    private String name = "John Doe";
    private boolean isJudge = false;
    private int timesJudge = 0;         //to rotate who is judge

    public Player(String s) {
        name = s;
        score = 0;
        isJudge = false;
    }

    /**Getters and Setters for the instance variables  */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getJudge() {
        return isJudge;
    }

    public void setJudge(boolean b) {
        this.isJudge = b;
    }

    public int getTimesJudge() {
        return timesJudge;
    }

    public void setTimesJudge(int i) {
        timesJudge = i;
    }


    /** the following just enables players to be in saved instance state by using a parseable */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(score);
        dest.writeString(name);
        dest.writeByte((byte) (isJudge ? 1 : 0));
        dest.writeInt(timesJudge);
    }
    public static final Parcelable.Creator<Player> CREATOR
            = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    /** recreate object from parcel */
    private Player(Parcel in) {
        score = in.readInt();
        name = in.readString();
        isJudge = in.readByte() != 0;
        timesJudge = in.readInt();
    }

}
