import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum Figure {
    KING, QUEEN, ROOK, KNIGHT, BISHOP, PAWN;

    @Override
    public String toString() {
        switch (this) {
            case KING: return "K";
            case QUEEN: return "Q";
            case ROOK: return "R";
            case KNIGHT: return "N";
            case BISHOP: return "B";
            case PAWN: return "P";
            default: throw new NotImplementedException();
        }
    }
}
