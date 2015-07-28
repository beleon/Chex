import java.util.LinkedList;
import java.util.List;

public class Chess {
    private Square[][] board;
    private Player currentPlayer;
    private List<Vector<Vector<Integer>>> moves;

    public Chess() {
        currentPlayer = Player.WHITE;
        moves = new LinkedList<>();
        board = new Square[8][8];

        //Black first row.
        board[0][0] = new Square(new Piece(Player.BLACK, Figure.ROOK));
        board[0][1] = new Square(new Piece(Player.BLACK, Figure.KNIGHT));
        board[0][2] = new Square(new Piece(Player.BLACK, Figure.BISHOP));
        board[0][3] = new Square(new Piece(Player.BLACK, Figure.QUEEN));
        board[0][4] = new Square(new Piece(Player.BLACK, Figure.KING));
        board[0][5] = new Square(new Piece(Player.BLACK, Figure.BISHOP));
        board[0][6] = new Square(new Piece(Player.BLACK, Figure.KNIGHT));
        board[0][7] = new Square(new Piece(Player.BLACK, Figure.ROOK));

        //Black second row.
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Square(new Piece(Player.BLACK, Figure.PAWN));
        }


        //Empty center.
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 8; i++) {
                board[j + 2][i] = new Square();
            }
        }


        //White first row.
        board[7][0] = new Square(new Piece(Player.WHITE, Figure.ROOK));
        board[7][1] = new Square(new Piece(Player.WHITE, Figure.KNIGHT));
        board[7][2] = new Square(new Piece(Player.WHITE, Figure.BISHOP));
        board[7][3] = new Square(new Piece(Player.WHITE, Figure.QUEEN));
        board[7][4] = new Square(new Piece(Player.WHITE, Figure.KING));
        board[7][5] = new Square(new Piece(Player.WHITE, Figure.BISHOP));
        board[7][6] = new Square(new Piece(Player.WHITE, Figure.KNIGHT));
        board[7][7] = new Square(new Piece(Player.WHITE, Figure.ROOK));

        //White second row.
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Square(new Piece(Player.WHITE, Figure.PAWN));
        }
    }

    public boolean move(String fromString, String toString) {
        Vector<Integer> from = Vector.fromChessNotation(fromString);
        Vector<Integer> to = Vector.fromChessNotation(toString);

        if (validateMove(from, to)) {
            movePiece(from, to);
            moves.add(new Vector<>(from, to));
            nextPlayer();
            return true;
        }
        return false;
    }

    private void movePiece(Vector<Integer> from, Vector<Integer> to) {
        Piece piece = board[from.getY()][from.getX()].removePiece();
        board[to.getY()][to.getX()].setPiece(piece);
    }

    private boolean validateMove(Vector<Integer> from, Vector<Integer> to) {
        Piece piece = board[from.getY()][from.getX()].getPiece();
        if (piece != null && piece.getPlayer() == currentPlayer) {
            return true;
        }
        return false;
    }

    private void nextPlayer() {
        if (currentPlayer == Player.BLACK) {
            currentPlayer = Player.WHITE;
        } else {
            currentPlayer = Player.BLACK;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("-----------------------------------------\n");
        for (Square[] line : board) {
            stringBuilder.append("| ");
            for (Square square : line) {
                stringBuilder.append(square).append(" | ");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);
            stringBuilder.append("\n-----------------------------------------\n");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public String moveListAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        moves.stream().forEach(moveVector -> stringBuilder.append(Vector.toChessNotation(moveVector.getX())).append(" ")
                .append(Vector.toChessNotation(moveVector.getY())).append("\n"));
        if (moves.size() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
