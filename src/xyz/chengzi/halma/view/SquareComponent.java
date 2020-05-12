package xyz.chengzi.halma.view;

import javax.swing.*;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;

import java.awt.*;

public class SquareComponent extends JPanel {
    private Color color;
    private JTextField currentPlayerText;

    public JTextField getCurrentPlayerText() {return currentPlayerText;}
    public void setCurrentPlayerText(JTextField currentPlayerText) {this.currentPlayerText = currentPlayerText;}

    public SquareComponent(int size, Color color) {
        setLayout(new GridLayout(1, 1)); // Use 1x1 grid layout.
        setSize(size, size);
        this.color = color;
    }

    public SquareComponent(int size, Color color, String text) {
        setLayout(new GridLayout(1, 1)); // Use 1x1 grid layout.
        setSize(size, size);
        this.color = color;
        currentPlayerText = new JTextField("RED's turn");
        currentPlayerText.setEditable(false);
        currentPlayerText.setHorizontalAlignment(JTextField.CENTER);
        this.add(currentPlayerText);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSquare(g);
    }

    private void paintSquare(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}
