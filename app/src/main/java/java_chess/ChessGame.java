package java_chess;

public class ChessGame implements Game {
    private Color nextTurn = Color.WHITE;
    private Piece[][] board = new Piece[8][8];

    public ChessGame() {
    }

    public static ChessGame readGame(String notation) {
        return null;
    }

    public boolean attemptMove(Cell start, Cell dest) {
        Piece piece = piece(start);
        if (piece == null || piece.color() != getTurn()) {
            return false;
        }
        if (!piece.canMove(this, start, dest)) {
            return false;
        }
        // TODO appears like a legal move - check for game logic
        return true;
    }


    @Override
    public Color getTurn() {
        return nextTurn;
    }

    private Piece piece(Cell cell) {
        return board[cell.getColumnIndex() - 1][cell.row() - 1];
    }

    @Override
    public boolean isCellFree(Cell cell) {
        return piece(cell) == null;
    }

    @Override
    public boolean isPathFree(Cell start, Cell dest) {
        for (Cell c : start.getPath(dest)) {
            if (!isCellFree(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCellOpponent(Cell cell) {
        Piece piece = piece(cell);
        return piece != null && piece.color() != getTurn();
    }

    @Override
    public boolean isCellOwnPiece(Cell cell) {
        Piece piece = piece(cell);
        return piece != null && piece.color() == getTurn();
    }
    
}
