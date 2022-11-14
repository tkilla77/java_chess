package java_chess;

public enum Color {
    
    WHITE() {
        @Override public String toString() {
            return "w";
        }

        @Override public boolean isPawnMoveDirection(Direction dir) {
            return dir == Direction.UP;
        }

        @Override public boolean isPawnCaptureDirection(Direction dir) {
            return dir == Direction.UP_LEFT || dir ==  Direction.UP_RIGHT;
        }

        @Override public boolean isPawnStartRow(Cell cell) {
            return cell.getRow() == 2;
        }
    },
    BLACK() {
        @Override public String toString() {
            return "b";
        }

        @Override public boolean isPawnMoveDirection(Direction dir) {
            return dir == Direction.DOWN;
        }

        @Override public boolean isPawnCaptureDirection(Direction dir) {
            return dir == Direction.DOWN_LEFT || dir ==  Direction.DOWN_RIGHT;
        }

        @Override public boolean isPawnStartRow(Cell cell) {
            return cell.getRow() == 7;
        }
    };
    
    @Override
    public abstract String toString();
    public abstract boolean isPawnMoveDirection(Direction dir);
    public abstract boolean isPawnCaptureDirection(Direction dir);
    public abstract boolean isPawnStartRow(Cell cell);
}
