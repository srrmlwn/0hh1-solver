package com.srrmlwn.puzzles._0hh1;

import com.srrmlwn.puzzles._0hh1.util.GridUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SolverTest {

    @Test
    public void samplePuzzles() throws Exception {
        List<String> inputs = readFile("src/test/resources/input.txt");
        List<String> expectedOutputs = readFile("src/test/resources/expected_output.txt");
        Assert.assertEquals(inputs.size(), expectedOutputs.size());
        for (int i = 0; i < inputs.size(); i++) {
            assertInput(expectedOutputs.get(i), inputs.get(i));
            System.out.println("Puzzle " + (i + 1) + "/" + inputs.size() + " - SUCCESS");
        }
    }

    private void assertInput(String expectedOutput, String input) {
        Grid outputGrid = new Solver(GridUtils.toGrid(input)).solve();
        String outputGridWithNewLines = GridUtils.asString(outputGrid);
        String outputGridAsString = outputGridWithNewLines.replaceAll("\n","");
        Assert.assertEquals(expectedOutput, outputGridAsString);
    }

    private static List<String> readFile(String file) throws Exception {
        return Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/" + file));
    }
}