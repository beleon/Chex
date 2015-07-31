package chex;

import chex.figures.King;

public class Player {
    private Color color;
    private Vector2d direction;
    private boolean movedLeftRook, movedRightRook, movedKing;
    private int[] ff;

    public Player(Color color, Vector2d direction) {
        this.color = color;
        this.direction = direction;
        movedKing = movedRightRook = movedLeftRook = false;
        ff = new int[8];
        for (int i = 0; i < ff.length; i++) {
            ff[i] = -i;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setMovedLeftRook(boolean movedLeftRook) {
        this.movedLeftRook = movedLeftRook;
    }

    public void setMovedRightRook(boolean movedRightRook) {
        this.movedRightRook = movedRightRook;
    }

    public boolean hasMovedLeftRook() {
        return movedLeftRook;
    }

    public boolean hasMovedRightRook() {
        return movedRightRook;
    }

    public boolean hasMovedKing() {
        return movedKing;
    }

    public void setMovedKing(boolean movedKing) {
        this.movedKing = movedKing;
    }

    public int getFfByIndex(int index) {
        return ff[index];
    }

    public void setFfByIndex(int index, int value) {
        ff[index] = value;
    }

    public Vector2d getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
