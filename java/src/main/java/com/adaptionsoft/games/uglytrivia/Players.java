package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    private ArrayList players;
    private Purses purses;
    private Player cPlayer;

    public Players(ArrayList players, Purses purses, Player cPlayer) {
        this.players = players;
        this.purses = purses;
        this.cPlayer = cPlayer;
    }

    public Player getcPlayer() {
        return cPlayer;
    }

    int numberOfPlayers() {
        return players.size();
    }

    void nextPlayer() {
        cPlayer.changePlayer();
    }

    boolean noOneHasWonYet() {
        return purses.hasNotYetWon(cPlayer);
    }

    int currentPlayerIndex() {
        return cPlayer.current();
    }

    Object currentPlayerName() {
        return players.get(currentPlayerIndex());
    }

    void currentPlayerAnswersInCorrectly() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayerName() + " was sent to the penalty box");
    }

    void currentPlayerAnswersCorrectly() {
        System.out.println("Answer was correct!!!!");
        purses.gainOneCoin(cPlayer);
        System.out.println(currentPlayerName() + " now has " + purses.coinsFor(cPlayer) + " Gold Coins.");
    }

    int addPlayer(String playerName) {
        players.add(playerName);
        cPlayer.addPlayer(playerName);
        int lastPlayer1 = numberOfPlayers();
        cPlayer.setMaxPlayers(lastPlayer1);
        int lastPlayer = lastPlayer1;
        purses.initialisePlayersPurse(lastPlayer);
        return lastPlayer;
    }
}