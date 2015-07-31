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
    public static final int VALID_KING_MOVE = 6;

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);

        if (moveVector.abs().max() == 1) {
            if (board[to.getY()][to.getX()].getPiece() != null
                && board[to.getY()][to.getX()].getPiece().belongsTo(player)) {
                return OWN_PIECE_COLLISION_ERROR;
            } else {
                return VALID_KING_MOVE;
            }
        } else if (!player.hasMovedKing()) {
            //castling
            if (!new PretendEnv(board, from, from).isInCheck(player)) {
                Vector2d leftRook = from.sub(new Vector2d(4, 0));
                Vector2d rightRook = from.add(new Vector2d(3, 0));
                if (moveVector.equals(new Vector2d(2, 0)) && !player.hasMovedRightRook()
                        && board[rightRook.getY()][rightRook.getX()].getPiece() != null
                        && board[rightRook.getY()][rightRook.getX()].getPiece().getFigure() instanceof Rook
                        && board[rightRook.getY()][rightRook.getX()].getPiece().belongsTo(player)) {
                    for (int i = 0; i < 2; i++) {
                        Vector2d wayPoint = from.add(new Vector2d(1, 0).mult(i + 1));
                        if (new PretendEnv(board, from, wayPoint).isInCheck(player)) {
                            return IN_CHECK_CASTLING_ERROR;
                        }
                        if (board[wayPoint.getY()][wayPoint.getX()].getPiece() != null) {
                            return CASTLING_COLLISION_ERROR;
                        }
                    }
                    return RIGHT_SIDE_CASTLING_MOVE;
                } else if (moveVector.equals(new Vector2d(-2, 0)) && !player.hasMovedLeftRook()
                    && board[leftRook.getY()][leftRook.getX()].getPiece() != null
                            && board[leftRook.getY()][leftRook.getX()].getPiece().getFigure() instanceof Rook
                            && board[leftRook.getY()][leftRook.getX()].getPiece().belongsTo(player)) {
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
