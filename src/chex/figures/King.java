package chex.figures;

import chex.Player;
import chex.PretendEnv;
import chex.Square;
import chex.Vector2d;

public class King extends Figure {
    public static final int INVALID_KING_MOVE = 601;
    public static final int OWN_PIECE_COLLISION_ERROR = 602;
    public static final int LEFT_SIDE_CASTLING_MOVE = 612;
    public static final int RIGHT_SIDE_CASTLING_MOVE = 611;
    public static final int IN_CHECK_CASTLING_ERROR = 613;
    public static final int CASTLING_COLLISION_ERROR = 614;

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);

        if (moveVector.abs().max() == 1) {
            if (board[to.getY()][to.getX()].getPiece() != null
                && board[to.getY()][to.getX()].getPiece().belongsTo(player)) {
                return OWN_PIECE_COLLISION_ERROR;
            } else {
                player.setMovedKing(true);
                return 0;
            }
        } else if (!player.hasMovedKing()) {
            //castling
            if (!new PretendEnv(board, from, from).isInCheck(player)) {
                if (moveVector.equals(new Vector2d(2, 0)) && !player.hasMovedRightRook()) {
                    for (int i = 0; i < 2; i++) {
                        Vector2d wayPoint = from.add(new Vector2d(1, 0).mult(i + 1));
                        if (new PretendEnv(board, from, wayPoint).isInCheck(player)) {
                            return IN_CHECK_CASTLING_ERROR;
                        }
                        if (board[wayPoint.getY()][wayPoint.getX()].getPiece() != null) {
                            return CASTLING_COLLISION_ERROR;
                        }
                    }
                    player.setMovedKing(true);
                    return RIGHT_SIDE_CASTLING_MOVE;
                } else if (moveVector.equals(new Vector2d(-2, 0)) && !player.hasMovedLeftRook()) {
                    for (int i = 0; i < 2; i++) {
                        Vector2d wayPoint = from.sub(new Vector2d(1, 0).mult(i + 1));
                        if (new PretendEnv(board, from, wayPoint).isInCheck(player)) {
                            return IN_CHECK_CASTLING_ERROR;
                        }
                        if (board[wayPoint.getY()][wayPoint.getX()].getPiece() != null) {
                            return CASTLING_COLLISION_ERROR;
                        }
                    }

                    Vector2d wayPoint = from.sub(new Vector2d(3, 0));
                    if (board[wayPoint.getY()][wayPoint.getX()].getPiece() != null) {
                        return CASTLING_COLLISION_ERROR;
                    }
                    player.setMovedKing(true);
                    return LEFT_SIDE_CASTLING_MOVE;
                } else {
                    return INVALID_KING_MOVE;
                }
            } else {
                return IN_CHECK_CASTLING_ERROR;
            }
        } else {
            return INVALID_KING_MOVE;
        }
    }

    @Override
    public boolean canAttack(Player player, Vector2d from, Vector2d to, PretendEnv pretendEnv) {
        Vector2d moveVector = to.sub(from);

        return moveVector.abs().max() == 1;
    }

    @Override
    public String toString() {
        return "K";
    }
}
