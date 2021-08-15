package com.korbiak.mentorship.databases.task11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TranIsolationPhenTest {

    private static TranIsolationPhen t;

    @BeforeAll
    static void init() {
        t = new TranIsolationPhen(System.getenv("user"),
                System.getenv("password"));
    }

    @Test
    void executeTest() {
        t.execute();
    }
}
