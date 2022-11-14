package java_chess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChessTest {
    @Test void cellTest() {
        Cell a = Cell.at("A1");
        Cell b = Cell.at("H8");
        assertTrue(a.isDiagonal(b));
        assertFalse(a.isStraight(b));
        assertFalse(a.isDiagonal(a));
        
        assertEquals(Direction.UP_RIGHT, a.getDirection(b));
        assertEquals(Direction.DOWN_LEFT, b.getDirection(a));

        Cell c = Cell.at("A8");
        Cell d = Cell.at("H1");
        assertEquals(Direction.DOWN_RIGHT, c.getDirection(d));
        assertEquals(Direction.UP_LEFT, d.getDirection(c));

        assertEquals(Direction.RIGHT, a.getDirection(d));
        assertEquals(Direction.LEFT, d.getDirection(a));
        assertEquals(Direction.DOWN, c.getDirection(a));
        assertEquals(Direction.UP, a.getDirection(c));

        assertEquals(Direction.ZERO, a.getDirection(a));

        Cell e = Cell.at("C2");
        assertEquals(Direction.OTHER, a.getDirection(e));
    }

    @Test void pawnTestRegular() {
        for (Color color : Color.values()) {
            Pawn p = new Pawn(color);
            Cell start = Cell.at('B', color.getPawnStartRow());
            while (!color.isPawnConversionRow(start)) {
                for (Direction direction : start.getLegalDirections()) {
                    if (color.isPawnMoveDirection(direction) || color.isPawnCaptureDirection(direction)) {
                        assertTrue(p.isLegalMove(start, start.move(direction)));
                    } else {
                        assertFalse(p.isLegalMove(start, start.move(direction)));
                    }
                }
                Cell dest = start.move(color.getPawnMoveDirection());
                start = dest;
            }            
        }
    }

    @Test void pawnTestDouble() {
        for (Color color : Color.values()) {
            Pawn p = new Pawn(color);
            Cell start = Cell.at('B', color.getPawnStartRow());
            assertTrue(p.isLegalMove(start, start.move(color.getPawnMoveDirection(), 2)));
        }
        for (Color color : Color.values()) {
            Pawn p = new Pawn(color);
            Cell start = Cell.at('B', 4);
            assertFalse(p.isLegalMove(start, start.move(color.getPawnMoveDirection(), 2)));
        }
    }
    
}
