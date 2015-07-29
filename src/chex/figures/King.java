package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

public class King extends Figure {
    public King(Player owner) {
        owner.registerKing(this);
    }

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        return 0;
    }

    @Override
    public String toString() {
        return "K";
    }
}
