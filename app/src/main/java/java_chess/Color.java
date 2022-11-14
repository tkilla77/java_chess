package java_chess;

public enum Color {
    
    WHITE() {
        @Override public String toString() {
            return "w";
        }

        @Override public Direction getPawnMoveDirection() {
            return Direction.UP;
        }

        @Override public boolean isPawnCaptureDirection(Direction dir) {
            return dir == Direction.UP_LEFT || dir ==  Direction.UP_RIGHT;
        }

        @Override public int getPawnStartRow() {
            return 2;
        }

        @Override public int getPawnConversionRow() {
            return 8;
        }
    },
    BLACK() {
        @Override public String toString() {
            return "b";
        }

        @Override public Direction getPawnMoveDirection() {
            return Direction.DOWN;
        }

        @Override public boolean isPawnCaptureDirection(Direction dir) {
            return dir == Direction.DOWN_LEFT || dir ==  Direction.DOWN_RIGHT;
        }

        @Override public int getPawnStartRow() {
            return 7;
        }

        @Override public int getPawnConversionRow() {
            return 1;
        }
    };
    
    @Override
    public abstract String toString();
    public boolean isPawnMoveDirection(Direction dir) {
        return dir == getPawnMoveDirection();
    };
    public abstract boolean isPawnCaptureDirection(Direction dir);
    public abstract int getPawnStartRow();
    public boolean isPawnStartRow(Cell cell) {
        return cell.row() == getPawnStartRow();
    }
    public abstract Direction getPawnMoveDirection();
    public abstract int getPawnConversionRow();
    public boolean isPawnConversionRow(Cell cell) {
        return cell.row() == getPawnConversionRow();
    }
}
