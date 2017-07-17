package com.adaptionsoft.games.uglytrivia;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.*;

public class Game {

    //    public static class Player {
//        int purse = 0;
//        boolean inPenaltyBox;
//        int place = 0;
//    }
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    private Map<String, LinkedList> questionsDeck = new HashMap();
    private List<String> categories = asList("Pop","Science","Sports","Rock");

    int currentPlayer = 0;

    public Game() {
        categories.forEach(category -> {
            LinkedList questions = makeQuestions(category);
            questionsDeck.put(category, questions);
        });
    }

    private LinkedList makeQuestions(String category) {
        LinkedList questions = new LinkedList();
        for (int i = 0; i < 50; i++) {
            questions.addLast(String.format("%s Question %d", category, i));
        }
        return questions;
    }

    public boolean add(String playerName) {


        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!inPenaltyBox[currentPlayer]) {

            movePlayerOnBoard(roll);
            askQuestion();
        } else if (roll % 2 != 0) {
            inPenaltyBox[currentPlayer] = false;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            movePlayerOnBoard(roll);
            askQuestion();
        } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        }

    }

    private void movePlayerOnBoard(int roll) {
        places[currentPlayer] = (places[currentPlayer] + roll) % 12;

        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
    }

    private void askQuestion() {
        Object question = questionsDeck.get(currentCategory()).removeFirst();
        System.out.println(question);
    }


    private String currentCategory() {
        int categoryPosition = places[currentPlayer] % categories.size();
        return categories.get(categoryPosition);
    }

    public boolean wasCorrectlyAnswered() {
        if (!inPenaltyBox[currentPlayer]) {
            return winCoin();
        } else {
            changePlayer();
            return true;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        changePlayer();
        return true;
    }

    private boolean winCoin() {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

        boolean winner = didPlayerWin();
        changePlayer();

        return winner;
    }

    private void changePlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
