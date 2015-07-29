package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

public class Bishop extends Figure {

    public static final int OWN_PIECE_COLLISION_ERROR = 402;
    public static final int ENEMY_COLLISION_ERROR = 403;
    public static final int INVALID_BISHOP_MOVE = 401;

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);

        if (moveVector.normalize().abs().equals(new Vector2d(1, 1))) {
            for (int i = 0; i < moveVector.abs().max(); ++i) {
                Vector2d wayPoint = from.add(moveVector.normalize().mult(i + 1));
                if (board[wayPoint.getY()][wayPoint.getX()].getPiece() != null) {
                    if (board[wayPoint.getY()][wayPoint.getX()].getPiece().belongsTo(player)) {
                        return OWN_PIECE_COLLISION_ERROR;
                    } else if (i != moveVector.abs().max() - 1) {
                        return ENEMY_COLLISION_ERROR;
                    }
                }
            }
            return 0;
        } else {
            return INVALID_BISHOP_MOVE;
        }
    }

    @Override
    public String toString() {
        return "B";
    }
}
