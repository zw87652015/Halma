package xyz.chengzi.halma.model;

import java.awt.*;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChessPiece implements Serializable{
    private Color color;
    private ImageIcon pieceIcon;
    private JLabel iconLabel;
    private JFrame iconFrame;

    public ChessPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public JLabel getIconLabel() {
        return iconLabel;
    }
    public ImageIcon getPieceIcon() {
        return pieceIcon;
    }
    public JFrame getIconFrame() {
        return iconFrame;
    }
}
