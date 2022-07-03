package adet.finalsproject.t174.tictactoe;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int row;
    private int col;

    Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Coordinates{" + row + ", " + col + "}";
    }
}
