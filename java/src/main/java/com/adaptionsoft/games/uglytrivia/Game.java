package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    public ArrayList players = new ArrayList();
    public Purses purses = new Purses(new int[6]);
    public boolean[] inPenaltyBox = new boolean[6];

    private Questions questions = new Questions(new HashMap<String, LinkedList>());
    private Player cPlayer = new Player(0);
    public Board board = new Board(new int[6], cPlayer);

    public Game() {
        questions.initQuestions();
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

    public void playGame(Random rand) {
        boolean notAWinner = true;
        do {
            int diceResult = rand.nextInt(5) + 1;

            System.out.println(players.get(cPlayer.current()) + " is the current player");
            System.out.println("They have rolled a " + diceResult);
            
            boolean canLeavePenaltyBox = move(diceResult);

            if (rand.nextInt(9) == 7) {
                answerIncorrectly();
            } else {
                notAWinner = answerCorrectly(canLeavePenaltyBox);
            }
            cPlayer.changePlayer();
        } while (notAWinner);
    }

    private boolean answerCorrectly(boolean canLeavePenaltyBox) {
        if (inPenaltyBox[cPlayer.current()] && !canLeavePenaltyBox) return true;
        
        System.out.println("Answer was correct!!!!");
        purses.gainOneCoin(cPlayer);
        System.out.println(players.get(cPlayer.current()) + " now has " + purses.coinsFor(cPlayer) + " Gold Coins.");

        return purses.hasNotYetWon(cPlayer);
    }

    private void answerIncorrectly() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(cPlayer.current()) + " was sent to the penalty box");
        inPenaltyBox[cPlayer.current()] = true;
    }

    private boolean move(int diceResult) {
        if (inPenaltyBox[cPlayer.current()]) {
            if (diceResult % 2 != 0) {
                System.out.println(players.get(cPlayer.current()) + " is getting out of the penalty box");
            } else {
                System.out.println(players.get(cPlayer.current()) + " is not getting out of the penalty box");
            }
        }

        boolean canLeavePenaltyBox;
        if (inPenaltyBox[cPlayer.current()] && diceResult % 2 == 0) {
            canLeavePenaltyBox = false;
        } else {
            board.moveCurrentPlayerForward(diceResult);

            System.out.println(players.get(cPlayer.current()) + "'s new location is " + board.squareOfCurrentPlayer());
            System.out.println("The category is " + board.currentCategory());
            questions.askAbout(board.currentCategory());
            canLeavePenaltyBox = true;
        }
        return canLeavePenaltyBox;
    }
}
