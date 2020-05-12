package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.GameFrame;
import xyz.chengzi.halma.view.SquareComponent;

import java.awt.*;

import javax.swing.JLabel;

public class GameController implements GameListener {
    private ChessBoardComponent view;
    private ChessBoard model;
    private static ChessComponent chessComponent;
    public static ChessBoard mod;
    private boolean jumpcontinue=false;
    public static GameController gc;

    private Color currentPlayer;
    private ChessPiece selectedPiece;
    private ChessBoardLocation selectedLocation;

    public Color getCurrentPlayer() {return currentPlayer;}

    public GameController(ChessBoardComponent boardComponent, ChessBoard chessBoard, Color nextPlayer) {
        this.view = boardComponent;
        this.model = chessBoard;
        this.currentPlayer = nextPlayer;
        gc=this;
        mod = model;
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

        if (model.color1.equals( currentPlayer )) {
            if(model.fourman){
            currentPlayer=model.color2;return currentPlayer;}
            else {currentPlayer=model.color3;}

        } else if (model.color2.equals( currentPlayer )) {

            currentPlayer=model.color3;return currentPlayer;


        } else if (model.color3.equals( currentPlayer )) {
            if(model.fourman){
            currentPlayer=model.color4;return currentPlayer;}
            else {currentPlayer=model.color1;return currentPlayer;}
        } else if (model.color4.equals( currentPlayer )) {
            currentPlayer=model.color1;return currentPlayer;
        }
        /*String currentPlayerString = "";
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color1) {currentPlayerString = "Player 1's turn";}
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color2) {currentPlayerString = "Player 2's turn";}
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color3) {currentPlayerString = "Player 3's turn";}
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color4) {currentPlayerString = "Player 4's turn";}*/
        view.setTextField();
        view.repaint();
        return currentPlayer;
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
                mod = model;
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
            mod = model;
            nextPlayer();}}
        }else {
            if (model.isjumpcanmove( selectedLocation,location )){
            model.moveChessPiece(selectedLocation, location);
            view.setChessAtGrid(location, selectedPiece.getColor());
            view.removeChessAtGrid(selectedLocation);
            view.repaint();
            selectedLocation = location;
            mod = model;
            jumpcontinue=true;
            }else {
                if(jumpcontinue){
                selectedLocation = null;
                selectedPiece=null;
                mod = model;
                jumpcontinue=false;
                nextPlayer();
            }}
        }
    }
    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        if (jumpcontinue==false){
        ChessPiece piece = model.getChessPieceAt(location);
        DuDang.save(GameController.getChessBoard());
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
