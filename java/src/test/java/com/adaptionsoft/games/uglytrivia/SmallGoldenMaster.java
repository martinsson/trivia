package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.Random;

import org.junit.Test;

import com.adaptionsoft.games.trivia.runner.GameRunner;

public class SmallGoldenMaster {

    @Test
    public void test() throws Exception {
        Random randomizer = new Random(287389);
        
        for (int i = 0; i < 200; i++) {
            System.setOut(new PrintStream("src/test/resources/" + "short/" + "Reference."+i));
            GameRunner.playGame(randomizer);
        }
        
    }

}
