package chex;

import chex.figures.Figure;

public class Piece {
    private Player player;
    private Figure figure;

    public Piece(Player player, Figure figure) {
        this.player = player;
        this.figure = figure;
    }

    public Player getPlayer() {
        return player;
    }

    public Figure getFigure() {
        return figure;
    }

    @Override
    public String toString() {
        return figure.toString() + player.toString();
    }

    public boolean belongsTo(Player player) {
        return this.player == player;
    }
}
