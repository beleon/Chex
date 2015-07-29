public class Vector2d {
    private int x, y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Vector2d fromChessNotation(String chessNotation) {
        return new Vector2d(chessNotation.toLowerCase().charAt(0) - 'a', 8 - Character.getNumericValue(chessNotation.charAt(1)));
    }

    public static String toChessNotation(Vector2d vector2d) {
        return "" + ((char) ('a' + vector2d.getX())) + (8 - vector2d.getY());
    }

    public Vector2d add(Vector2d vector2d) {
        return new Vector2d(x + vector2d.x, vector2d.y);
    }

    public Vector2d sub(Vector2d vector2d) {
        return new Vector2d(x - vector2d.x, y - vector2d.y);
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
}
