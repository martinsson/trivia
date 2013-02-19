
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {


	public static void main(String[] args) {
	    Random rand = new Random();
		playGame(rand);
		
	}

    public static void playGame(Random rand) {
        Game aGame = new Game();
		
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");
		
	
		aGame.playGame(rand);
    }
}
