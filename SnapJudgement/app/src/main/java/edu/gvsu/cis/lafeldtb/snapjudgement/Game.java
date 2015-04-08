package edu.gvsu.cis.lafeldtb.snapjudgement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import edu.gvsu.cis.lafeldtb.snapjudgement.Player;

/**
 * Started by Josh Techentin on 3/28/2015.
 */
public class Game implements Serializable {

    public int numberOfTurns, currentTurn;
    public String name;
    public ArrayList<Player> players;

    public Game(int turns, String n) {
        numberOfTurns = turns;
        currentTurn = 1;
        name = n;
        players = new ArrayList<Player>();
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void nextJudge() {
        if (players.get(players.size() - 1).getJudge()) {
            players.get(players.size() - 1).setJudge(false);
            players.get(0).setJudge(true);
            currentTurn++;
        }
        else {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getJudge()) {
                    players.get(i).setJudge(false);
                    players.get(i + 1).setJudge(true);
                }
            }
        }
    }

    public boolean gameEnded() {
        return currentTurn >= numberOfTurns;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Turns : ")
                .append(this.numberOfTurns)
                .append(" Current Turn : ")
                .append(this.currentTurn)
                .append(" Players : ")
                .append(this.players).toString();
    }
}
