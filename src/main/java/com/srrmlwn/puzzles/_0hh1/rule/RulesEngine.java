package com.srrmlwn.puzzles._0hh1.rule;

import com.srrmlwn.puzzles._0hh1.Cell;
import com.srrmlwn.puzzles._0hh1.Grid;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by sriram on 3/5/15.
 */
public class RulesEngine {
    private static final Rule[] RULES = new Rule[]{new Rule.EqualColorsInRow(), new Rule.ThreeColorsInSequence(), new Rule.UnidenticalRows(), new Rule.UnidenticalColumns()};

    private final int[] solvedCells;
    private final int initialUnsolvedCount;

    public RulesEngine(int initialUnsolvedCount) {
        this.solvedCells = new int[RULES.length];
        Arrays.fill(this.solvedCells, 0);
        this.initialUnsolvedCount = initialUnsolvedCount;
    }

    public Cell process(Grid grid, int row, int column) {
        for (int ruleIndex = 0; ruleIndex < RULES.length; ruleIndex++) {
            Rule rule = RULES[ruleIndex];
            int unsolvedBefore = grid.getUnsolvedCells();
            Cell cellFromRule = rule.process(grid, row, column);
            if (!cellFromRule.isUndefined()) {
                int unsolvedAfter = grid.getUnsolvedCells();
                this.solvedCells[ruleIndex] += unsolvedBefore - unsolvedAfter;

                return cellFromRule;
            }
        }
        return Cell.UNDEFINED;
    }

    public String getStatsSummary() {
        StringBuilder builder = new StringBuilder();
        builder.append("Number of empty cells in input: ").append(this.initialUnsolvedCount).append("\n");
        for (int ruleIndex = 0; ruleIndex < RULES.length; ruleIndex++) {
            builder.append("+ ")
                    .append(RULES[ruleIndex].description())
                    .append(": ")
                    .append(this.solvedCells[ruleIndex])
                    .append("/")
                    .append(this.initialUnsolvedCount)
                    .append(" (")
                    .append(new DecimalFormat("##.00").format((double) this.solvedCells[ruleIndex] * 100 / this.initialUnsolvedCount) )
                    .append("%) cells solved.")
                    .append("\n");
        }

        return builder.toString();
    }
}
