import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public enum Player {
    WHITE, BLACK;

    @Override
    public String toString() {
        switch (this) {
            case WHITE: return "1";
            case BLACK: return "2";
            default: throw new NotImplementedException();
        }
    }
}
