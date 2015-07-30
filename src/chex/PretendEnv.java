package chex;

import chex.figures.King;

public class PretendEnv {
    private Square[][] board;
    private Vector2d from, to;


    public  PretendEnv(Square[][] board, Vector2d from, Vector2d to) {
        this.board = board;
        this.from = from;
        this.to = to;
    }

    public Square get(Vector2d point) {
        if (point == null) {
            return null;
        }
        if (to.equals(point)) {
            return board[from.getY()][from.getX()];
        }
        if (from.equals(point)) {
            return new Square();
        } else {
            return board[point.getY()][point.getX()];
        }
    }

    public Square get(int x, int y) {
        return get(new Vector2d(x, y));
    }

    public boolean isInCheck(Player player) {
        Vector2d kingPos = findKing(player);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = get(i, j);
                if (square.getPiece() != null
                    && !square.getPiece().belongsTo(player)
                    && square.getPiece().getFigure()
                        .canAttack(square.getPiece().getPlayer(), new Vector2d(i, j), kingPos, this)) {
                        return true;
                }
            }
        }
        return false;
    }

    private Vector2d findKing(Player player) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = get(i, j);
                if (square.getPiece() != null
                    && square.getPiece().getFigure() instanceof King
                    && square.getPiece().belongsTo(player)) {
                    return new Vector2d(i, j);
                }
            }
        }
        System.out.println("test");
        return null;
    }
}
