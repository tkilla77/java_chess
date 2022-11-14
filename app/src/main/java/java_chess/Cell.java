package java_chess;

import com.google.common.collect.ImmutableList;

public final class Cell {
    private final int row;
    private final char column;

    public static Cell at(char column, int row) {
        return new Cell(column, row);
    }

    public static Cell at(String address) {
        if (address.length() != 2) {
            throw new IllegalArgumentException("illegal address " + address);
        }
        try {
            return new Cell(address.charAt(0), Integer.parseUnsignedInt(address, 1, 2, 10));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("illegal address " + address, e);
        }
    }

    private Cell(char column, int row) {
        if (row < 1 || 8 < row) {
            throw new IllegalArgumentException("illegal row " + row);
        }
        if ("abcdefgh".indexOf(Character.toLowerCase(column)) < 0) {
            throw new IllegalArgumentException("illegal column " + column);
        }
        this.column = Character.toLowerCase(column);
        this.row = row;
    }

    public int row() {
        return row;
    }

    public char col() {
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

    /** Returns a new cell in the given direction from this cell. Throws IllegalArgumenException if
     * the cell in the given direction does not exist (i.e. is not on the board) or if direction is
     * {@linkplain Direction.OTHER}. */
    public Cell move(Direction direction) {
        return move(direction, 1);
    }

    /** Returns a new cell in the given direction from this cell. Throws IllegalArgumenException if
     * the cell in the given direction does not exist (i.e. is not on the board) or if direction is
     * {@linkplain Direction.OTHER}.
     */
    public Cell move(Direction direction, int steps) {
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
            case OTHER:
                throw new IllegalArgumentException("Cannot move in direction OTHER");
            case ZERO:
            default:
                return this;
        }
        int row = this.row + rowIncrement * steps;
        int col = this.getColumnIndex() + colIncrement * steps;

        return new Cell(colChar(col), row);
    }

    private char colChar(int col) {
        return "abcdefgh".charAt(col - 1);
    }

    /** Returns true if moving in {@code value} is legal and would result is
     * not the current cell. */
    public boolean isLegalDirection(Direction direction) {
        if (direction == Direction.ZERO) {
            return false;
        }
        try {
            move(direction);
            return true;
        } catch (IllegalArgumentException e) { 
            return false;
        }
    }

    /** Returns the list of all legal directions moveable from this cell. */
    public Iterable<Direction> getLegalDirections() {
        ImmutableList.Builder<Direction> result = ImmutableList.builder();
        for (Direction direction : Direction.values()) {
            if (isLegalDirection(direction)) {
                result.add(direction);
            }
        }
        return result.build();
    }

    /** Returns the list of all neighboring cells. */
    public Iterable<Cell> getNeighbors() {
        ImmutableList.Builder<Cell> result = ImmutableList.builder();
        for (Direction direction : Direction.values()) {
            try {
                if (direction != Direction.ZERO && direction != Direction.OTHER) {
                    result.add(move(direction));
                }
            } catch (IllegalArgumentException e) {
                // swallow - moved off the board
            }
        }
        return result.build();
    }

}
