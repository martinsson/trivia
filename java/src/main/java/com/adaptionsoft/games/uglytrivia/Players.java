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

    public void setPurses(Purses purses) {
        this.purses = purses;
    }

    public Player getcPlayer() {
        return cPlayer;
    }

    public void setcPlayer(Player cPlayer) {
        this.cPlayer = cPlayer;
    }

    int numberOfPlayers() {
        return players.size();
    }

    void nextPlayer() {
        getcPlayer().changePlayer();
    }

    boolean didCurrentPlayerWin() {
        return purses.hasNotYetWon(getcPlayer());
    }

    int currentPlayerIndex() {
        return getcPlayer().current();
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
        purses.gainOneCoin(getcPlayer());
        System.out.println(currentPlayerName() + " now has " + purses.coinsFor(getcPlayer()) + " Gold Coins.");
    }

    int addPlayer(String playerName) {
        players.add(playerName);
        getcPlayer().addPlayer(playerName);
        int lastPlayer1 = numberOfPlayers();
        getcPlayer().setMaxPlayers(lastPlayer1);
        int lastPlayer = lastPlayer1;
        purses.initialisePlayersPurse(lastPlayer);
        return lastPlayer;
    }
}