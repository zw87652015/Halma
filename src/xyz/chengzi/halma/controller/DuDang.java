package xyz.chengzi.halma.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame;

public class DuDang {
    public static void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cundang.save"));
            ChessBoard temp = (ChessBoard) ois.readObject();
            ois.close();
            SwingUtilities.invokeLater(() -> {
                ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 19);
                ChessBoard chessBoard = new ChessBoard(temp);
                GameController controller = new GameController(chessBoardComponent, temp);

                GameFrame mainFrame = new GameFrame();
                mainFrame.add(chessBoardComponent);
                JOptionPane.showMessageDialog(null, "读档成功！");
                mainFrame.setVisible(true);
            });
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "读档失败！");
        }
    }
}