package chex;

public class Square {
    private Piece piece;

    public Square() {
    }

    public Square(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece removePiece() {
        Piece piece = this.piece;
        this.piece = null;
        return piece;
    }

    @Override
    public String toString() {
        if (piece == null) {
            return "  ";
        }
        return piece.toString();
    }
}
