package java_chess;

public enum Direction {
    /** The direction from a cell to itself. */
    ZERO,
    /** The direction from the white to the black side. */
    UP,
    UP_LEFT,
    LEFT,
    DOWN_LEFT,
    /** The direction from the black to the white side. */
    DOWN,
    DOWN_RIGHT,
    RIGHT,
    UP_RIGHT,
    /** Any non-straight and non-diagonal direction. */
    OTHER;
}
