package com.adaptionsoft.games.uglytrivia;

import org.junit.Before;

public class LongGoldenMaster extends GoldenMaster {
    @Before
    public void initBeforeTest() throws Exception {
        numberOfFiles = 2000;
    }

}
