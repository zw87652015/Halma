package xyz.chengzi.halma.model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import xyz.chengzi.halma.view.ChessBoardComponent;

public class ChessBoard implements Serializable{

    private static final long serialVersionUID = 1546070631726276643L;
    private String id = "";
    private Square[][] grid;
    private int dimension;
    private int steps = 0;
    private Color nextPlayer;
    private HashMap<Color, Integer> stepsMap = new HashMap<>();
    public static Color color1 = Color.RED;
    public static Color color2 = Color.GREEN;
    public static Color color3 = Color.yellow;
    public static Color color4 = Color.blue;
    private int pricenumber=4;
    public boolean fourman=true;
    private ArrayList< ArrayList<ChessBoardLocation> > chainTable = new ArrayList<>();//链表
    private ArrayList<Color> chainTable_color = new ArrayList<>();//与链表对应的color
    private ChessBoardLocation currentMove = new ChessBoardLocation();
    private ChessBoardLocation lastMove = new ChessBoardLocation();
    private int index = 0;

    public int getPricenumber() {return pricenumber;}
    public void setNextPlayer(Color nextPlayer) {this.nextPlayer = nextPlayer;}
    public Color getNextPlayer() {return nextPlayer;}
    public void setStepsMap(HashMap<Color, Integer> stepsMap) {this.stepsMap = stepsMap;}
    public HashMap<Color, Integer> getStepsMap() {return stepsMap;}
    public int getDimension() {return dimension;}
    public int getSteps() {return this.steps;}
    public void setSteps(int steps) {this.steps = steps;}
    public Square[][] getGrid() {return grid;}
    public void setChainTable(ArrayList<ArrayList<ChessBoardLocation>> chainTable) {this.chainTable = chainTable;}
    public ArrayList<ArrayList<ChessBoardLocation>> getChainTable() {return chainTable;}
    public void setCurrentMove(ChessBoardLocation currentMove) {this.currentMove = currentMove;}
    public ChessBoardLocation getCurrentMove() {return currentMove;}
    public void setLastMove(ChessBoardLocation lastMove) {this.lastMove = lastMove;}
    public ChessBoardLocation getLastMove() {return lastMove;}
    public void setIndex(int index) {this.index = index;}
    public int getIndex() {return index;}
    public void setChainTable_color(ArrayList<Color> chainTable_color) {this.chainTable_color = chainTable_color;}
    public ArrayList<Color> getChainTable_color() {return chainTable_color;}
    public String getId() {return id;}
    
    public ChessBoard(int dimension,int pricenumber,boolean fourman, ChessBoardComponent chessBoardComponent) {
        chainTable.add(new ArrayList<ChessBoardLocation>());
        this.id = chessBoardComponent.getId();
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;
        this.fourman=fourman;
        this.pricenumber=pricenumber;
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
        for(int i=0;i<pricenumber;i++){
            for (int j=0;j<pricenumber;j++){
                if(i+j<pricenumber&&Math.abs(  (i-j) )<pricenumber-1)
                {grid[i][j].setPiece(new ChessPiece(color1));
                grid[dimension-i-1][dimension-j-1].setPiece(new ChessPiece(color3));
                if(fourman){
                    grid[i][dimension-j-1].setPiece(new ChessPiece(color4));
                    grid[dimension-i-1][j].setPiece(new ChessPiece(color2));
                }
                }
            }
        }
    }

    private void initPieces(ChessBoard meow) {
        this.grid=meow.getGrid();
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
