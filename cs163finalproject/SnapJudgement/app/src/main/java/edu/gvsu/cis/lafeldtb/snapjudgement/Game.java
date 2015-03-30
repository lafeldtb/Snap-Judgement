package edu.gvsu.cis.lafeldtb.applestoapples;

import java.util.ArrayList;
import java.util.Random;

/**
 * Started by Josh Techentin on 3/28/2015.
 */
public class Game {

    private int numberOfPlayers, currentJudge, numberOfTurns;
    private ArrayList<Player> players;

    public Game(int numPlayers, int turns) {
        numberOfPlayers = numPlayers;
        numberOfTurns = turns;
        for (int i = 0; i < numberOfPlayers; i++)
        {
            Player p = new Player(i);
            players.add(p);
        }
        Random r = new Random();
        currentJudge = r.nextInt(numberOfPlayers);
    }

    public void advanceNextTurn() {
        if (currentJudge == players.size() - 1)
            currentJudge = 0;
        else
            currentJudge++;
    }

    public void awardPoints(int player) {
        players.get(player).score += 10;
    }

    public class Player {

        public int score, ID;

        public Player(int id) {
            score = 0;
            ID = id;
        }
    }
}
