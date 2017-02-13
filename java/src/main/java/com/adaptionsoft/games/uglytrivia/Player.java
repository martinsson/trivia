package com.adaptionsoft.games.uglytrivia;

/**
 * Created by johan on 13/02/17.
 */
public class Player {
    private String playerName;
    private boolean inPenaltyBox = false;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void sendToPenaltyBox() {
        inPenaltyBox = true;
    }

//    public void free() {
//
//    }

    @Override
    public String toString() {
        return playerName;
    }
}
