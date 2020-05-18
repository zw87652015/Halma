package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ranklist extends JFrame {
    private String[][] data = {{"第一名",null},{"第二名",null},{"第三名",null},{"第四名",null}};
    private String[] dataTitle = {"名次","玩家"};
    private JTable jtable = new JTable(data, dataTitle);
    private JScrollPane jscrollpane = new JScrollPane(jtable);

    public Ranklist() {
        setTitle("排行榜");
        setVisible(true);
        setBounds(new Rectangle(607 + 50, 233 + 50, 300, 200));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(jscrollpane, BorderLayout.CENTER);


        if (GameController.virturepeople.size()==3){
            if (GameController.virturepeople.contains(Color.red)==false){GameController.virturepeople.add(Color.red);}
            if (GameController.virturepeople.contains(Color.green)==false){GameController.virturepeople.add(Color.green);}
            if (GameController.virturepeople.contains(Color.yellow)==false){GameController.virturepeople.add(Color.yellow);}
            if (GameController.virturepeople.contains(Color.blue)==false){GameController.virturepeople.add(Color.blue);}

        }

        ArrayList<Color> ccc = new ArrayList<>(4);
        ccc.add(Color.red);
        ccc.add(Color.green);
        ccc.add(Color.yellow);
        ccc.add(Color.blue);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (GameController.getVirturepeople().get(i)== ccc.get(j)) {
                    int x = j + 1;
                    data[i][1] = "player" + x;
                }
            }
        }
    }
}
