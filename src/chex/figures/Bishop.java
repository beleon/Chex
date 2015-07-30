package chex.figures;

import chex.Player;
import chex.PretendEnv;
import chex.Square;
import chex.Vector2d;

public class Bishop extends Figure {

    public static final int OWN_PIECE_COLLISION_ERROR = 402;
    public static final int ENEMY_COLLISION_ERROR = 403;
    public static final int INVALID_BISHOP_MOVE = 401;

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);

        if (moveVector.isDiagonal() && moveVector.abs().max() > 0) {
            for (int i = 0; i < moveVector.abs().max(); ++i) {
                Vector2d wayPoint = from.add(moveVector.shorten().mult(i + 1));
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
    public boolean canAttack(Player player, Vector2d from, Vector2d to, PretendEnv pretendEnv) {
        Vector2d moveVector = to.sub(from);
        if (moveVector.isDiagonal() && moveVector.abs().max() > 0) {
            for (int i = 1; i < moveVector.abs().max(); ++i) {
                Vector2d wayPoint = from.add(moveVector.shorten().mult(i));
                if (pretendEnv.get(wayPoint).getPiece() != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "B";
    }
}
