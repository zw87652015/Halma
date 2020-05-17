package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DuDang {
    public static void load(File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ChessBoard temp = (ChessBoard) ois.readObject();
            ois.close();
            SwingUtilities.invokeLater(() -> {
                ChessBoardComponent chessBoardComponent = new ChessBoardComponent(507, temp.getDimension(), temp);
                Color nextPlayer = null, temp_color;
                temp_color = temp.getChainTable_color().get(temp.getChainTable_color().size()-1);
                if(temp.fourman) {
                    if(temp_color.equals(ChessBoard.color1)) {nextPlayer = ChessBoard.color2;}
                    if(temp_color.equals(ChessBoard.color2)) {nextPlayer = ChessBoard.color3;}
                    if(temp_color.equals(ChessBoard.color3)) {nextPlayer = ChessBoard.color4;}
                    if(temp_color.equals(ChessBoard.color4)) {nextPlayer = ChessBoard.color1;}
                } else {
                    if(temp_color.equals(ChessBoard.color1)) {nextPlayer = ChessBoard.color3;}
                    if(temp_color.equals(ChessBoard.color3)) {nextPlayer = ChessBoard.color1;}
                }
                GameController controller = new GameController(chessBoardComponent, temp, nextPlayer, true);

                GameFrame loadedFrame = new GameFrame(chessBoardComponent, temp);
                loadedFrame.add(chessBoardComponent);
                JOptionPane.showMessageDialog(null, "读档成功！");
                loadedFrame.setVisible(true);
            });
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "读档失败。");
            ;
        }
    }

    public static void save(ChessBoard currentChessBoard, String path) {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "\\" + "cundang.save"));
            oos.writeObject(currentChessBoard);
            oos.close();
            JOptionPane.showMessageDialog(null, "存档成功！");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "存档失败。");
        }
    }

    public static void huiQi(String id) {
        System.out.println(id);
        GameController controller = GameController.controllerlList.get(id);
        ChessBoard model = GameController.controllerlList.get(id).getModel();
        ChessBoardComponent view = ChessBoardComponent.chessBoardComponentList.get(id);
        if(model.getIndex() > 0) {
            model.moveChessPiece( model.getChainTable().get(model.getIndex()-1).get(1), model.getChainTable().get(model.getIndex()-1).get(0) );
            view.setChessAtGrid( model.getChainTable().get(model.getIndex()-1).get(0), model.getChainTable_color().get(model.getIndex() - 1) ); 
            view.removeChessAtGrid( model.getChainTable().get(model.getIndex()-1).get(1) );
            model.getChainTable().remove(model.getChainTable().size()-2);
            controller.lastPlayer(model.fourman);
            controller.getStepsMap().put(controller.getLastPlayer(), controller.getStepsMap().get(controller.getLastPlayer()) - 1);
            model.setIndex(model.getIndex()-1);
            view.setTextField(model.fourman, id);
            view.setNextPlayerFigureField(model.fourman, id);
            view.repaint();
        }
    }
}