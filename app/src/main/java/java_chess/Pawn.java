package java_chess;

public class Pawn extends Piece {

    protected Pawn(Color color) {
        super("P", color);
    }

    @Override
    public boolean isLegalMove(Cell start, Cell dest) {
        // Impossible to decide statically without game information.
        Direction dir = start.getDirection(dest);
        int distance = start.getDistance(dest);
        if (distance == 2) {
            // consider double move from initial position
            return color().isPawnStartRow(start) && color().isPawnMoveDirection(dir);
        }
        if (distance == 1) {
            return color().isPawnMoveDirection(dir) || color().isPawnCaptureDirection(dir);
        }
        return false;
    }

    @Override
    public boolean canMove(Game game, Cell start, Cell dest) {
        Direction dir = start.getDirection(dest);
        int distance = start.getDistance(dest);
        if (distance == 2) {
            // consider double move from initial position
            return color().isPawnStartRow(start)
                    && color().isPawnMoveDirection(dir)
                    && game.isCellFree(dest)
                    && game.isPathFree(start, dest);
        }
        if (distance == 1) {
            if (color().isPawnMoveDirection(dir)) {
                return game.isCellFree(dest);
            }
            // TODO implement en-passant.
            if (color().isPawnCaptureDirection(dir)) {
                return game.isCellOpponent(dest);
            } 
        }
        return false;
    }
    

}
