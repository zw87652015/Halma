package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.controller.DuDang;

import java.io.File;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GameFrame extends JFrame {

    private JPanel jp;
    //private JPanel wanJiaShuPanel;
    private JTextField xuanZeWanJiaShu;
    private ButtonGroup wanJiaShuGroup;
    private JRadioButton shuangRen, siRen;
    //private JPanel qiZiShuPanel;
    private JTextField xuanZeChuShiQiZiShu;
    private ButtonGroup qiZiShuGroup;
    private JRadioButton three, four, five;
    private JButton startButton;

    public boolean isFourMan = false;
    public int rowsOfPieces = 3;

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

        JMenuItem menuItem_load = new JMenuItem("读取存档...");
        menuItem_load.addActionListener((e) -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.showOpenDialog(new JLabel());
		    File file = jfc.getSelectedFile();
            DuDang.load(file);
        });
        menu_Game.add(menuItem_load);
        
        JMenuItem menuItem_save = new JMenuItem("保存游戏...");
        menuItem_save.addActionListener((e) -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.showSaveDialog(new JLabel());
            File file = jfc.getSelectedFile();
            DuDang.save(GameController.getChessBoard(), file.getPath());
        });
        menu_Game.add(menuItem_save);
        
        JMenuItem menuItem_huiQi = new JMenuItem("悔棋");
        menuItem_huiQi.addActionListener((e) -> {
            try{
                DuDang.huiQi();
            } catch (Exception ex) {
            }
        });
        menu_Game.add(menuItem_huiQi);
    }

    private class RadioButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == shuangRen) {isFourMan = false;}
            if(ae.getSource() == siRen) {isFourMan = true;}
            if(ae.getSource() == three) {rowsOfPieces = 3;}
            if(ae.getSource() == four) {rowsOfPieces = 4;}
            if(ae.getSource() == five) {rowsOfPieces = 5;}
            if(ae.getSource() == startButton) {
                SwingUtilities.invokeLater(() -> {
                    ChessBoardComponent chessBoardComponent = new ChessBoardComponent(500, 19);
                    ChessBoard chessBoard = new ChessBoard(19, rowsOfPieces, isFourMan);
                    GameController controller = new GameController(chessBoardComponent, chessBoard, Color.RED);
                    
                    GameFrame mainFrame = new GameFrame();
                    mainFrame.add(chessBoardComponent);
                    mainFrame.setVisible(true);
                });
            }
        }
    }

    public GameFrame(String starterFrame) {
        super("HHHHHalma");
        RadioButtonListener listener = new RadioButtonListener();
        setSize(300, 200);
        this.setLocationRelativeTo(null);

        jp = new JPanel();
        jp.setLayout(new GridLayout(0,1));
        xuanZeWanJiaShu = new JTextField("Number of players");
        xuanZeWanJiaShu.setEditable(false);
        xuanZeWanJiaShu.setHorizontalAlignment(JTextField.CENTER);
        xuanZeWanJiaShu.setBounds(0, 0, 10, 5);
        jp.add(xuanZeWanJiaShu);
        wanJiaShuGroup = new ButtonGroup();
        shuangRen = new JRadioButton("2 players", true);
        shuangRen.setBounds(10, 0, 10, 5);
        siRen = new JRadioButton("4 players");
        siRen.setBounds(10, 10, 10, 5);
        wanJiaShuGroup.add(shuangRen);
        wanJiaShuGroup.add(siRen);
        jp.add(shuangRen);
        jp.add(siRen);
        shuangRen.addActionListener(listener);
        siRen.addActionListener(listener);

        xuanZeChuShiQiZiShu = new JTextField("Rows of chess pieces for each player");
        xuanZeChuShiQiZiShu.setEditable(false);
        xuanZeChuShiQiZiShu.setHorizontalAlignment(JTextField.CENTER);
        xuanZeChuShiQiZiShu.setBounds(100, 0, 10, 5);
        jp.add(xuanZeChuShiQiZiShu);
        qiZiShuGroup = new ButtonGroup();
        three = new JRadioButton("3 rows",true);
        three.setBounds(10, 50, 10, 5);
        four = new JRadioButton("4 rows");
        four.setBounds(10, 60, 10, 5);
        five = new JRadioButton("5 rows");
        five.setBounds(10, 70, 10, 5);
        qiZiShuGroup.add(three);
        qiZiShuGroup.add(four);
        qiZiShuGroup.add(five);
        jp.add(three);
        jp.add(four);
        jp.add(five);
        three.addActionListener(listener);
        four.addActionListener(listener);
        five.addActionListener(listener);

        startButton = new JButton("Start game!");
        jp.add(startButton);
        startButton.addActionListener(listener);

        add(jp);
    }
}
