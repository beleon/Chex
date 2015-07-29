package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

/**
 * Created by leon on 29.07.15.
 */
public class Rook extends Figure {
    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        return 0;
    }

    @Override
    public String toString() {
        return "R";
    }
}
