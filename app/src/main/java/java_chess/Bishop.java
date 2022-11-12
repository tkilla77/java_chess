package java_chess;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super("B", color);
    }

    @Override
    public boolean isLegalMove(Cell start, Cell dest) {
        return start.isDiagonal(dest);
    }

    @Override
    public boolean canMove(Game game, Cell start, Cell dest) {
        return isLegalMove(start, dest)
            && (game.isCellFree(dest) || game.isCellOpponent(dest))
            && game.isPathFree(start, dest);
    }
    
}
