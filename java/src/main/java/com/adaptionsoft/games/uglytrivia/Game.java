package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    public boolean[] inPenaltyBox = new boolean[6];

    public Players players = new Players(new ArrayList(), new Purses(new int[6]), new Player(0));
    public Board board;

    public Game() {
        Questions questions;
        questions = new Questions(new HashMap<String, LinkedList>());
        questions.initQuestions();
        board = new Board(new int[6], players.getcPlayer(), questions);
    }

    public boolean isPlayable() {
        return (players.numberOfPlayers() >= 2);
    }

    public boolean add(String playerName) {

        int lastPlayer = players.addPlayer(playerName);
        
        board.putCurrentPlayerOnStartSquare(lastPlayer);
        inPenaltyBox[lastPlayer] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + lastPlayer);
        return true;
    }

    public void playGame(Random rand) {
        boolean notAWinner = true;
        do {
            int diceResult = rand.nextInt(5) + 1;

            System.out.println(players.currentPlayerName() + " is the current player");
            System.out.println("They have rolled a " + diceResult);
            if (inPenaltyBox[players.currentPlayerIndex()]) {
                if (diceResult % 2 != 0) {
                    System.out.println(players.currentPlayerName() + " is getting out of the penalty box");
                } else {
                    System.out.println(players.currentPlayerName() + " is not getting out of the penalty box");
                }
            }
            
            boolean isStayingInPenaltyBox = inPenaltyBox[players.currentPlayerIndex()] && diceResult % 2 == 0;
            if (!isStayingInPenaltyBox) {
                board.moveCurrentPlayerForward(diceResult);
            
                System.out.println(players.currentPlayerName() + "'s new location is " + board.squareOfCurrentPlayer());
                board.askAboutCurrentCategory();
            }
            
            if (rand.nextInt(9) == 7) {
                players.currentPlayerAnswersInCorrectly();
                inPenaltyBox[players.currentPlayerIndex()] = true;
            } else {
                if (!isStayingInPenaltyBox) {
                    
                
                    players.currentPlayerAnswersCorrectly();
                }
                notAWinner = players.didCurrentPlayerWin();
            }
            players.nextPlayer();
        } while (notAWinner);
    }
}
