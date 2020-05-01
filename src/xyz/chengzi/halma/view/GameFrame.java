package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.controller.DuDang;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("2020 CS102A Project Demo");
        setSize(776, 810);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(0, 760);
        statusLabel.setSize(200, 10);
        add(statusLabel);

        JButton button = new JButton("...");
        button.addActionListener((e) -> {
            JOptionPane.showMessageDialog(this, "Game loaded!");
            DuDang.load();
        });
        button.setLocation(740, 760);
        button.setSize(20, 12);
        add(button);
    }
}
