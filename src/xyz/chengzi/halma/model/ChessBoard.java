package xyz.chengzi.halma.model;

import java.awt.*;
import java.io.Serializable;

public class ChessBoard implements Serializable{

    private static final long serialVersionUID = 1546070631726276643L;
    private Square[][] grid;
    private int dimension;
    private int steps = 0;
    public Color color1=Color.green;
    public Color color2=Color.RED;

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
        grid[0][0].setPiece(new ChessPiece(color2));
        grid[0][1].setPiece(new ChessPiece(color2));
        grid[1][0].setPiece(new ChessPiece(color2));
        grid[dimension - 1][dimension - 1].setPiece(new ChessPiece(color1));
        grid[dimension - 1][dimension - 2].setPiece(new ChessPiece(color1));
        grid[dimension - 2][dimension - 1].setPiece(new ChessPiece(color1));
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
        steps++;
    }

    public int getDimension() {
        return dimension;
    }

    public int getSteps() {
        return this.steps;
    }

    public boolean isValidMove(ChessBoardLocation src, ChessBoardLocation dest) {
        /*if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
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
        return false;*/
        return true;
    }
    public boolean isjumpmove(ChessBoardLocation src, ChessBoardLocation dest){
        int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
        int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;
        if (Math.abs( rowDistance )>1||Math.abs( colDistance )>1){
            return true;
        }
        return false;
    }
    public boolean jumpdistance(int x){if(Math.abs( x )==0){return true;}if (Math.abs( x )==2){return true;}return false;}
    public boolean isjumpcanmove(ChessBoardLocation src, ChessBoardLocation dest){
        int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
        int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;
        if (jumpdistance( rowDistance )&&jumpdistance( colDistance )){
            ChessBoardLocation k=new ChessBoardLocation( (src.getRow()+dest.getRow())/2,(src.getColumn()+dest.getColumn())/2 );
            if (getChessPieceAt( k )!=null){
            return true;}else {return false;}
        }else{
            return false;
        }
    }
}
