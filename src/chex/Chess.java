package chex;

import chex.figures.*;

import java.util.LinkedList;
import java.util.List;

public class Chess {
    public static final int IN_CHECK_ERROR = 55;
    private Square[][] board;
    private int currentPlayer;
    private Tuple<Player> players;
    private List<Tuple<Vector2d>> moves;
    private int turnCount;

    public Chess() {
        currentPlayer = 0;
        turnCount = 1;
        players = new Tuple<>(new Player(Color.WHITE, new Vector2d(0, -1)),
                              new Player(Color.BLACK, new Vector2d(0, 1)));
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

    public int move(String fromString, String toString) {
        Vector2d from = Vector2d.fromChessNotation(fromString);
        Vector2d to = Vector2d.fromChessNotation(toString);

        int valCode = validateMove(from, to);

        if (valCode == 0 || valCode == Pawn.EN_PASSANT_MOVE || valCode == King.LEFT_SIDE_CASTLING_MOVE
                || valCode == King.RIGHT_SIDE_CASTLING_MOVE || valCode == Rook.VALID_ROOK_MOVE
                || valCode == King.VALID_KING_MOVE || valCode == Pawn.VALID_FF_MOVE) {
            if (new PretendEnv(board, from, to).isInCheck(players.get(currentPlayer))) {
                return IN_CHECK_ERROR;
            } else {
                if (valCode == Pawn.EN_PASSANT_MOVE) {
                    Vector2d enemyPawn = to.sub(players.get(currentPlayer).getDirection());
                    board[enemyPawn.getY()][enemyPawn.getX()] = new Square();
                } else if (valCode == King.LEFT_SIDE_CASTLING_MOVE) {
                    movePiece(from.sub(new Vector2d(4, 0)), from.sub(new Vector2d(1, 0)));
                    players.get(currentPlayer).setMovedLeftRook(true);
                    players.get(currentPlayer).setMovedKing(true);
                } else if (valCode == King.RIGHT_SIDE_CASTLING_MOVE) {
                    movePiece(from.add(new Vector2d(3, 0)), from.add(new Vector2d(1, 0)));
                    players.get(currentPlayer).setMovedRightRook(true);
                    players.get(currentPlayer).setMovedKing(true);
                } else if (valCode == Rook.VALID_ROOK_MOVE) {
                    if ((from.getY() == 0 && players.get(currentPlayer).getColor().equals(Color.BLACK))
                            || (from.getY() == 7 && players.get(currentPlayer).getColor().equals(Color.WHITE))) {
                        if (from.getX() == 0) {
                            players.get(currentPlayer).setMovedLeftRook(true);
                        } else if (from.getX() == 7) {
                            players.get(currentPlayer).setMovedRightRook(true);
                        }
                    }
                } else if (valCode == King.VALID_KING_MOVE) {
                    players.get(currentPlayer).setMovedKing(true);
                } else if (valCode == Pawn.VALID_FF_MOVE) {
                    players.get(currentPlayer).setFfByIndex(from.getX(), turnCount);
                }
                valCode = 0;
                movePiece(from, to);
                moves.add(new Tuple<>(from, to));
                nextPlayer();
                ++turnCount;
            }
        }
        return valCode;
    }

    private void movePiece(Vector2d from, Vector2d to) {
        Piece piece = board[from.getY()][from.getX()].removePiece();
        board[to.getY()][to.getX()].setPiece(piece);
    }

    private int validateMove(Vector2d from, Vector2d to) {
        Piece piece = board[from.getY()][from.getX()].getPiece();
        if (piece != null && piece.belongsTo(players.get(currentPlayer))) {
            int pieceValCode = piece.getFigure().validateMove(piece.getPlayer(), from, to, board, turnCount);
            if (pieceValCode == 0) {
                return 0;
            } else {
                return pieceValCode;
            }
        } else {
            return 1;
        }
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

    public void replace(String targetString, String playerString, String abbr) {
        Vector2d target = Vector2d.fromChessNotation(targetString);
        Player player;
        Piece newPiece;

        switch (playerString.toLowerCase().charAt(0)) {
            case 'w': player = players.getX(); break;
            case 'b': player = players.getY(); break;
            default: throw new IllegalArgumentException("Not a valid player.");
        }

        switch (abbr.toLowerCase().charAt(0)) {
            case 'k': newPiece = new Piece(player, new King()); break;
            case 'q': newPiece = new Piece(player, new Queen()); break;
            case 'b': newPiece = new Piece(player, new Bishop()); break;
            case 'n': newPiece = new Piece(player, new Knight()); break;
            case 'r': newPiece = new Piece(player, new Rook()); break;
            case 'p': newPiece = new Piece(player, new Pawn()); break;
            default: throw new IllegalArgumentException("Not a valid piece.");
        }

        board[target.getY()][target.getX()].setPiece(newPiece);
    }

    public void replace(String targetString) {
        Vector2d target = Vector2d.fromChessNotation(targetString);
        board[target.getY()][target.getX()].setPiece(null);
    }

    public List<Vector2d> moveInfo(Vector2d vector2d) {
        List<Vector2d> moveList = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Vector2d to = new Vector2d(i, j);
                int valCode = validateMove(vector2d, to);
                if (valCode == 0 || valCode == Pawn.EN_PASSANT_MOVE || valCode == King.LEFT_SIDE_CASTLING_MOVE
                || valCode == King.RIGHT_SIDE_CASTLING_MOVE || valCode == Rook.VALID_ROOK_MOVE
                || valCode == King.VALID_KING_MOVE || valCode == Pawn.VALID_FF_MOVE) {
                    moveList.add(to);
                }
            }
        }
        return moveList;
    }
}
