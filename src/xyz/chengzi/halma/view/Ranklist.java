package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class Ranklist extends JFrame {
    private String[][] data = {{"第一名",null},{"第二名",null},{"第三名",null},{"第四名",null}};
    private String[] dataTitle = {"名次","玩家"};
    private JTable jtable = new JTable(data, dataTitle);
    private JScrollPane jscrollpane = new JScrollPane(jtable);

    public Ranklist(){
        setTitle("排行榜");
        setVisible(true);
        //setSize(300,300);
        setBounds(
                new Rectangle(607+50, 233+50, 300, 300)
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(jscrollpane,BorderLayout.CENTER);
        setFont(new Font("宋体", Font.PLAIN, 25));

        data[0][1] = String.valueOf(GameController.getVirturepeople().get(0));
        data[1][1] = String.valueOf(GameController.getVirturepeople().get(1));
        data[2][1] = String.valueOf(GameController.getVirturepeople().get(2));
        data[3][1] = String.valueOf(GameController.getVirturepeople().get(3));



    }
}
