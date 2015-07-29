package chex;

public class Vector2d {
    private int x, y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Vector2d fromChessNotation(String chessNotation) {
        return new Vector2d(chessNotation.toLowerCase().charAt(0) - 'a', 8 - Character.getNumericValue(chessNotation.charAt(1)));
    }

    public static String toChessNotation(Vector2d vector2d) {
        return "" + ((char) ('a' + vector2d.getX())) + (8 - vector2d.getY());
    }

    public Vector2d add(Vector2d vector2d) {
        return new Vector2d(x + vector2d.x, y + vector2d.y);
    }

    public Vector2d sub(Vector2d vector2d) {
        return new Vector2d(x - vector2d.x, y - vector2d.y);
    }

    public Vector2d mult(int scalar) {
        return new Vector2d(x * scalar, y * scalar);
    }

    public Vector2d scaleX(int scalar) {
        return new Vector2d(x * scalar, y);
    }

    public Vector2d scaleY(int scalar) {
        return new Vector2d(x, y * scalar);
    }

    public Vector2d mod(int scalar) {
        return new Vector2d(x % scalar, y % scalar);
    }

    public Vector2d normalize() {
        return new Vector2d(x != 0 ? x / Math.abs(x) : 0, y != 0 ? y / Math.abs(y) : 0);
    }

    public int maxAbs() {
        return Math.abs(x) > Math.abs(y) ? Math.abs(x) : Math.abs(y);
    }

    public Vector2d abs() {
        return new Vector2d(Math.abs(x), Math.abs(y));
    }

    public Vector2d mirror() {
        return new Vector2d(y, x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return x * 8 + y;
    }

    @Override
    public String toString() {
        return new StringBuilder("X: ").append(x).append(", Y: ").append(y).toString();
    }
}
