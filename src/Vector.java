public class Vector {
    private int x, y;

    public Vector(int x, int y) {
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

    public static Vector fromChessNotation(String chessNotation) {
        return new Vector(chessNotation.toLowerCase().charAt(0) - 'a', 8 - Character.getNumericValue(chessNotation.charAt(1)));
    }
}
