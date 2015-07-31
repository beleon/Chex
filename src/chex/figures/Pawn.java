package chex.figures;

import chex.Player;
import chex.PretendEnv;
import chex.Square;
import chex.Vector2d;

public class Pawn extends Figure {
    public static final int NOT_ON_BASELINE_ERROR = 103;
    public static final int COLLISION_ERROR = 102;
    public static final int INVALID_PAWN_MOVE = 101;
    public static final int NO_COLLISION_ERROR = 104;
    public static final int CANT_TAKE_OWN_PIECE = 105;
    public static final int EN_PASSANT_MOVE = 111;
    public static final int VALID_FF_MOVE = 7;

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
                Vector2d wayPoint = from.add(player.getDirection());
                if (board[to.getY()][to.getX()].getPiece() == null
                    && board[wayPoint.getY()][wayPoint.getX()].getPiece() == null) {
                    return VALID_FF_MOVE;
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
                       && !board[enPassantPos.getY()][enPassantPos.getX()].getPiece().belongsTo(player)
                       && board[enPassantPos.getY()][enPassantPos.getX()].getPiece().getPlayer().getFfByIndex(to.getX())
                        == turnCount - 1) {
                return EN_PASSANT_MOVE;
            } else {
                return NO_COLLISION_ERROR;
            }
        } else {
            return INVALID_PAWN_MOVE;
        }
    }

    @Override
    public boolean canAttack(Player player, Vector2d from, Vector2d to, PretendEnv pretendEnv) {
        Vector2d moveVector = to.sub(from);

        return moveVector.isDiagonal() && moveVector.getY() == player.getDirection().getY();
    }

    @Override
    public String toString() {
        return "P";
    }
}
