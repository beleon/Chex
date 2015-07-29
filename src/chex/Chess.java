package chex;

import chex.figures.*;

import java.util.LinkedList;
import java.util.List;

public class Chess {
    private Square[][] board;
    private int currentPlayer;
    private Tuple<Player> players;
    private List<Tuple<Vector2d>> moves;

    public Chess() {
        currentPlayer = 0;
        players = new Tuple<>(new Player(Color.WHITE), new Player(Color.BLACK));
        moves = new LinkedList<>();
        board = new Square[8][8];

        //Black first row.
        board[0][0] = new Square(new Piece(players.getY(), new Rook()));
        board[0][1] = new Square(new Piece(players.getY(), new Knight()));
        board[0][2] = new Square(new Piece(players.getY(), new Bishop()));
        board[0][3] = new Square(new Piece(players.getY(), new Queen()));
        board[0][4] = new Square(new Piece(players.getY(), new King()));
        board[0][5] = new Square(new Piece(players.getY(), new Bishop()));
        board[0][6] = new Square(new Piece(players.getY(), new Knight()));
        board[0][7] = new Square(new Piece(players.getY(), new Rook()));

        //Black second row.
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Square(new Piece(players.getY(), new Pawn()));
        }


        //Empty center.
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 8; i++) {
                board[j + 2][i] = new Square();
            }
        }


        //White first row.
        board[7][0] = new Square(new Piece(players.getX(), new Rook()));
        board[7][1] = new Square(new Piece(players.getX(), new Knight()));
        board[7][2] = new Square(new Piece(players.getX(), new Bishop()));
        board[7][3] = new Square(new Piece(players.getX(), new Queen()));
        board[7][4] = new Square(new Piece(players.getX(), new King()));
        board[7][5] = new Square(new Piece(players.getX(), new Bishop()));
        board[7][6] = new Square(new Piece(players.getX(), new Knight()));
        board[7][7] = new Square(new Piece(players.getX(), new Rook()));

        //White second row.
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Square(new Piece(players.getX(), new Pawn()));
        }
    }

    public boolean move(String fromString, String toString) {
        Vector2d from = Vector2d.fromChessNotation(fromString);
        Vector2d to = Vector2d.fromChessNotation(toString);

        if (validateMove(from, to)) {
            movePiece(from, to);
            moves.add(new Tuple<>(from, to));
            nextPlayer();
            return true;
        }
        return false;
    }

    private void movePiece(Vector2d from, Vector2d to) {
        Piece piece = board[from.getY()][from.getX()].removePiece();
        board[to.getY()][to.getX()].setPiece(piece);
    }

    private boolean validateMove(Vector2d from, Vector2d to) {
        Piece piece = board[from.getY()][from.getX()].getPiece();
        if (piece != null && piece.belongsTo(players.get(currentPlayer))) {
            return true;
        }
        return false;
    }

    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % 2;
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
        moves.stream().forEach(moveVector -> stringBuilder.append(Vector2d.toChessNotation(moveVector.getX())).append(" ")
                .append(Vector2d.toChessNotation(moveVector.getY())).append("\n"));
        if (moves.size() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
