package com.adaptionsoft.games.uglytrivia;

public class Purses {
    private int[] purses;

    public Purses(int[] purses) {
        this.purses = purses;
    }

    void gainOneCoin(Player player) {
        purses[player.current()]++;
    }

    int coinsFor(Player player) {
        return purses[player.current()];
    }

    boolean hasNotYetWon(Player player) {
        return coinsFor(player) != 6;
    }

    void initialisePlayersPurse(int lastPlayer) {
        purses[lastPlayer] = 0;
    }
}