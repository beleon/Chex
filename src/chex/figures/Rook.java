package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

public class Rook extends Figure {
    public static final int OWN_PIECE_COLLISION_ERROR = 202;
    public static final int ENEMY_COLLISION_ERROR = 203;
    public static final int INVALID_ROOK_MOVE = 201;
    private boolean hasMoved;

    public Rook() {
        hasMoved = false;
    }

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);
        Vector2d normal = moveVector.normalize();

        if (!(moveVector.getX() != moveVector.getY() && (moveVector.getX() == 0 || moveVector.getY() == 0))) {
            return INVALID_ROOK_MOVE;
        }

        for (int i = 0; i < moveVector.abs().max(); ++i) {
            Vector2d wayPoint = from.add(normal.mult(i + 1));
            if (board[wayPoint.getY()][wayPoint.getX()].getPiece() != null) {
                if (board[wayPoint.getY()][wayPoint.getX()].getPiece().belongsTo(player)) {
                    return OWN_PIECE_COLLISION_ERROR;
                } else if (i != moveVector.abs().max() - 1) {
                    return ENEMY_COLLISION_ERROR;
                }
            }
        }

        if (!hasMoved) {
            hasMoved = true;
            if (from.getX() == 0) {
                player.setMovedLeftRook(true);
            } else if (from.getX() == 7) {
                player.setMovedRightRook(true);
            } else {
                throw new IllegalStateException("Rook hasn't moved yet it is on a non starting position.");
            }
        }

        return 0;
    }

    @Override
    public String toString() {
        return "R";
    }
}
