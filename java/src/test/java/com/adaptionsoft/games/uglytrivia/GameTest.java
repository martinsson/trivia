package com.adaptionsoft.games.uglytrivia;

import static java.util.Arrays.asList;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;


public class GameTest {

    private Game game;

     
    @Before
    public void initBeforeTest() throws Exception {
        game = new Game();
        game.add("Fred");
    }

    @Test public void 
    wasCorrectlyAnswered() throws Exception {
        assertEquals(0, game.currentPlayer);

        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0}, game.purses);
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0}, game.places);
        assertEquals(asList("Fred"), game.players);
        assertThat(game.inPenaltyBox).isEqualTo(new boolean[]{false, false, false, false, false, false});
        assertEquals(false, game.isGettingOutOfPenaltyBox);
        game.roll(1);
        assertTrue(game.wasCorrectlyAnswered());
        assertEquals(0, game.currentPlayer);
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0}, game.purses);
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0}, game.places);
        assertEquals(asList("Fred"), game.players);
        assertThat(game.inPenaltyBox).isEqualTo(new boolean[]{false, false, false, false, false, false});
        assertEquals(false, game.isGettingOutOfPenaltyBox);
    }
    
    @Test public void 
    wrongAnswer() throws Exception {
        game.roll(-1);
        assertTrue(game.wasCorrectlyAnswered());
    }

}
