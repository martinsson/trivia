package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    public boolean[] inPenaltyBox = new boolean[6];

    private Questions questions = new Questions(new HashMap<String, LinkedList>());
    public Players players = new Players(new ArrayList(), new Purses(new int[6]), new Player(0));
    public Board board = new Board(new int[6], players.getcPlayer());

    public Game() {
        questions.initQuestions();
    }

    public boolean isPlayable() {
        return (players.getPlayers().size() >= 2);
    }

    public boolean add(String playerName) {

        players.getPlayers().add(playerName);
        players.getcPlayer().addPlayer(playerName);
        int lastPlayer = players.getPlayers().size();
        players.getcPlayer().setMaxPlayers(lastPlayer);
        
        board.putCurrentPlayerOnStartSquare(lastPlayer);
        players.getPurses().initialisePlayersPurse(lastPlayer);
        inPenaltyBox[lastPlayer] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + lastPlayer);
        return true;
    }

    public void playGame(Random rand) {
        boolean notAWinner = true;
        do {
            int diceResult = rand.nextInt(5) + 1;

            System.out.println(players.getPlayers().get(players.getcPlayer().current()) + " is the current player");
            System.out.println("They have rolled a " + diceResult);
            if (inPenaltyBox[players.getcPlayer().current()]) {
                if (diceResult % 2 != 0) {
                    System.out.println(players.getPlayers().get(players.getcPlayer().current()) + " is getting out of the penalty box");
                } else {
                    System.out.println(players.getPlayers().get(players.getcPlayer().current()) + " is not getting out of the penalty box");
                }
            }
            
            boolean isStayingInPenaltyBox = inPenaltyBox[players.getcPlayer().current()] && diceResult % 2 == 0;
            if (!isStayingInPenaltyBox) {
                board.moveCurrentPlayerForward(diceResult);
            
                System.out.println(players.getPlayers().get(players.getcPlayer().current()) + "'s new location is " + board.squareOfCurrentPlayer());
                System.out.println("The category is " + board.currentCategory());
                questions.askAbout(board.currentCategory());
            }
            
            if (rand.nextInt(9) == 7) {
                System.out.println("Question was incorrectly answered");
                System.out.println(players.getPlayers().get(players.getcPlayer().current()) + " was sent to the penalty box");
                inPenaltyBox[players.getcPlayer().current()] = true;
            } else {
                if (!isStayingInPenaltyBox) {
                    
                
                    System.out.println("Answer was correct!!!!");
                    players.getPurses().gainOneCoin(players.getcPlayer());
                    System.out.println(players.getPlayers().get(players.getcPlayer().current()) + " now has " + players.getPurses().coinsFor(players.getcPlayer()) + " Gold Coins.");
                }
                notAWinner = players.getPurses().hasNotYetWon(players.getcPlayer());
            }
            players.getcPlayer().changePlayer();
        } while (notAWinner);
    }
}
