package xyz.chengzi.halma.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Time;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame;

public class DuDang {
    public static void load(File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ChessBoard temp = (ChessBoard) ois.readObject();
            ois.close();
            SwingUtilities.invokeLater(() -> {
                ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 19);
                Color nextPlayer;
                if(temp.getSteps() %2 == 0) {
                    nextPlayer = Color.RED;
                } else {
                    nextPlayer = Color.GREEN;
                }
                GameController controller = new GameController(chessBoardComponent, temp, nextPlayer);

                GameFrame mainFrame = new GameFrame();
                mainFrame.add(chessBoardComponent);
                JOptionPane.showMessageDialog(null, "读档成功！");
                mainFrame.setVisible(true);
            });
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "读档失败。");
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
}