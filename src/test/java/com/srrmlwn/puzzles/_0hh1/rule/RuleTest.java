package com.srrmlwn.puzzles._0hh1.rule;

import com.srrmlwn.puzzles._0hh1.Cell;
import com.srrmlwn.puzzles._0hh1.Grid;
import com.srrmlwn.puzzles._0hh1.util.GridUtils;
import org.junit.Assert;
import org.junit.Test;

public class RuleTest {

    @Test
    public void equalColorsInRow() {
        Grid grid = grid("-bb-"
                + "----"
                + "----"
                + "--b-");
        final Rule.EqualColorsInRow equalColorsInRow = new Rule.EqualColorsInRow();
        Assert.assertEquals(Cell.RED, equalColorsInRow.process(grid, 0, 0));
        Assert.assertEquals(Cell.RED, equalColorsInRow.process(grid, 0, 3));
        Assert.assertEquals(Cell.RED, equalColorsInRow.process(grid, 1, 2));
        Assert.assertEquals(Cell.RED, equalColorsInRow.process(grid, 2, 2));
    }

    @Test
    public void threeColorsInSequence() {
        Grid grid = grid(
                        "-----b" +
                        "---rr-" +
                        "-b--r-" +
                        "---r--" +
                        "-b-r--" +
                        "------"
        );

        final Rule.ThreeColorsInSequence threeColorsInSequence = new Rule.ThreeColorsInSequence();
        Assert.assertEquals(Cell.BLUE, threeColorsInSequence.process(grid, 1, 2));
        Assert.assertEquals(Cell.BLUE, threeColorsInSequence.process(grid, 0, 4));
        Assert.assertEquals(Cell.BLUE, threeColorsInSequence.process(grid, 1, 5));
        Assert.assertEquals(Cell.BLUE, threeColorsInSequence.process(grid, 5, 3));
        Assert.assertEquals(Cell.RED, threeColorsInSequence.process(grid, 3, 1));
    }

    @Test
    public void unidenticalRows() {
        Grid grid = grid(
                          "-rb-"
                        + "----"
                        + "rrbb"
                        + "rbrb"
        );

        final Rule.UnidenticalRows unidenticalRows = new Rule.UnidenticalRows();
        Assert.assertEquals(Cell.BLUE, unidenticalRows.process(grid, 0, 0));
        Assert.assertEquals(Cell.RED, unidenticalRows.process(grid, 0, 3));
    }

    @Test
    public void unidenticalColumns() {
        Grid grid = grid(
                 "rrb-"
                +"bbr-"
                +"-rb-"
                +"-br-"
        );

        final Rule.UnidenticalColumns unidenticalColumns = new Rule.UnidenticalColumns();
        Assert.assertEquals(Cell.BLUE, unidenticalColumns.process(grid, 2, 0));
        Assert.assertEquals(Cell.RED, unidenticalColumns.process(grid, 3, 0));
    }


    private static Grid grid(String gridRepresentation) {
        return GridUtils.toGrid(gridRepresentation);
    }

}