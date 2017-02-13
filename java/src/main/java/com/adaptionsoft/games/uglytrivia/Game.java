package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    List<Player> playerz = new ArrayList();

    int[] places = new int[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {

        playerz.add(new Player(playerName));

        places[howManyPlayers()] = 0;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerz.size());
        return true;
    }

    public int howManyPlayers() {
        return playerz.size();
    }

    public void roll(int roll) {
        Player player = playerz.get(currentPlayer);
        System.out.println(player + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!player.isInPenaltyBox()) {

            normalRoll(roll);
        } else if (roll % 2 != 0) {

            player.freeFromPenaltyBox();
            System.out.println(player + " is getting out of the penalty box");
            normalRoll(roll);
        } else {
            System.out.println(player + " is not getting out of the penalty box");
        }

    }

    private void normalRoll(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

        System.out.println(playerz.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
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

    public boolean wasCorrectlyAnswered() {
        Player player = playerz.get(currentPlayer);

        if (!player.isInPenaltyBox()) {

            return normalAnswer();
        } else {

            currentPlayer++;
            if (currentPlayer == playerz.size()) currentPlayer = 0;
            return true;
        }
    }

    private boolean normalAnswer() {
        System.out.println("Answer was correct!!!!");
        Player player = playerz.get(currentPlayer);
        player.earnGoldCoin();
        System.out.println(player
                + " now has "
                + player.getCoins()
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == playerz.size()) currentPlayer = 0;

        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        Player player = playerz.get(currentPlayer);
        System.out.println(player + " was sent to the penalty box");

        player.sendToPenaltyBox();

        currentPlayer++;
        if (currentPlayer == playerz.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        Player player = playerz.get(currentPlayer);
        return player.getCoins() != 6;
    }
}
