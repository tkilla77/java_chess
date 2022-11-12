package java_chess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChessTest {
    @Test void cellTest() {
        Cell a = new Cell('A', 1);
        Cell b = new Cell('H', 8);
        assertTrue(a.isDiagonal(b));
        assertFalse(a.isStraight(b));
        assertFalse(a.isDiagonal(a));
        
        assertEquals(Direction.UP_RIGHT, a.getDirection(b));
        assertEquals(Direction.DOWN_LEFT, b.getDirection(a));

        Cell c = new Cell('A', 8);
        Cell d = new Cell('H', 1);
        assertEquals(Direction.DOWN_RIGHT, c.getDirection(d));
        assertEquals(Direction.UP_LEFT, d.getDirection(c));

        assertEquals(Direction.RIGHT, a.getDirection(d));
        assertEquals(Direction.LEFT, d.getDirection(a));
        assertEquals(Direction.DOWN, c.getDirection(a));
        assertEquals(Direction.UP, a.getDirection(c));

        assertEquals(Direction.ZERO, a.getDirection(a));

        Cell e = new Cell('C', 2);
        assertEquals(Direction.OTHER, a.getDirection(e));
    }
    
}
