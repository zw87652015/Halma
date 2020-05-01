package xyz.chengzi.halma.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.SwingUtilities;

import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame;

public class DuDang {
    public static ChessBoard load() {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cundang.save"));
            ChessBoard temp = (ChessBoard) ois.readObject();
            ois.close();
            SwingUtilities.invokeLater(() -> {
                ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 19);
                ChessBoard chessBoard = new ChessBoard(temp);
                GameController controller = new GameController(chessBoardComponent, chessBoard);
    
                GameFrame mainFrame = new GameFrame();
                mainFrame.add(chessBoardComponent);
                mainFrame.setVisible(true);
                
            });
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}