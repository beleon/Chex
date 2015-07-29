package chex.figures;

import chex.Player;
import chex.Square;
import chex.Vector2d;

public class Knight extends Figure {

    public static final int INVALID_KNIGHT_MOVE = 301;
    public static final int OWN_PIECE_COLLISION_ERROR = 302;

    @Override
    public int validateMove(Player player, Vector2d from, Vector2d to, Square[][] board, int turnCount) {
        Vector2d moveVector = to.sub(from);
        Vector2d bluePrintMove = new Vector2d(1, 2);

        if (moveVector.abs().equals(bluePrintMove) || moveVector.abs().mirror().equals(bluePrintMove)) {
            if (board[to.getY()][to.getX()].getPiece() != null
                && board[to.getY()][to.getX()].getPiece().belongsTo(player)) {
                return OWN_PIECE_COLLISION_ERROR;
            } else {
                return 0;
            }
        } else {
            return INVALID_KNIGHT_MOVE;
        }
    }

    @Override
    public String toString() {
        return "N";
    }
}
