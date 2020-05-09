package xyz.chengzi.halma;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Halma {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessBoardComponent chessBoardComponent = new ChessBoardComponent(500, 19);
            ChessBoard chessBoard = new ChessBoard(19,4,false);
            GameController controller = new GameController(chessBoardComponent, chessBoard, Color.RED);
            
            GameFrame mainFrame = new GameFrame();
            mainFrame.add(chessBoardComponent);
            mainFrame.setVisible(true);
        });
    }
}
