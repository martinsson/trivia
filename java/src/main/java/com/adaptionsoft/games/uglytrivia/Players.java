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

    public ArrayList getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList players) {
        this.players = players;
    }

    public Purses getPurses() {
        return purses;
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
}