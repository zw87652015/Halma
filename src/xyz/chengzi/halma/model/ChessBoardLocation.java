package xyz.chengzi.halma.model;

public class ChessBoardLocation {
    private int row, column;

    public ChessBoardLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
