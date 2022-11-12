package java_chess;

public interface Game {
    /** Returns "w" or "b". */
    Color getTurn();
    boolean isCellFree(Cell cell);
    boolean isPathFree(Cell start, Cell dest);
    boolean isCellOpponent(Cell cell);
    boolean isCellOwnPiece(Cell cell);
}
