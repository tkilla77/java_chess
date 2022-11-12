package java_chess;

public class ChessGame implements Game {
    private Color nextTurn = Color.WHITE;
    private Piece[][] board = new Piece[8][8];

    public ChessGame() {
        for (int row = 1; row <= 8; row++) {
            setPiece(new Cell('B', row), new Pawn(Color.WHITE));
            setPiece(new Cell('B', row), new Pawn(Color.WHITE));

        }
    }

    public static ChessGame readGame(String notation) {

    }

    public boolean attemptMove(Cell start, Cell dest) {
        Piece piece = piece(start);
        if (piece == null || piece.color() != getTurn()) {
            return false;
        }
        if (!piece.canMove(this, start, dest)) {
            return false;
        }
        // appears like a legal move - check for game logic
        if (isCheck)
    }


    @Override
    public Color getTurn() {
        return nextTurn;
    }

    private Piece piece(Cell cell) {
        return board[cell.getColumnIndex() - 1][cell.getRow() - 1];
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
