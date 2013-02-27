package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Player {
    private int currentPlayer;
    private int maxPlayers;
    public Player(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int current() {
        return currentPlayer;
    }

    public void changePlayer() {
        this.currentPlayer = currentPlayer + 1;
        if (currentPlayer == maxPlayers)
            this.currentPlayer = 0;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void addPlayer(String playerName) {
        // TODO Auto-generated method stub
        
    }

}