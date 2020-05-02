package xyz.chengzi.halma.model;

import java.awt.*;
import java.io.Serializable;

public class ChessPiece implements Serializable{
    private Color color;

    public ChessPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
