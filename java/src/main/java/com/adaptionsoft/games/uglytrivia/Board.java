package com.adaptionsoft.games.uglytrivia;

public class Board {
    private int[] places;
    private Player cPlayer;

    public Board(int[] places, Player cPlayer) {
        this.places = places;
        this.cPlayer = cPlayer;
    }

    public void setPlaces(int[] places) {
        this.places = places;
    }

    int squareOfCurrentPlayer() {
        return places[cPlayer.current()];
    }

    public void moveCurrentPlayerForward(int diceResult) {
        places[cPlayer.current()] = squareOfCurrentPlayer() + diceResult;
        if (squareOfCurrentPlayer() > 11) 
            places[cPlayer.current()] = squareOfCurrentPlayer() - 12;
    }

    void putCurrentPlayerOnStartSquare(int lastPlayer) {
        places[lastPlayer] = 0;
    }

    public String currentCategory() {
    	if (squareOfCurrentPlayer() == 0) return "Pop";
    	if (squareOfCurrentPlayer() == 4) return "Pop";
    	if (squareOfCurrentPlayer() == 8) return "Pop";
    	if (squareOfCurrentPlayer() == 1) return "Science";
    	if (squareOfCurrentPlayer() == 5) return "Science";
    	if (squareOfCurrentPlayer() == 9) return "Science";
    	if (squareOfCurrentPlayer() == 2) return "Sports";
    	if (squareOfCurrentPlayer() == 6) return "Sports";
    	if (squareOfCurrentPlayer() == 10) return "Sports";
    	return "Rock";
    }
}