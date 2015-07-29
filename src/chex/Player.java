package chex;

/**
 * Created by leon on 29.07.15.
 */
public class Player {
    private Color color;
    private boolean movedLeftRook, movedRightRook, movedKing;

    public Player(Color color) {
        this.color = color;
        movedKing = movedRightRook = movedLeftRook = false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
