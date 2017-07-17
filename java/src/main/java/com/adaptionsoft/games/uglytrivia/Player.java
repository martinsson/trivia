package com.adaptionsoft.games.uglytrivia;

public class Player {
    private int purse = 0;
    private boolean inPenaltyBox;
    int place = 0;
    public String name;

    public Player(String name) {
        this.name = name;
    }

    public boolean winCoin() {
        purse++;
        System.out.println(name + " now has " + purse + " Gold Coins.");

        boolean didPlayerNotWin = purse != 6;
        return didPlayerNotWin;
    }

    public void sendToPenaltyBox() {
        System.out.println("Question was incorrectly answered");
        inPenaltyBox = true;
    }

    public void allowOutOfPenaltyBox() {
        inPenaltyBox = false;
    }

    public boolean isOutOfPenaltyBox() {
        return !inPenaltyBox;
    }
}
