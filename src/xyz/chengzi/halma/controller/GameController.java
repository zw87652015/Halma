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
import java.util.HashMap;

import static javax.swing.JOptionPane.showMessageDialog;

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
    private HashMap<Color, Integer> stepsMap = new HashMap<>();

    public Color getCurrentPlayer() {return currentPlayer;}
    public HashMap<Color, Integer> getStepsMap() {return stepsMap;}


    public GameController(ChessBoardComponent boardComponent, ChessBoard chessBoard, Color nextPlayer) {
        this.view = boardComponent;
        this.model = chessBoard;
        this.currentPlayer =null;
        gc=this;
        mod = model;
        view.registerListener( this );
        this.stepsMap.put(ChessBoard.color1, 0);
        this.stepsMap.put(ChessBoard.color2, 0);
        this.stepsMap.put(ChessBoard.color3, 0);
        this.stepsMap.put(ChessBoard.color4, 0);
        initGameState();
    }

    public void initGameState() {
        for (int row = 0; row < model.getDimension(); row++) {
            for (int col = 0; col < model.getDimension(); col++) {
                ChessBoardLocation location = new ChessBoardLocation( row, col );
                ChessPiece piece = model.getChessPieceAt( location );
                if (piece != null) {
                    view.setChessAtGrid( location, piece.getColor() );
                }
            }
        }
        view.repaint();
    }

    public Color nextPlayer() {
        isVictory();
        if (model.color1.equals( currentPlayer )) {
            if (model.fourman) {
                currentPlayer = model.color2;
                view.setTextField(GameFrame.isFourMan);
                view.setNextPlayerFigureField(GameFrame.isFourMan);
                view.repaint();
                return currentPlayer;
            } else {
                currentPlayer = model.color3;
            }
        } else if (model.color2.equals( currentPlayer )) {
            currentPlayer = model.color3;
            view.setTextField(GameFrame.isFourMan);
            view.setNextPlayerFigureField(GameFrame.isFourMan);
            view.repaint();
            return currentPlayer;
        } else if (model.color3.equals( currentPlayer )) {
            if (model.fourman) {
                currentPlayer = model.color4;
                view.setTextField(GameFrame.isFourMan);
                view.setNextPlayerFigureField(GameFrame.isFourMan);
                view.repaint();
                return currentPlayer;
            } else {
                currentPlayer = model.color1;
                view.setTextField(GameFrame.isFourMan);
                view.setNextPlayerFigureField(GameFrame.isFourMan);
                view.repaint();
                return currentPlayer;
            }
        } else if (model.color4.equals( currentPlayer )) {
            currentPlayer = model.color1;
            view.setTextField(GameFrame.isFourMan);
            view.setNextPlayerFigureField(GameFrame.isFourMan);
            view.repaint();
            return currentPlayer;
        }
        view.setTextField(GameFrame.isFourMan);
        view.setNextPlayerFigureField(GameFrame.isFourMan);
        view.repaint();
        return currentPlayer;
    }

    public boolean isjump(ChessBoardLocation location) {
        return model.isjumpmove( selectedLocation, location );
    }
        
    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        if (jumpcontinue) {
            if (location.equals( selectedLocation )) {
                model.moveChessPiece( selectedLocation, location );
                view.setChessAtGrid( location, selectedPiece.getColor() );
                view.removeChessAtGrid( selectedLocation );
                stepsMap.put(currentPlayer, stepsMap.get(currentPlayer) + 1);
                view.repaint();
                mod = model;
                selectedLocation = null;
                selectedPiece = null;
                jumpcontinue = false;
                nextPlayer();
                return;
            }
        }
        if (isjump( location ) == false) {
            if (jumpcontinue == false) {
                if (selectedLocation != null && model.isValidMove( selectedLocation, location )) {
                    model.moveChessPiece( selectedLocation, location );
                    view.setChessAtGrid( location, selectedPiece.getColor() );
                    view.removeChessAtGrid( selectedLocation );
                    stepsMap.put(currentPlayer, stepsMap.get(currentPlayer) + 1);
                    view.repaint();
                    selectedPiece = null;
                    selectedLocation = null;
                    mod = model;
                    nextPlayer();
                }
            }
        } else {
            if (model.isjumpcanmove( selectedLocation, location )) {
                model.moveChessPiece( selectedLocation, location );
                view.setChessAtGrid( location, selectedPiece.getColor() );
                view.removeChessAtGrid( selectedLocation );
                stepsMap.put(currentPlayer, stepsMap.get(currentPlayer) + 1);
                view.repaint();
                selectedLocation = location;
                mod = model;
                jumpcontinue = true;
            } else {
                if (jumpcontinue) {
                    selectedLocation = null;
                    selectedPiece = null;
                    mod = model;
                    jumpcontinue = false;
                    nextPlayer();
                }
            }
        }
    }

    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        if (jumpcontinue == false) {
            ChessPiece piece = model.getChessPieceAt( location );
            if(currentPlayer==null){currentPlayer=piece.getColor();}
            DuDang.save( GameController.getChessBoard() );
            if (piece.getColor().equals( currentPlayer ) && (selectedPiece == piece || selectedPiece == null)) {
                if (selectedPiece == null) {
                    selectedPiece = piece;
                    selectedLocation = location;
                } else {
                    selectedPiece = null;
                    selectedLocation = null;
                }
                component.setSelected( !component.isSelected() );
                component.repaint();
            }
        }
    }

    public static ChessBoard getChessBoard() {
        return mod;
    }

    public void isVictory() {
        boolean b = true;
        ChessBoardLocation c;
        Judge:
        for (int i = 0; i < model.getPricenumber(); i++) {
            for (int j = 0; j < model.getPricenumber(); j++) {
                if (i + j < model.getPricenumber()) {
                    if (currentPlayer == model.color1) {
                        c = new ChessBoardLocation( model.getDimension() - i - 1, model.getDimension() - j - 1 );
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor() != model.color1) {
                            b = false;
                            break Judge;
                        }
                    }
                    if (currentPlayer == model.color3) {
                        c = new ChessBoardLocation( i, j );
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor() != model.color3) {
                            b = false;
                            break Judge;
                        }

                    }
                    if (currentPlayer == model.color2) {
                        c = new ChessBoardLocation( i, model.getDimension() - j - 1 );
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor() != model.color2) {
                            b = false;
                            break Judge;
                        }
                    }
                    if (currentPlayer == model.color4) {
                        c = new ChessBoardLocation( model.getDimension() - i - 1, j);
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor() != model.color4) {
                            b = false;
                            break Judge;
                        }
                    }
                }
            }
        }
        String winplayer=null;
        if(b==true){
            if(currentPlayer==model.color1){winplayer="player1";}
            if(currentPlayer==model.color2){winplayer="player2";}
            if(currentPlayer==model.color3){winplayer="player3";}
            if(currentPlayer==model.color4){winplayer="player4";}
            showMessageDialog(null,"Congratulation: "+winplayer+"is win!!!");}
    }
}
