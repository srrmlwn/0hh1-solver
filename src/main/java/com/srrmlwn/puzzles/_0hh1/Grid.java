package com.srrmlwn.puzzles._0hh1;

import java.util.Arrays;

/**
 * Created by sriram on 3/2/15.
 */
public class Grid {
    private final Cell[][] grid;
    private final int[] redsInRow;
    private final int[] redsInCol;
    private final int[] bluesInRow;
    private final int[] bluesInCol;
    private int unsolvedCells;

    public Grid(Cell[][] grid) {
        this.grid = grid;
        this.redsInRow = new int[grid.length];
        Arrays.fill(this.redsInRow, 0);
        this.redsInCol = new int[grid.length];
        Arrays.fill(this.redsInCol, 0);
        this.bluesInRow = new int[grid.length];
        Arrays.fill(this.bluesInRow, 0);
        this.bluesInCol = new int[grid.length];
        Arrays.fill(this.bluesInCol, 0);

        this.unsolvedCells = 0;
        for (int i = 0, gridLength = grid.length; i < gridLength; i++) {
            Cell[] row = grid[i];
            for (int j = 0, rowLength = row.length; j < rowLength; j++) {
                Cell cell = row[j];
                if (cell.isUndefined()) this.unsolvedCells++;
                if (cell == Cell.RED) {
                    this.redsInRow[i]++;
                    this.redsInCol[j]++;
                }
                if (cell == Cell.BLUE) {
                    this.bluesInRow[i]++;
                    this.bluesInCol[j]++;
                }
            }
        }
    }

    public Cell update(Cell cell, int row, int column)
    {
        if (this.grid[row][column].isUndefined()) {
            this.grid[row][column] = cell;
            this.unsolvedCells--;
            if (cell == Cell.RED)
            {
                this.redsInCol[column]++;
                this.redsInRow[row]++;
            }
            if (cell == Cell.BLUE)
            {
                this.bluesInCol[column]++;
                this.bluesInRow[row]++;
            }
        }
        return this.grid[row][column];
    }

    public boolean isSolved()
    {
        return this.unsolvedCells == 0;
    }

    public Cell cellAt(int row, int column)
    {
        return this.grid[row][column];
    }

    public int getRedsInRow(int row) {
        return redsInRow[row];
    }

    public int getRedsInCol(int column) {
        return redsInCol[column];
    }

    public int getBluesInRow(int row) {
        return bluesInRow[row];
    }

    public int getBluesInCol(int column) {
        return bluesInCol[column];
    }

    public int size()
    {
        return this.grid.length;
    }

    public int unpopulatedCellsInRow(int row)
    {
        int cellCount = 0;
        for (int column = 0; column < grid.length; column++)
        {
            if (grid[row][column].isUndefined()) cellCount++;
        }
        return cellCount;
    }

    public int unpopulatedCellsInColumn(int column)
    {
        int cellCount = 0;
        for (Cell[] row : grid) {
            if (row[column].isUndefined()) cellCount++;
        }
        return cellCount;
    }

    public int getUnsolvedCells() {
        return this.unsolvedCells;
    }
}
