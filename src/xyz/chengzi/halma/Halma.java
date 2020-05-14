package xyz.chengzi.halma;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame;

import javax.swing.*;

public class Halma {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {            
            GameFrame starterFrame = new GameFrame("starterFrame");
            starterFrame.setVisible(true);
        });
    }
}
