package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class Game {
    public ArrayList players = new ArrayList();
    public int[] places = new int[6];
    public int[] purses  = new int[6];
    public boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    public int currentPlayer = 0;
    public boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
    	}
    }

	public boolean isPlayable() {
		return (players.size() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[players.size()] = 0;
	    purses[players.size()] = 0;
	    inPenaltyBox[players.size()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	public String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

    public void playGame(Random rand) {
        boolean notAWinner;
    	do {
    		int diceResult = rand.nextInt(5) + 1;
    		
            System.out.println(players.get(currentPlayer) + " is the current player");
            System.out.println("They have rolled a " + diceResult);
            
            if (inPenaltyBox[currentPlayer]) {
            	if (diceResult % 2 != 0) {
            		isGettingOutOfPenaltyBox = true;
            		
            		System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            		
            		places[currentPlayer] = places[currentPlayer] + diceResult;
            		if (places[currentPlayer] > 11)
            		    places[currentPlayer] = places[currentPlayer] - 12;
            		
            		System.out.println(players.get(currentPlayer) 
            				+ "'s new location is " 
            				+ places[currentPlayer]);
            		System.out.println("The category is " + currentCategory());
            		askQuestion();
            	} else {
            		System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            		isGettingOutOfPenaltyBox = false;
            		}
            	
            } else {
            
            	places[currentPlayer] = places[currentPlayer] + diceResult;
            	if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
            	
            	System.out.println(players.get(currentPlayer) 
            			+ "'s new location is " 
            			+ places[currentPlayer]);
            	System.out.println("The category is " + currentCategory());
            	askQuestion();
            }
    		
    		if (rand.nextInt(9) == 7) {
    			System.out.println("Question was incorrectly answered");
                System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
                inPenaltyBox[currentPlayer] = true;
                
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                notAWinner = true;
    		} else {
    			boolean ret;
                if (inPenaltyBox[currentPlayer]){
                	if (isGettingOutOfPenaltyBox) {
                		System.out.println("Answer was correct!!!!");
                		purses[currentPlayer]++;
                		System.out.println(players.get(currentPlayer)
                				+ " now has "
                				+ purses[currentPlayer]
                				+ " Gold Coins.");
                		
                		boolean winner = !(purses[currentPlayer] == 6);
                		
                		currentPlayer++;
                		if (currentPlayer == players.size()) 
                		    currentPlayer = 0;
                		
                		ret = winner;
                	} else {
                		currentPlayer++;
                		if (currentPlayer == players.size()) currentPlayer = 0;
                		ret = true;
                	}
                	
                	
                	
                } else {
                
                	System.out.println("Answer was corrent!!!!");
                	purses[currentPlayer]++;
                	System.out.println(players.get(currentPlayer) 
                			+ " now has "
                			+ purses[currentPlayer]
                			+ " Gold Coins.");
                	
                	boolean winner = !(purses[currentPlayer] == 6);
                	currentPlayer++;
                	if (currentPlayer == players.size()) currentPlayer = 0;
                	
                	ret = winner;
                }
                notAWinner = ret;
    		}
    		
    		
    		
    	} while (notAWinner);
    }
}
