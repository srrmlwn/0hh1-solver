package com.srrmlwn.puzzles._0hh1;

/**
 * Created by sriram on 3/2/15.
 */
public enum Cell {
    RED('r'),
    BLUE('b'),
    UNDEFINED('-');
    private final char c;

    Cell(char c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return String.valueOf(this.c);
    }

    public boolean isUndefined() {
        return this == UNDEFINED;
    }

    public Cell flip() {
        if (this == Cell.RED) return Cell.BLUE;
        if (this == Cell.BLUE) return Cell.RED;
        return Cell.UNDEFINED;
    }

    public static Cell valueOf(char c)
    {
        for (Cell cell : values())
        {
            if (cell.c == c)
            {
                return cell;
            }
        }
        throw new IllegalStateException("Unknown input " + c);
    }
}
