package java_chess;

public class Pawn extends Piece {

    protected Pawn(Color color) {
        super("P", color);
    }

    @Override
    public boolean isLegalMove(Cell start, Cell dest) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canMove(Game game, Cell start, Cell dest) {
        int startRow;
        Direction direction;
        if (game.getTurn() == Color.WHITE) {
            startRow = 2;
            direction = Direction.UP;
        } else {
            startRow = 7;
            direction = Direction.DOWN;
        }
        Direction dir = start.getDirection(dest);
        if (start.getRow() == startRow) {
            // consider double move from initial position
            if (dir == direction && start.getDistance(dest) == 2) {
                return game.isCellFree(dest) && game.isPathFree(start, dest);
            }
        }
        if (start.getDistance(dest) != 1) {
            return false;
        }
        if (dir == direction) {
            return game.isCellFree(dest);
        }
        // TODO implement en-passant.
        if (game.getTurn() == Color.WHITE && (dir == Direction.UP_LEFT || dir == Direction.UP_RIGHT)) {
            return game.isCellOpponent(dest);
        } 
        if (game.getTurn() == Color.BLACK && (dir == Direction.DOWN_LEFT || dir == Direction.DOWN_RIGHT)) {
            return game.isCellOpponent(dest);
        } 
        return false;
    }
    

}
