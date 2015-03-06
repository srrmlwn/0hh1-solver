package com.srrmlwn.puzzles._0hh1.util;

import com.srrmlwn.puzzles._0hh1.Cell;
import com.srrmlwn.puzzles._0hh1.Grid;

/**
 * Created by sriram on 3/2/15.
 */
public class GridUtils {

    public static Grid toGrid(String gridRepresentation)
    {
        final int gridCells = gridRepresentation.length();
        if (!isPerfectSquare(gridCells)) throw new IllegalArgumentException("Grid of size " + gridCells + " is not a perfect square!");
        int gridSize = (int) Math.sqrt(gridCells);
        Cell[][] grid = new Cell[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                char c = gridRepresentation.charAt(i * gridSize + j);
                grid[i][j] = Cell.valueOf(c);
                }
            }
        return new Grid(grid);
    }

    public static String asString(Grid grid)
    {
        StringBuilder builder = new StringBuilder(grid.size() * grid.size());
        for (int row = 0; row < grid.size(); row++)
        {
            for (int column = 0; column < grid.size(); column++)
            {
                builder.append(grid.cellAt(row, column).toString());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private static boolean isPerfectSquare (int x)
    {
        int sqrt = (int) Math.sqrt(x);
        return sqrt * sqrt == x;
    }
}
