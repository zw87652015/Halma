package xyz.chengzi.halma.model;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ChessBoard implements Serializable{

    private static final long serialVersionUID = 1546070631726276643L;
    private Square[][] grid;
    private int dimension;

    public ChessBoard(int dimension) {
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;

        initGrid();
        initPieces();
    }

    public ChessBoard(ChessBoard meow) {
        this.grid = new Square[meow.getDimension()][meow.getDimension()];
        this.dimension = meow.getDimension();
        initGrid();
        initPieces(meow);
    }

    private void initGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = new Square(new ChessBoardLocation(i, j));
            }
        }
    }

    private void initPieces() {
        // TODO: This is only a demo implementation.
        grid[0][0].setPiece(new ChessPiece(Color.RED));
        grid[0][1].setPiece(new ChessPiece(Color.RED));
        grid[1][0].setPiece(new ChessPiece(Color.RED));
        grid[dimension - 1][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
    }

    private void initPieces(ChessBoard meow) {
        this.grid=meow.getGrid();
    }

    public Square[][] getGrid() {
        return grid;
    }

    public Square getGridAt(ChessBoardLocation location) {
        return grid[location.getRow()][location.getColumn()];
    }

    public ChessPiece getChessPieceAt(ChessBoardLocation location) {
        return getGridAt(location).getPiece();
    }

    public void setChessPieceAt(ChessBoardLocation location, ChessPiece piece) {
        getGridAt(location).setPiece(piece);
    }

    public ChessPiece removeChessPieceAt(ChessBoardLocation location) {
        ChessPiece piece = getGridAt(location).getPiece();
        getGridAt(location).setPiece(null);
        return piece;
    }

    public void moveChessPiece(ChessBoardLocation src, ChessBoardLocation dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal halma move");
        }
        setChessPieceAt(dest, removeChessPieceAt(src));
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isValidMove(ChessBoardLocation src, ChessBoardLocation dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }
        int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
        int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;
        if (rowDistance != 0 && colDistance != 0 && Math.abs((double) rowDistance / colDistance) != 1.0) {
            return false;
        }
        if (Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1) {
            return true;
        }
        return false;
    }
}
