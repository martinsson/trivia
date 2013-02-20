package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class Game {
    public ArrayList players = new ArrayList();
    public Purses purses = new Purses(new int[6]);
    public boolean[] inPenaltyBox  = new boolean[6];
    
    private Map<String, LinkedList> questions = new HashMap<String, LinkedList>();
    
    private Player cPlayer = new Player(0);
    public Board board = new Board(new int[6], cPlayer);
    public boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	initQuestions();
    }

    private void initQuestions() {
        LinkedList popQuestions = new LinkedList();
        LinkedList scienceQuestions = new LinkedList();
        LinkedList sportsQuestions = new LinkedList();
        LinkedList rockQuestions = new LinkedList();
        for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
    	}
    	questions.put("Pop", popQuestions);
    	questions.put("Science", scienceQuestions);
    	questions.put("Sports", sportsQuestions);
    	questions.put("Rock", rockQuestions);
    }

	public boolean isPlayable() {
		return (players.size() >= 2);
	}

	public boolean add(String playerName) {
		
	    players.add(playerName);
	    int lastPlayer = players.size();
        cPlayer.setMaxPlayers(lastPlayer);
	    board.putCurrentPlayerOnStartSquare(lastPlayer);
	    purses.initialisePlayersPurse(lastPlayer);
	    inPenaltyBox[lastPlayer] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + lastPlayer);
		return true;
	}

    public void askQuestion() {
        String currentCategory = board.currentCategory();
		ask(currentCategory);		
	}

    private void ask(String currentCategory) {
        LinkedList q = questions.get(currentCategory);
		Object popNextQuestions = q.removeFirst();
        System.out.println(popNextQuestions);
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
            		
            		board.moveCurrentPlayerForward(diceResult);
            		
            		System.out.println(players.get(cPlayer.current()) 
            				+ "'s new location is " 
            				+ board.squareOfCurrentPlayer());
            		System.out.println("The category is " + board.currentCategory());
            		askQuestion();
            	} else {
            		System.out.println(players.get(cPlayer.current()) + " is not getting out of the penalty box");
            		isGettingOutOfPenaltyBox = false;
            		}
            	
            } else {
            
            	board.moveCurrentPlayerForward(diceResult);
            	
            	System.out.println(players.get(cPlayer.current()) 
            			+ "'s new location is " 
            			+ board.squareOfCurrentPlayer());
            	System.out.println("The category is " + board.currentCategory());
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
                		purses.gainOneCoin(cPlayer);
                		System.out.println(players.get(cPlayer.current())
                				+ " now has "
                				+ purses.coinsFor(cPlayer)
                				+ " Gold Coins.");
                		
                		boolean winner = purses.hasNotYetWon(cPlayer);
                		
                		cPlayer.changePlayer();
                		
                		ret = winner;
                	} else {
                		cPlayer.changePlayer();
                		ret = true;
                	}
                	
                	
                	
                } else {
                
                	System.out.println("Answer was corrent!!!!");
                	purses.gainOneCoin(cPlayer);
                	System.out.println(players.get(cPlayer.current()) 
                			+ " now has "
                			+ purses.coinsFor(cPlayer)
                			+ " Gold Coins.");
                	
                	boolean winner = purses.hasNotYetWon(cPlayer);
                	cPlayer.changePlayer();
                	
                	ret = winner;
                }
                notAWinner = ret;
    		}
    		
    		
    		
    	} while (notAWinner);
    }
}
