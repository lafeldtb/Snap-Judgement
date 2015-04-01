package edu.gvsu.cis.lafeldtb.snapjudgement;

/**
 * Created by Josh Techentin on 4/1/2015.
 */
public class Player {

    public int score;
    public String name;
    public boolean isJudge;

    public Player(String s) {
        name = s;
        score = 0;
        isJudge = false;
    }
}
