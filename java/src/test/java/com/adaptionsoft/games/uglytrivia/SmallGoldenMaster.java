package com.adaptionsoft.games.uglytrivia;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.File;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.adaptionsoft.games.trivia.runner.GameRunner;

public class SmallGoldenMaster {
    
    @Rule public TemporaryFolder tmp = new TemporaryFolder();
    private static final String REFERENCE_FILE_BASE = "src/test/resources/" + "long/" + "Reference.";

    @Test
    public void test() throws Exception {
        Random randomizer = new Random(287389);
        
        for (int i = 0; i < 200; i++) {
            File file = tmp.newFile();
            System.setOut(new PrintStream(file));
            GameRunner.playGame(randomizer);
            assertThat(file).hasContentEqualTo(new File(REFERENCE_FILE_BASE + i));
        }
        
    }

}
