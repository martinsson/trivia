package com.adaptionsoft.games.uglytrivia;

import java.util.*;

import static java.util.Arrays.*;

public class Game {


    /*
    seems clean as a result,
     */

    private Player cPlayer;
    List<Player> realPlayers = new ArrayList();
    int currentPlayer = 0;

    private Map<String, LinkedList> questionsDeck;
    private List<String> categories = asList("Pop","Science","Sports","Rock");


    public Game() {
        questionsDeck = new HashMap();
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

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + realPlayers.size());

        // TODO this is wierd
        cPlayer = realPlayers.get(0);

        return true;
    }

    public int howManyPlayers() {
        return realPlayers.size();
    }

    public void roll(int roll) {
        System.out.println(cPlayer.name + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (cPlayer.isOutOfPenaltyBox()) {
            movePlayerOnBoard(roll);
            askQuestion();
        } else if (roll % 2 != 0) {
            cPlayer.allowOutOfPenaltyBox();
            System.out.println(cPlayer.name + " is getting out of the penalty box");
            movePlayerOnBoard(roll);
            askQuestion();
        } else {
            System.out.println(cPlayer.name + " is not getting out of the penalty box");
        }

    }

    private void askQuestion() {
        String category = categoryAt(cPlayer.place);
        Object question = questionsDeck.get(category).removeFirst();
        System.out.println(question);
    }

    private String categoryAt(int place) {
        int categoryPosition = place % categories.size();
        return categories.get(categoryPosition);
    }

    public boolean wasCorrectlyAnswered() {
        if (cPlayer.isOutOfPenaltyBox()) {
            return winCoin();
        } else {
            changePlayer();
            return true;
        }
    }

    public boolean wrongAnswer() {
        cPlayer.sendToPenaltyBox();
        System.out.println(cPlayer.name + " was sent to the penalty box");

        changePlayer();
        return true;
    }

    private void movePlayerOnBoard(int roll) {
        cPlayer.place = (cPlayer.place + roll) % 12;

        int newPlace = cPlayer.place;
        System.out.println(cPlayer.name + "'s new location is " + newPlace);
        System.out.println("The category is " + categoryAt(newPlace));
    }

    private boolean winCoin() {
        System.out.println("Answer was correct!!!!");
        boolean winner = cPlayer.winCoin();
        changePlayer();

        return winner;
    }

    private void changePlayer() {
        currentPlayer = (currentPlayer + 1) % realPlayers.size();
        cPlayer = realPlayers.get(currentPlayer);
    }


}
