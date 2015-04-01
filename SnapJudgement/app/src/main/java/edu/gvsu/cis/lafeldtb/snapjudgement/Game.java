package edu.gvsu.cis.lafeldtb.snapjudgement;

import java.util.ArrayList;
import java.util.Random;

import edu.gvsu.cis.lafeldtb.snapjudgement.Player;

/**
 * Started by Josh Techentin on 3/28/2015.
 */
public class Game {

    public int numberOfTurns, currentTurn;
    public ArrayList<Player> players;

    public Game(int turns) {
        numberOfTurns = turns;
        currentTurn = 1;
        players = new ArrayList<Player>();
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void nextJudge() {
        if (players.get(players.size() - 1).isJudge) {
            players.get(players.size() - 1).isJudge = false;
            players.get(0).isJudge = true;
            currentTurn++;
        }
        else {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).isJudge) {
                    players.get(i).isJudge = false;
                    players.get(i + 1).isJudge = true;
                }
            }
        }
    }

    public boolean gameEnded() {
        return currentTurn >= numberOfTurns;
    }
}
