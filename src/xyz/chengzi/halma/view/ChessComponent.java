package xyz.chengzi.halma.view;

import javax.swing.*;

import xyz.chengzi.halma.model.ChessBoard;

import java.awt.*;

public class ChessComponent extends JComponent {
    private Color color;
    private boolean selected;

    public ChessComponent(Color color) {
        this.color = color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintChess(g);
    }

    private void paintChess(Graphics g) {
        int spacing = (int) (getWidth() * 0.05);
        if(color.equals(ChessBoard.color1)) {
            g.drawImage(new ImageIcon(getClass().getResource("images\\" + "A.png")).getImage(), spacing, spacing, getWidth()-spacing*2, getWidth()-spacing*2, this);
        }
        if(color.equals(ChessBoard.color2)) {
            g.drawImage(new ImageIcon(getClass().getResource("images\\" + "B.png")).getImage(), spacing, spacing, getWidth()-spacing*2, getWidth()-spacing*2, this);
        }
        if(color.equals(ChessBoard.color3)) {
            g.drawImage(new ImageIcon(getClass().getResource("images\\" + "C.png")).getImage(), spacing, spacing, getWidth()-spacing*2, getWidth()-spacing*2, this);
        }
        if(color.equals(ChessBoard.color4)) {
            g.drawImage(new ImageIcon(getClass().getResource("images\\" + "D.png")).getImage(), spacing, spacing, getWidth()-spacing*2, getWidth()-spacing*2, this);
        }

        /*((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);

        
        g.fillOval(spacing, spacing, getWidth() - 2 * spacing, getHeight() - 2 * spacing);*/

        if (selected) { // Draw a + sign in the center of the piece.
            g.setColor(new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue()));
            g.drawLine(getWidth() / 2, getHeight() / 4, getWidth() / 2, getHeight() * 3 / 4);
            g.drawLine(getWidth() / 4, getHeight() / 2, getWidth() * 3 / 4, getHeight() / 2);
        }
    }
}
