package java_chess;

import com.google.common.collect.ImmutableList;

public final class Cell {
    private final int row;
    private final char column;


    public Cell(char column, int row) {
        if (row < 1 || 8 < row) {
            throw new IllegalArgumentException("illegal row " + row);
        }
        if ("abcdefgh".indexOf(Character.toLowerCase(column)) < 0) {
            throw new IllegalArgumentException("illegal column " + column);
        }
        this.column = Character.toLowerCase(column);
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    /** Returns the numeric column index in 1-8. */
    public int getColumnIndex() {
        return "abcdefgh".indexOf(column) + 1;
    }

    public boolean isDiagonal(Cell dest) {
        int rowDelta = Math.abs(this.row - dest.row);
        int colDelta = Math.abs(this.getColumnIndex() - dest.getColumnIndex());
        return rowDelta > 0 && rowDelta == colDelta;
    }

    public boolean isStraight(Cell cell) {
        return this.row == cell.row ^ this.column == cell.column;
    }

    public int getDistance(Cell dest) {
        int rowDelta = Math.abs(this.row - dest.row);
        int colDelta = Math.abs(this.getColumnIndex() - dest.getColumnIndex());
        return Math.max(rowDelta, colDelta);
    }

    public Direction getDirection(Cell dest) {
        int rowDelta = this.row - dest.row;
        int colDelta = this.getColumnIndex() - dest.getColumnIndex();

        if (rowDelta == colDelta) {
            if (rowDelta > 0) {
                return Direction.DOWN_LEFT;
            } else if (rowDelta < 0) {
                return Direction.UP_RIGHT;
            } else {
                return Direction.ZERO;
            }
        }
        if (rowDelta == -colDelta) {
            if (rowDelta > 0) {
                return Direction.DOWN_RIGHT;
            } else {
                return Direction.UP_LEFT;
            }
        }
        if (rowDelta == 0) {
            if (colDelta > 0) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        }
        if (colDelta == 0) {
            if (rowDelta > 0) {
                return Direction.DOWN;
            } else {
                return Direction.UP;
            }
        }
        return Direction.OTHER;
    }

    /** Returns the cells between this cell and dest if dest is either straight or diagonal
     * from this cell, an empyt iterable otherwise. The start and destination cells are not
     * included in the return value. */
    public Iterable<Cell> getPath(Cell dest) {
        Direction direction = getDirection(dest);
        if (direction == Direction.ZERO || direction == Direction.OTHER) {
            return ImmutableList.of();
        }
        ImmutableList.Builder<Cell> result = ImmutableList.builder();
        Cell c = this;
        while (!c.equals(dest)) {
            c= c.move(direction);
            result.add(c);
        }
        return result.build();
    }

    public Cell move(Direction direction) {
        int rowIncrement;
        int colIncrement;
        switch(direction) {
            case UP:
                rowIncrement = 1;
                colIncrement = 0;
                break;
            case UP_LEFT:
                rowIncrement = 1;
                colIncrement = -1;
                break;
            case LEFT:
                rowIncrement = 0;
                colIncrement = -1;
                break;
            case DOWN_LEFT:
                rowIncrement = -1;
                colIncrement = -1;
                break;
            case DOWN:
                rowIncrement = -1;
                colIncrement = 0;
                break;
            case DOWN_RIGHT:
                rowIncrement = -1;
                colIncrement = 1;
                break;
            case RIGHT:
                rowIncrement = 0;
                colIncrement = 1;
                break;
            case UP_RIGHT:
                rowIncrement = 1;
                colIncrement = 1;
                break;
            case ZERO:
            case OTHER:
            default:
                return this;
        }
        int row = this.row + rowIncrement;
        int col = this.getColumnIndex() + colIncrement;

        return new Cell(colChar(col), row);
    }

    private char colChar(int col) {
        return "abcdefgh".charAt(col - 1);
    }


}
