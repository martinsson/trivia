package com.adaptionsoft.games.uglytrivia;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.*;

public class Game {


    public static class Player {
        int purse = 0;
        boolean inPenaltyBox;
        int place = 0;
        private String name;

        public Player(String name) {
            this.name = name;
        }
    }

    private Player cPlayer;
    List<Player> realPlayers = new ArrayList();
    int currentPlayer = 0;

    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    private Map<String, LinkedList> questionsDeck = new HashMap();
    private List<String> categories = asList("Pop","Science","Sports","Rock");


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

        realPlayers.add(new Player(playerName));
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + realPlayers.size());
        cPlayer = realPlayers.get(0);
        return true;
    }

    public int howManyPlayers() {
        return realPlayers.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!inPenaltyBox[currentPlayer]) {

            movePlayerOnBoard(roll);
            askQuestion();
        } else if (roll % 2 != 0) {
            inPenaltyBox[currentPlayer] = false;

            System.out.println(currentPlayerName() + " is getting out of the penalty box");
            movePlayerOnBoard(roll);
            askQuestion();
        } else {
            System.out.println(currentPlayerName() + " is not getting out of the penalty box");
        }

    }

    private void movePlayerOnBoard(int roll) {
        cPlayer.place = (cPlayer.place + roll) % 12;

        System.out.println(currentPlayerName() + "'s new location is " + getPlace());
        System.out.println("The category is " + currentCategory());
    }

    private int getPlace() {
        return cPlayer.place;
    }

    private void askQuestion() {
        Object question = questionsDeck.get(currentCategory()).removeFirst();
        System.out.println(question);
    }


    private String currentCategory() {
        int categoryPosition = getPlace() % categories.size();
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
        System.out.println(currentPlayerName() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        changePlayer();
        return true;
    }

    private boolean winCoin() {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(currentPlayerName() + " now has " + purses[currentPlayer] + " Gold Coins.");

        boolean winner = didPlayerWin();
        changePlayer();

        return winner;
    }

    private Object currentPlayerName() {
        return cPlayer.name;
    }

    private void changePlayer() {
        currentPlayer = (currentPlayer + 1) % realPlayers.size();
        cPlayer = realPlayers.get(currentPlayer);
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
