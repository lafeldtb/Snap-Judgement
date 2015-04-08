package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.text.Editable;

/**
 * Created by Josh Techentin on 4/1/2015.
 */
public class Player {

    private int score = 0;
    private String name = "John Doe";
    boolean isJudge = false;

    public Player(String s) {
        name = s;
        score = 0;
        isJudge = false;
    }

    public String getName() {
        return name;
    }
}
