package chex.figures;

import chex.*;

public class Rook extends Figure {
    public static final int OWN_PIECE_COLLISION_ERROR = 202;
    public static final int ENEMY_COLLISION_ERROR = 203;
    public static final int INVALID_ROOK_MOVE = 201;

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);

        if (moveVector.getX() != moveVector.getY() && (moveVector.getX() == 0 || moveVector.getY() == 0)) {
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
        } else {
            return INVALID_ROOK_MOVE;
        }

        if ((from.getY() == 0 && player.getColor().equals(Color.BLACK))
             || (from.getY() == 7 && player.getColor().equals(Color.WHITE))) {
            if (from.getX() == 0) {
                player.setMovedLeftRook(true);
            } else if (from.getX() == 7) {
                player.setMovedRightRook(true);
            }
        }

        return 0;
    }

    @Override
    public boolean canAttack(Player player, Vector2d from, Vector2d to, PretendEnv pretendEnv) {
        Vector2d moveVector = to.sub(from);
        if (moveVector.shorten().isNormal()) {
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
        return "R";
    }
}
