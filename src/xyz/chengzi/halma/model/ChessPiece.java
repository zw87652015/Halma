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
        /*if(color == ChessBoard.color1) {this.pieceIcon = new ImageIcon("A.png");}
        if(color == ChessBoard.color2) {this.pieceIcon = new ImageIcon("B.png");}
        if(color == ChessBoard.color3) {this.pieceIcon = new ImageIcon("C.png");}
        if(color == ChessBoard.color4) {this.pieceIcon = new ImageIcon("D.png");}
        iconLabel = new JLabel(pieceIcon);
        iconFrame.add(iconLabel);
        iconFrame.setSize(pieceIcon.getIconWidth(), pieceIcon.getIconHeight());*/
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
