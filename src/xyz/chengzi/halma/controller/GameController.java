package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.SquareComponent;

import java.awt.*;

public class GameController implements GameListener {
    private ChessBoardComponent view;
    private ChessBoard model;
    private static ChessComponent chessComponent;
    public static ChessBoard mod;
    private boolean jumpcontinue=false;

    private Color currentPlayer;
    private ChessPiece selectedPiece;
    private ChessBoardLocation selectedLocation;

    public GameController(ChessBoardComponent boardComponent, ChessBoard chessBoard, Color nextPlayer) {
        this.view = boardComponent;
        this.model = chessBoard;
        this.currentPlayer = nextPlayer;
        view.registerListener(this);
        initGameState();
    }

    public void initGameState() {
        for (int row = 0; row < model.getDimension(); row++) {
            for (int col = 0; col < model.getDimension(); col++) {
                ChessBoardLocation location = new ChessBoardLocation(row, col);
                ChessPiece piece = model.getChessPieceAt(location);
                if (piece != null) {
                    view.setChessAtGrid(location, piece.getColor());
                }
            }
        }
        view.repaint();
    }

    public Color nextPlayer() {
        return currentPlayer = currentPlayer == Color.RED ? Color.GREEN : Color.RED;
    }

    public boolean isjump(ChessBoardLocation location){
        return model.isjumpmove( selectedLocation,location ); }
    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        if (jumpcontinue){
            if (location.equals( selectedLocation )){
                model.moveChessPiece(selectedLocation, location);
                view.setChessAtGrid(location, selectedPiece.getColor());
                view.removeChessAtGrid(selectedLocation);
                view.repaint();
                selectedLocation=null;
                selectedPiece=null;
                jumpcontinue=false;
                nextPlayer();
                return;
            }
        }
        if (isjump( location )==false){
            if (jumpcontinue==false){
        if (selectedLocation != null && model.isValidMove(selectedLocation, location)) {
            model.moveChessPiece(selectedLocation, location);
            view.setChessAtGrid(location, selectedPiece.getColor());
            view.removeChessAtGrid(selectedLocation);
            view.repaint();
            selectedPiece = null;
            selectedLocation = null;
            mod=model;
            nextPlayer();}}
        }else {
            if (model.isjumpcanmove( selectedLocation,location )){
            model.moveChessPiece(selectedLocation, location);
            view.setChessAtGrid(location, selectedPiece.getColor());
            view.removeChessAtGrid(selectedLocation);
            view.repaint();
            selectedLocation = location;
            mod=model;
            jumpcontinue=true;
            }else {
                selectedLocation = null;
                selectedPiece=null;
                mod=model;
                jumpcontinue=false;
                nextPlayer();
            }
        }
    }

    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        if (jumpcontinue==false){
        ChessPiece piece = model.getChessPieceAt(location);
        if (piece.getColor().equals(currentPlayer) && (selectedPiece == piece || selectedPiece == null)) {

            if (selectedPiece == null) {
                selectedPiece = piece;
                selectedLocation = location;
            } else {
                selectedPiece = null;
                selectedLocation = null;
            }
            component.setSelected(!component.isSelected());
            component.repaint();
        }}
    }

    public static ChessBoard getChessBoard() {
        return mod;
    }

}
