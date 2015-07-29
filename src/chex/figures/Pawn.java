package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

public class Pawn extends Figure {
    public static final int NOT_ON_BASELINE_ERROR = 103;
    public static final int COLLISION_ERROR = 102;
    public static final int INVALID_PAWN_MOVE = 101;
    public static final int NO_COLLISION_ERROR = 104;
    public static final int CANT_TAKE_OWN_PIECE = 105;

    private int fastForward;

    public Pawn() {
        fastForward = -1;
    }

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);
        if (moveVector.equals(player.getDirection())) {
          if (board[to.getY()][to.getX()].getPiece() == null) {
              return 0;
          } else {
              return COLLISION_ERROR;
          }
        } else if (moveVector.equals(player.getDirection().mult(2))) {
            if (from.scaleX(0).equals(new Vector2d(0, 7).add(player.getDirection()).mod(7))) {
                Vector2d inBetween = from.add(player.getDirection());
                if (board[to.getY()][to.getX()].getPiece() == null
                    && board[inBetween.getY()][inBetween.getX()].getPiece() == null) {
                    fastForward = turnCount;
                    return 0;
                } else {
                    return COLLISION_ERROR;
                }
            } else {
                return NOT_ON_BASELINE_ERROR;
            }
        } else if (moveVector.equals(player.getDirection().add(new Vector2d(1, 0)))
                   || moveVector.equals(player.getDirection().add(new Vector2d(-1, 0)))) {
            Vector2d enPassantPos = to.sub(player.getDirection());
            if (board[to.getY()][to.getX()].getPiece() != null) {
                if (!board[to.getY()][to.getX()].getPiece().belongsTo(player)) {
                    return 0;
                } else {
                    return CANT_TAKE_OWN_PIECE;
                }
            } else if (board[enPassantPos.getY()][enPassantPos.getX()].getPiece() != null
                       && board[enPassantPos.getY()][enPassantPos.getX()].getPiece().getFigure() instanceof Pawn
                       && ((Pawn) board[enPassantPos.getY()][enPassantPos.getX()].getPiece().getFigure())
                            .getFastForward() == turnCount - 1) {
                //en passant
                return 0;
            } else {
                return NO_COLLISION_ERROR;
            }
        } else {
            return INVALID_PAWN_MOVE;
        }
    }

    public int getFastForward() {
        return fastForward;
    }

    @Override
    public String toString() {
        return "P";
    }
}
