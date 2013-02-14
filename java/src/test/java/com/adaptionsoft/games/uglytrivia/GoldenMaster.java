package com.adaptionsoft.games.uglytrivia;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.adaptionsoft.games.trivia.runner.GameRunner;

public abstract class GoldenMaster {

    private static final String REFERENCE_LOCATION = "src/test/resources/" + "long/" + "Reference.";
    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();
    protected Random randomizer = new Random(287389);
    protected int numberOfFiles;
    
    public void testFile(int i) throws IOException, FileNotFoundException {
        File file = tmp.newFile();
        System.setOut(new PrintStream(file));
        GameRunner.playGame(randomizer);
        assertThat(file).hasContentEqualTo(new File(REFERENCE_LOCATION + i));
    }

    @Test
    public void test() throws Exception {
        for (int i = 0; i < getNumberOfFiles(); i++) {
            testFile(i);
        }
    }

    private int getNumberOfFiles() {
        return numberOfFiles;
    }

}