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
    
    public Player cPlayer = new Player(0);
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
	    cPlayer.setMaxPlayers(players.size());
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
		if (places[cPlayer.current()] == 0) return "Pop";
		if (places[cPlayer.current()] == 4) return "Pop";
		if (places[cPlayer.current()] == 8) return "Pop";
		if (places[cPlayer.current()] == 1) return "Science";
		if (places[cPlayer.current()] == 5) return "Science";
		if (places[cPlayer.current()] == 9) return "Science";
		if (places[cPlayer.current()] == 2) return "Sports";
		if (places[cPlayer.current()] == 6) return "Sports";
		if (places[cPlayer.current()] == 10) return "Sports";
		return "Rock";
	}

    public void playGame(Random rand) {
        boolean notAWinner;
    	do {
    		int diceResult = rand.nextInt(5) + 1;
    		
            System.out.println(players.get(cPlayer.current()) + " is the current player");
            System.out.println("They have rolled a " + diceResult);
            
            if (inPenaltyBox[cPlayer.current()]) {
            	if (diceResult % 2 != 0) {
            		isGettingOutOfPenaltyBox = true;
            		
            		System.out.println(players.get(cPlayer.current()) + " is getting out of the penalty box");
            		
            		places[cPlayer.current()] = places[cPlayer.current()] + diceResult;
            		if (places[cPlayer.current()] > 11)
            		    places[cPlayer.current()] = places[cPlayer.current()] - 12;
            		
            		System.out.println(players.get(cPlayer.current()) 
            				+ "'s new location is " 
            				+ places[cPlayer.current()]);
            		System.out.println("The category is " + currentCategory());
            		askQuestion();
            	} else {
            		System.out.println(players.get(cPlayer.current()) + " is not getting out of the penalty box");
            		isGettingOutOfPenaltyBox = false;
            		}
            	
            } else {
            
            	places[cPlayer.current()] = places[cPlayer.current()] + diceResult;
            	if (places[cPlayer.current()] > 11) places[cPlayer.current()] = places[cPlayer.current()] - 12;
            	
            	System.out.println(players.get(cPlayer.current()) 
            			+ "'s new location is " 
            			+ places[cPlayer.current()]);
            	System.out.println("The category is " + currentCategory());
            	askQuestion();
            }
    		
    		if (rand.nextInt(9) == 7) {
    			System.out.println("Question was incorrectly answered");
                System.out.println(players.get(cPlayer.current())+ " was sent to the penalty box");
                inPenaltyBox[cPlayer.current()] = true;
                
                cPlayer.changePlayer();
                notAWinner = true;
    		} else {
    			boolean ret;
                if (inPenaltyBox[cPlayer.current()]){
                	if (isGettingOutOfPenaltyBox) {
                		System.out.println("Answer was correct!!!!");
                		purses[cPlayer.current()]++;
                		System.out.println(players.get(cPlayer.current())
                				+ " now has "
                				+ purses[cPlayer.current()]
                				+ " Gold Coins.");
                		
                		boolean winner = !(purses[cPlayer.current()] == 6);
                		
                		cPlayer.changePlayer();
                		
                		ret = winner;
                	} else {
                		cPlayer.changePlayer();
                		ret = true;
                	}
                	
                	
                	
                } else {
                
                	System.out.println("Answer was corrent!!!!");
                	purses[cPlayer.current()]++;
                	System.out.println(players.get(cPlayer.current()) 
                			+ " now has "
                			+ purses[cPlayer.current()]
                			+ " Gold Coins.");
                	
                	boolean winner = !(purses[cPlayer.current()] == 6);
                	cPlayer.changePlayer();
                	
                	ret = winner;
                }
                notAWinner = ret;
    		}
    		
    		
    		
    	} while (notAWinner);
    }
}
