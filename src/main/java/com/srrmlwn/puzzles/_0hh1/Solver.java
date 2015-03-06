package com.srrmlwn.puzzles._0hh1;

import com.srrmlwn.puzzles._0hh1.rule.RulesEngine;
import com.srrmlwn.puzzles._0hh1.util.GridUtils;

/**
 * Created by sriram on 2/28/15.
 */
public class Solver {

    private final Grid grid;
    private final RulesEngine rulesEngine;

    public Solver(Grid grid) {
        this.grid = grid;
        this.rulesEngine = new RulesEngine(grid.getUnsolvedCells());
    }

    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2)
        {
            System.err.println("Usage: java " + Solver.class.getName() + " [-v] <puzzle>");
            return;
        }

        boolean verbose = false;
        String puzzle = null;
        if (args.length == 1)
        {
            puzzle = args[0];
        }
        else
        {
            verbose = "-v".equals(args[0]);
            puzzle = args[1];
        }

        Grid inputGrid = GridUtils.toGrid(puzzle);
        final Solver solver = new Solver(inputGrid);
        Grid outputGrid = solver.solve();

        printGrid(GridUtils.toGrid(puzzle), outputGrid, solver.rulesEngine, verbose);

    }

    public Grid solve() {
        int previousUnsolved = 0;
        while (!this.grid.isSolved() && previousUnsolved != this.grid.getUnsolvedCells()) {
            previousUnsolved = this.grid.getUnsolvedCells();
            for (int i = 0; i < this.grid.size(); i++) {
                for (int j = 0; j < this.grid.size(); j++) {
                    recursivelySolve(i, j);
                }
            }
        }
        return this.grid;
    }

    private static void printGrid(Grid inputGrid, Grid outputGrid, RulesEngine rulesEngine, boolean verbose) {
        System.out.println("Input:\n" + GridUtils.asString(inputGrid));
        System.out.println("Output:\n" + GridUtils.asString(outputGrid));
        if (!outputGrid.isSolved()) {
            System.out.println("The grid cannot be solved without backtracking!");
        }
        if (verbose) {
            System.out.println();
            System.out.println("Constraints Summary:");
            System.out.println(rulesEngine.getStatsSummary());
        }
    }

    private void recursivelySolve(int row, int column) {
        if (!this.grid.cellAt(row, column).isUndefined()) {
            return;
        }

        Cell cell = this.rulesEngine.process(this.grid, row, column);
        if (cell.isUndefined()) {
            return;
        }

        this.solveNearbyCells(row, column);
    }

    private void solveNearbyCells(int row, int column) {
        if (row - 2 >= 0) recursivelySolve(row - 2, column);
        if (row - 1 >= 0) recursivelySolve(row - 1, column);
        if (row + 1 < this.grid.size()) recursivelySolve(row + 1, column);
        if (row + 2 < this.grid.size()) recursivelySolve(row + 2, column);
        if (column - 2 >= 0) recursivelySolve(row, column - 2);
        if (column - 1 >= 0) recursivelySolve(row, column - 1);
        if (column + 1 < this.grid.size()) recursivelySolve(row, column + 1);
        if (column + 2 < this.grid.size()) recursivelySolve(row, column + 2);
    }
}
