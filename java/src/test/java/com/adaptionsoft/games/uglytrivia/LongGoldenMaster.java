package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.Random;

import org.junit.Test;

import com.adaptionsoft.games.trivia.runner.GameRunner;

public class LongGoldenMaster {

    @Test
    public void test() throws Exception {
        Random randomizer = new Random(287389);
        
        for (int i = 0; i < 2000; i++) {
            System.setOut(new PrintStream("src/test/resources/" + "long/" + "Reference."+i));
            GameRunner.playGame(randomizer);
        }
        
    }

}
