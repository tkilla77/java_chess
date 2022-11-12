package java_chess;

public abstract class Piece {
    private final String name;
    private final Color color;

    protected Piece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    /** Returns true exactly if the piece could theoretically move from start to dest
     * ignoring any game state. */
    public abstract boolean isLegalMove(Cell start, Cell dest);
    /** Returns true exactly if the piece could move from start to dest. */
    public abstract boolean canMove(Game game, Cell start, Cell dest);

    @Override
    public String toString() {
        return this.name;
    }

    public Color color() {
        return this.color;
    }
}
