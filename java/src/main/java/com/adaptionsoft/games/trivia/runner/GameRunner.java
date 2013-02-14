
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
		
	
		boolean notAWinner;
		do {
			int diceResult = rand.nextInt(5) + 1;
			
            System.out.println(aGame.players.get(aGame.currentPlayer) + " is the current player");
            System.out.println("They have rolled a " + diceResult);
            
            if (aGame.inPenaltyBox[aGame.currentPlayer]) {
            	if (diceResult % 2 != 0) {
            		aGame.isGettingOutOfPenaltyBox = true;
            		
            		System.out.println(aGame.players.get(aGame.currentPlayer) + " is getting out of the penalty box");
            		aGame.places[aGame.currentPlayer] = aGame.places[aGame.currentPlayer] + diceResult;
            		if (aGame.places[aGame.currentPlayer] > 11) aGame.places[aGame.currentPlayer] = aGame.places[aGame.currentPlayer] - 12;
            		
            		System.out.println(aGame.players.get(aGame.currentPlayer) 
            				+ "'s new location is " 
            				+ aGame.places[aGame.currentPlayer]);
            		System.out.println("The category is " + aGame.currentCategory());
            		aGame.askQuestion();
            	} else {
            		System.out.println(aGame.players.get(aGame.currentPlayer) + " is not getting out of the penalty box");
            		aGame.isGettingOutOfPenaltyBox = false;
            		}
            	
            } else {
            
            	aGame.places[aGame.currentPlayer] = aGame.places[aGame.currentPlayer] + diceResult;
            	if (aGame.places[aGame.currentPlayer] > 11) aGame.places[aGame.currentPlayer] = aGame.places[aGame.currentPlayer] - 12;
            	
            	System.out.println(aGame.players.get(aGame.currentPlayer) 
            			+ "'s new location is " 
            			+ aGame.places[aGame.currentPlayer]);
            	System.out.println("The category is " + aGame.currentCategory());
            	aGame.askQuestion();
            }
			
			if (rand.nextInt(9) == 7) {
				System.out.println("Question was incorrectly answered");
                System.out.println(aGame.players.get(aGame.currentPlayer)+ " was sent to the penalty box");
                aGame.inPenaltyBox[aGame.currentPlayer] = true;
                
                aGame.currentPlayer++;
                if (aGame.currentPlayer == aGame.players.size()) aGame.currentPlayer = 0;
                notAWinner = true;
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			
			
			
		} while (notAWinner);
    }
}
