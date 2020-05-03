package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.controller.DuDang;

import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.MenuItemUI;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("2020 CS102A Project Demo");
        setSize(776, 830);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(0, 780);
        statusLabel.setSize(200, 10);
        add(statusLabel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu_Game = new JMenu("游戏");
        menuBar.add(menu_Game);

        JMenuItem menuItem_load = new JMenuItem("读档");
        menuItem_load.addActionListener((e) -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.showOpenDialog(new JLabel());
		    File file = jfc.getSelectedFile();
            DuDang.load(file);
        });
        menu_Game.add(menuItem_load);
        
        JMenuItem menuItem_save = new JMenuItem("存档");
        menuItem_save.addActionListener((e) -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showSaveDialog(new JLabel());
            File file = jfc.getSelectedFile();
            DuDang.save(GameController.getChessBoard(), file.getPath());
        });
        menu_Game.add(menuItem_save);
    }
}
