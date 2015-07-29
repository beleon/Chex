package chex.figures;

import chex.Vector2d;

/**
 * Created by leon on 29.07.15.
 */
public class King extends Figure {
    @Override
    boolean validMove(Vector2d playerDirection) {
        return false;
    }

    @Override
    public String toString() {
        return "K";
    }
}
