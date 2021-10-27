package com.korbiak.mentorship.io.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ProcedureCallTest {
    private static ProcedureCall procedureCall;

    @BeforeAll
    public static void setUpRepo() {
        procedureCall = new ProcedureCall(System.getenv("user"),
                System.getenv("password"));
    }

    @Test
    void countSizeOfDB() {
        Map<String, String> result = procedureCall.callCountSizeOfDb();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.containsKey("totalSize"));
        Assertions.assertTrue(result.containsKey("numberOfTable"));
        Assertions.assertTrue(result.containsKey("allTables"));
        System.out.println(result);
    }

    @Test
    void callGetTableInfoTest() {
        Map<String, String> result = procedureCall.callGetTableInfo("tutor");
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.containsKey("numberOfColumns"));
        Assertions.assertTrue(result.containsKey("tableSize"));
        Assertions.assertTrue(result.containsKey("columnsName"));
        System.out.println(result);
    }
}
