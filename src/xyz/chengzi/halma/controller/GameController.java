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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static javax.swing.JOptionPane.showMessageDialog;

public class GameController implements GameListener {
    private String id = "";
    private ChessBoardComponent view;
    private ChessBoard model;
    private static ChessComponent chessComponent;
    public static ChessBoard mod;
    private boolean jumpcontinue=false;
    public static GameController gc;
    private Color currentPlayer;
    private Color lastPlayer;
    private ChessPiece selectedPiece;
    private ChessBoardLocation selectedLocation;
    private HashMap<Color, Integer> stepsMap = new HashMap<>();
    public ArrayList<ChessBoardLocation> locationList=new ArrayList<ChessBoardLocation>();
    public static HashMap<String, GameController> controllerlList = new HashMap<>();

    public Color getCurrentPlayer() {return currentPlayer;}
    public HashMap<Color, Integer> getStepsMap() {return stepsMap;}
    public Color getLastPlayer() {return lastPlayer;}
    public String getId() {return id;}

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
        this.id = boardComponent.getId();
        GameController.controllerlList.put(id, this);
        initGameState();
    }
    
    public GameController(ChessBoardComponent boardComponent, ChessBoard chessBoard, Color nextPlayer, boolean isReload) {
        this.view = boardComponent;
        this.model = chessBoard;
        this.currentPlayer = nextPlayer;
        gc=this;
        mod = model;
        view.registerListener( this );
        this.stepsMap = chessBoard.getStepsMap();
        this.id = boardComponent.getId();
        GameController.controllerlList.put(id, this);
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

    public Color nextPlayer(boolean isFourMan) {
        isVictory();
        lastPlayer = currentPlayer;
        if (model.color1.equals( currentPlayer )) {
            if (model.fourman) {
                currentPlayer = model.color2;
                view.setTextField(isFourMan, this.id);
                view.setNextPlayerFigureField(isFourMan, this.id);
                model.setStepsMap(stepsMap);
                mod.setNextPlayer(currentPlayer);
                view.repaint();
                lastPlayer = currentPlayer;
                return currentPlayer;
            } else {
                currentPlayer = model.color3;
            }
        } else if (model.color2.equals( currentPlayer )) {
            currentPlayer = model.color3;
            view.setTextField(isFourMan, this.id);
            view.setNextPlayerFigureField(isFourMan, this.id);
            model.setStepsMap(stepsMap);
            mod.setNextPlayer(currentPlayer);
            view.repaint();
            lastPlayer = currentPlayer;
            return currentPlayer;
        } else if (model.color3.equals( currentPlayer )) {
            if (model.fourman) {
                currentPlayer = model.color4;
                view.setTextField(isFourMan, this.id);
                view.setNextPlayerFigureField(isFourMan, this.id);
                model.setStepsMap(stepsMap);
                mod.setNextPlayer(currentPlayer);
                view.repaint();
                lastPlayer = currentPlayer;
                return currentPlayer;
            } else {
                currentPlayer = model.color1;
                view.setTextField(isFourMan, this.id);
                view.setNextPlayerFigureField(isFourMan, this.id);
                model.setStepsMap(stepsMap);
                mod.setNextPlayer(currentPlayer);
                view.repaint();
                return currentPlayer;
            }
        } else if (model.color4.equals( currentPlayer )) {
            currentPlayer = model.color1;
            view.setTextField(isFourMan, this.id);
            view.setNextPlayerFigureField(isFourMan, this.id);
            model.setStepsMap(stepsMap);
            mod.setNextPlayer(currentPlayer);
            view.repaint();
            return currentPlayer;
        }
        view.setTextField(isFourMan, this.id);
        view.setNextPlayerFigureField(isFourMan, this.id);
        //model.setSteps(model.getSteps() + 1);
        model.setNextPlayer(currentPlayer);
        mod.setNextPlayer(currentPlayer);
        model.setStepsMap(stepsMap);
        view.repaint();
        return currentPlayer;
    }

    public Color lastPlayer(boolean isFourMan) {
        currentPlayer = model.getChainTable_color().get(model.getChainTable_color().size()-1);
        model.getChainTable_color().remove(model.getChainTable_color().size()-1);
        /*
        if (model.color1.equals( currentPlayer )) {
            if (model.fourman) {
                currentPlayer = model.color4;
                view.setTextField(isFourMan, this.id);
                view.setNextPlayerFigureField(isFourMan, this.id);
                model.setStepsMap(stepsMap);
                mod.setNextPlayer(currentPlayer);
                view.repaint();
                return currentPlayer;
            } else {
                currentPlayer = model.color3;
            }
        } else if (model.color2.equals( currentPlayer )) {
            currentPlayer = model.color1;
            view.setTextField(isFourMan, this.id);
            view.setNextPlayerFigureField(isFourMan, this.id);
            model.setStepsMap(stepsMap);
            mod.setNextPlayer(currentPlayer);
            view.repaint();
            return currentPlayer;
        } else if (model.color3.equals( currentPlayer )) {
            if (model.fourman) {
                currentPlayer = model.color2;
                view.setTextField(isFourMan, this.id);
                view.setNextPlayerFigureField(isFourMan, this.id);
                model.setStepsMap(stepsMap);
                mod.setNextPlayer(currentPlayer);
                view.repaint();
                return currentPlayer;
            } else {
                currentPlayer = model.color1;
                view.setTextField(isFourMan, this.id);
                view.setNextPlayerFigureField(isFourMan, this.id);
                model.setStepsMap(stepsMap);
                mod.setNextPlayer(currentPlayer);
                view.repaint();
                lastPlayer = currentPlayer;
                return currentPlayer;
            }
        } else if (model.color4.equals( currentPlayer )) {
            currentPlayer = model.color3;
            view.setTextField(isFourMan, this.id);
            view.setNextPlayerFigureField(isFourMan, this.id);
            model.setStepsMap(stepsMap);
            mod.setNextPlayer(currentPlayer);
            view.repaint();
            lastPlayer = currentPlayer;
            return currentPlayer;
        }*/
        view.setTextField(isFourMan, this.id);
        view.setNextPlayerFigureField(isFourMan, this.id);
        model.setNextPlayer(currentPlayer);
        mod.setNextPlayer(currentPlayer);
        model.setStepsMap(stepsMap);
        view.repaint();
        lastPlayer = currentPlayer;
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
                
                model.setLastMove(model.getCurrentMove());
                model.setCurrentMove(selectedLocation);
                model.getChainTable().add(new ArrayList<ChessBoardLocation>());
                model.getChainTable().get(model.getIndex()).add(selectedLocation);
                model.getChainTable().get(model.getIndex()).add(location);
                model.getChainTable_color().add(selectedPiece.getColor());
                model.setIndex(model.getIndex() + 1);

                view.setChessAtGrid( location, selectedPiece.getColor() );
                view.removeChessAtGrid( selectedLocation );
                /*
                model.setCurrentMove(new ChessBoardLocation());
                model.setLastMove(new ChessBoardLocation());
                */
                stepsMap.put(currentPlayer, stepsMap.get(currentPlayer) + 1);
                view.repaint();
                mod = model;
                selectedLocation = null;
                selectedPiece = null;
                jumpcontinue = false;
                nextPlayer(model.fourman);
                return;
            }
        }
        if (isjump( location ) == false) {
            if (jumpcontinue == false) {
                if (selectedLocation != null && model.isValidMove( selectedLocation, location )) {
                    model.moveChessPiece( selectedLocation, location );
                    
                    model.setLastMove(model.getCurrentMove());
                    model.setCurrentMove(selectedLocation);
                    model.getChainTable().add(new ArrayList<ChessBoardLocation>());
                    model.getChainTable().get(model.getIndex()).add(selectedLocation);
                    model.getChainTable().get(model.getIndex()).add(location);
                    model.getChainTable_color().add(selectedPiece.getColor());
                    model.setIndex(model.getIndex() + 1);

                    view.setChessAtGrid( location, selectedPiece.getColor() );
                    view.removeChessAtGrid( selectedLocation );
                    /*
                    model.setCurrentMove(new ChessBoardLocation());
                    model.setLastMove(new ChessBoardLocation());
                    */
                    stepsMap.put(currentPlayer, stepsMap.get(currentPlayer) + 1);
                    view.repaint();
                    selectedPiece = null;
                    selectedLocation = null;
                    mod = model;
                    nextPlayer(model.fourman);
                }
            }
        } else {
            if (model.isjumpcanmove( selectedLocation, location )) {
                model.moveChessPiece( selectedLocation, location );

                model.setLastMove(model.getCurrentMove());
                model.setCurrentMove(selectedLocation);
                model.getChainTable().add(new ArrayList<ChessBoardLocation>());
                model.getChainTable().get(model.getIndex()).add(selectedLocation);
                model.getChainTable().get(model.getIndex()).add(location);
                model.getChainTable_color().add(selectedPiece.getColor());
                model.setIndex(model.getIndex() + 1);

                view.setChessAtGrid( location, selectedPiece.getColor() );
                view.removeChessAtGrid( selectedLocation );
                /*
                model.setCurrentMove(new ChessBoardLocation());
                model.setLastMove(new ChessBoardLocation());
                */
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
                    nextPlayer(model.fourman);
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
    public ChessBoard getModel() {
        return model;
    }

    public void isVictory() {
        if(currentPlayer.equals(null)) {return;}
        boolean b = true;
        ChessBoardLocation c;
        Judge:
        for (int i = 0; i < model.getPricenumber(); i++) {
            for (int j = 0; j < model.getPricenumber(); j++) {
                if (i + j < model.getPricenumber()) {
                    if (currentPlayer.equals(model.color1)) {
                        c = new ChessBoardLocation( model.getDimension() - i - 1, model.getDimension() - j - 1 );
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor().equals(model.color1) == false) {
                            b = false;
                            break Judge;
                        }
                    }
                    if (currentPlayer.equals(model.color3)) {
                        c = new ChessBoardLocation( i, j );
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor().equals(model.color3) == false) {
                            b = false;
                            break Judge;
                        }

                    }
                    if (currentPlayer.equals(model.color2)) {
                        c = new ChessBoardLocation( i, model.getDimension() - j - 1 );
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor().equals(model.color2) == false) {
                            b = false;
                            break Judge;
                        }
                    }
                    if (currentPlayer.equals(model.color4)) {
                        c = new ChessBoardLocation( model.getDimension() - i - 1, j);
                        if(model.getChessPieceAt( c )==null){b=false;break Judge;}
                        if (model.getChessPieceAt( c ).getColor().equals(model.color4) == false) {
                            b = false;
                            break Judge;
                        }
                    }
                }
            }
        }
        String winplayer=null;
        if(b==true){
            if(GameFrame.isFourMan) {
                if(currentPlayer.equals(model.color1)){winplayer="player1";}
                if(currentPlayer.equals(model.color2)){winplayer="player2";}
                if(currentPlayer.equals(model.color3)){winplayer="player3";}
                if(currentPlayer.equals(model.color4)){winplayer="player4";}
            } else {
                if(currentPlayer.equals(model.color1)){winplayer="player1";}
                if(currentPlayer.equals(model.color3)){winplayer="player2";}
            }
            
            showMessageDialog(null,"Congratulation: "+winplayer+" is win!!!");}
    }
    public void canarrive(ChessBoardLocation location){
        int x=location.getColumn();
        int y=location.getRow();
        ChessBoardLocation c=new ChessBoardLocation( x-1,y-1 );
        if(x-1>=0){
            if(y-1>=0){
                jumplocation( c,-1,-1 );
                if( jumpcontinue==false)
                addLocation( c);}
        }
         c=new ChessBoardLocation( x-1,y );
        if(x-1>=0){
            jumplocation( c,-1,0);
            if( jumpcontinue==false)
                addLocation( c);
        }
        c=new ChessBoardLocation( x-1,y+1 );
        if(x-1>=0){
            if(y+1<model.getDimension()){
                jumplocation( c,-1,1 );
                if( jumpcontinue==false)
                addLocation( c);}
        }
         c=new ChessBoardLocation( x,y-1 );
            if(y-1>=0){
                jumplocation( c,0,-1 );
                if( jumpcontinue==false)
                addLocation( c);
        }
         c=new ChessBoardLocation( x,y+1 );
            if(y+1<model.getDimension()){
                jumplocation( c,0,1 );
                if( jumpcontinue==false)
                addLocation( c);
        }
         c=new ChessBoardLocation( x+1,y-1 );
        if(x+1<model.getDimension()){
            if(y-1>=0){
                jumplocation( c,1,-1 );
                if( jumpcontinue==false)
                addLocation( c);}
        }
        c=new ChessBoardLocation( x+1,y );
        if(x+1<model.getDimension()){
            jumplocation( c,1,0 );
            if( jumpcontinue==false)
                addLocation( c);
        }
         c=new ChessBoardLocation( x+1,y+1 );
        if(x+1<model.getDimension()){
            if(y+1>model.getDimension()){
                jumplocation( c,1,1 );
                if( jumpcontinue==false)
                addLocation( c);}
        }
    }
    public void addLocation(ChessBoardLocation location){
        if(model.getChessPieceAt( location )!=null){return;}
        if(locationList.size()!=0){
        for(int i=0;i<locationList.size();i++){if(locationList.get( i )==location)return;}}
        if(model.getChessPieceAt( location )==null)
        locationList.add( location );
    }
    public void deleteLocation(){locationList=null;}
    public void jumplocation(ChessBoardLocation location,int x,int y){
        ChessBoardLocation k=new ChessBoardLocation( location.getRow()+x,location.getColumn()+y );
        if(location.getColumn()+x<model.getDimension()||location.getRow()+x>=0||location.getColumn()+y>=0||location.getColumn()+y<model.getDimension()){
        if(model.getChessPieceAt( location )==null){
            return;
        }else {
            if(model.getChessPieceAt( k )==null){
                addLocation( k );
            }
            return;
        }}
    }
}
