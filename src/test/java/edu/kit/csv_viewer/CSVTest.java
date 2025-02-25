package edu.kit.csv_viewer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CSVTest {

    static final String testCSV = """
            123,2.99,AMO024,Title,"test","Description, \\",more info",,123987564,"\\"","\\\\asd"
            123,2.99,AMO024,Title,"test","Description, \\",more info",,123987564,"\\"","\\\\asd"
            123,2.99,AMO024,Title,"test","Description, \\",more info",,123987564,"\\"","\\\\asd"
            """;

    @Test
    public void hardCodedCases() {
        final var csv = CSV.parse(testCSV);
        System.out.println(csv);
        Assertions.assertEquals(3, csv.size());
        Assertions.assertEquals(10, csv.getFirst().size());
        Assertions.assertEquals("123", (csv.getFirst().getFirst()).toString());
    }
}
