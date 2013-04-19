package com.adaptionsoft.games.uglytrivia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.approvaltests.Approvals;
import org.junit.Test;

import com.adaptionsoft.games.trivia.runner.GameRunner;

public class GoldenCopyGameTest {

    private static final long FIXED_SEED = 9383;


    @Test
    public void a_simple_test_with_approvals() throws Exception {
        Game game = new Game();
        Approvals.verify(game.toString());
    }

    
    @Test public void 
    testname() throws Exception {
        
        Random random = new Random(FIXED_SEED);
        ArrayList<Game> games = new ArrayList<Game>();
        for (int i = 0; i < 200; i++) {
            games.add(GameRunner.runGame(random));
        }
        Approvals.verifyAll("game", games);
    }
}
