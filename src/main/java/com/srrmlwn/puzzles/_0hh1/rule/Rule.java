package com.srrmlwn.puzzles._0hh1.rule;


import com.srrmlwn.puzzles._0hh1.Cell;
import com.srrmlwn.puzzles._0hh1.Grid;

/**
 * Created by sriram on 3/2/15.
 */
public interface Rule {

    public Cell process(Grid grid, int row, int column);

    public String description();

    public class EqualColorsInRow implements Rule {

        @Override
        public Cell process(Grid grid, int row, int column) {
            if (grid.size() / 2 == grid.getRedsInRow(row) || grid.size() / 2 == grid.getRedsInCol(column))
                return grid.update(Cell.BLUE, row, column);
            if (grid.size() / 2 == grid.getBluesInRow(row) || grid.size() / 2 == grid.getBluesInCol(column))
                return grid.update(Cell.RED, row, column);

            return Cell.UNDEFINED;
        }

        @Override
        public String description() {
            return "Row/Column should have equal number of reds and blues";
        }
    }

    public class ThreeColorsInSequence implements Rule {

        private static boolean threeSequentialColors(Grid grid, Cell cell, int row, int column) {
            return (row - 2 >= 0 && grid.cellAt(row - 2, column) == grid.cellAt(row - 1, column) && grid.cellAt(row - 1, column) == cell)
                    || (row - 1 >= 0 && row + 1 < grid.size() && grid.cellAt(row - 1, column) == grid.cellAt(row + 1, column) && grid.cellAt(row + 1, column) == cell)
                    || (row + 2 < grid.size() && grid.cellAt(row + 2, column) == grid.cellAt(row + 1, column) && grid.cellAt(row + 1, column) == cell)
                    || (column - 2 >= 0 && grid.cellAt(row, column - 1) == grid.cellAt(row, column - 2) && grid.cellAt(row, column - 2) == cell)
                    || (column - 1 >= 0 && column + 1 < grid.size() && grid.cellAt(row, column - 1) == grid.cellAt(row, column + 1) && grid.cellAt(row, column + 1) == cell)
                    || (column + 2 < grid.size() && grid.cellAt(row, column + 2) == grid.cellAt(row, column + 1) && grid.cellAt(row, column + 1) == cell);
        }

        @Override
        public Cell process(Grid grid, int row, int column) {
            if (threeSequentialColors(grid, Cell.RED, row, column)) return grid.update(Cell.BLUE, row, column);
            if (threeSequentialColors(grid, Cell.BLUE, row, column)) return grid.update(Cell.RED, row, column);
            return Cell.UNDEFINED;
        }

        @Override
        public String description() {
            return "Three sequential cells cannot have the same color";
        }
    }

    public class UnidenticalRows implements Rule {

        @Override
        public Cell process(Grid grid, int row, int column) {
            if (grid.unpopulatedCellsInRow(row) == 2) {
                for (int otherRow = 0; otherRow < grid.size(); otherRow++) {
                    if (otherRow != row && grid.unpopulatedCellsInRow(otherRow) == 0) {
                        Cell[] populatedRow = new Cell[grid.size()];
                        boolean populateRow = true;

                        for (int otherColumn = 0; otherColumn < grid.size(); otherColumn++) {
                            if (grid.cellAt(row, otherColumn) == grid.cellAt(otherRow, otherColumn) || grid.cellAt(row, otherColumn).isUndefined()) {
                                populatedRow[otherColumn] = grid.cellAt(otherRow, otherColumn);
                            } else {
                                populateRow = false;
                            }
                        }

                        if (populateRow) {
                            for (int populatedColumn = 0; populatedColumn < grid.size(); populatedColumn++) {
                                if (grid.cellAt(row, populatedColumn).isUndefined())
                                    grid.update(populatedRow[populatedColumn].flip(), row, populatedColumn);
                            }
                            break;
                        }

                    }
                }
            }

            return grid.cellAt(row, column);
        }

        @Override
        public String description() {
            return "No two rows can be identical";
        }
    }

    public class UnidenticalColumns implements Rule {

        @Override
        public Cell process(Grid grid, int row, int column) {
            if (grid.unpopulatedCellsInColumn(column) == 2) {
                for (int otherColumn = 0; otherColumn < grid.size(); otherColumn++) {
                    if (otherColumn != column && grid.unpopulatedCellsInColumn(otherColumn) == 0) {
                        Cell[] populatedColumn = new Cell[grid.size()];
                        boolean populateColumn = true;

                        for (int otherRow = 0; otherRow < grid.size(); otherRow++) {
                            if (grid.cellAt(otherRow, column) == grid.cellAt(otherRow, otherColumn) || grid.cellAt(otherRow, column).isUndefined()) {
                                populatedColumn[otherRow] = grid.cellAt(otherRow, otherColumn);
                            } else {
                                populateColumn = false;
                            }
                        }

                        if (populateColumn) {
                            for (int populatedRow = 0; populatedRow < grid.size(); populatedRow++) {
                                if (grid.cellAt(populatedRow, column).isUndefined())
                                    grid.update(populatedColumn[populatedRow].flip(), populatedRow, column);
                            }
                            break;
                        }

                    }
                }
            }

            return grid.cellAt(row, column);
        }

        @Override
        public String description() {
            return "No two columns can be identical";
        }
    }
}
