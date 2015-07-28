public class Vector<T> {
    private T x, y;

    public Vector(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public static Vector<Integer> fromChessNotation(String chessNotation) {
        return new Vector<>(chessNotation.toLowerCase().charAt(0) - 'a', 8 - Character.getNumericValue(chessNotation.charAt(1)));
    }

    public static String toChessNotation(Vector<Integer> vector) {
        return "" + ((char) ('a' + vector.getX())) + (8 - vector.getY());
    }
}
