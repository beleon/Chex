package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

public abstract class Figure {
    public abstract int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount);
}
