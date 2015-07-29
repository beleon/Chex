package chex.figures;

import chex.Vector2d;

/**
 * Created by leon on 29.07.15.
 */
public class Rook extends Figure {
    @Override
    boolean validMove(Vector2d playerDirection) {
        return false;
    }

    @Override
    public String toString() {
        return "R";
    }
}
