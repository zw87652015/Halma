package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardComponent extends JComponent {
    private static final Color BOARD_COLOR_1 = new Color(255, 255, 204);
    private static final Color BOARD_COLOR_2 = new Color(170, 170, 170);

    private List<GameListener> listenerList = new ArrayList<>();
    private SquareComponent[][] gridComponents;
    private int dimension;
    private int gridSize;

    public void setTextField() {
        String currentPlayer = "";
        System.out.println(GameController.gc.getCurrentPlayer());
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color1) {currentPlayer = "RED's turn";}
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color2) {currentPlayer = "GREEN's turn";}
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color3) {currentPlayer = "YELLOW's turn";}
        if(GameController.gc.getCurrentPlayer() == ChessBoard.color4) {currentPlayer = "BLUE's turn";}
        this.gridComponents[0][dimension].getCurrentPlayerText().setText(currentPlayer);
    }

    public ChessBoardComponent(int size, int dimension) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null); // Use absolute layout.
        setSize(size+100, size+100);

        this.gridComponents = new SquareComponent[dimension+1][dimension+1];
        this.dimension = dimension;
        this.gridSize = size / dimension;
        initGridComponents();
    }

    private void initGridComponents() {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                gridComponents[row][col] = new SquareComponent(gridSize,
                        (row + col) % 2 == 0 ? BOARD_COLOR_1 : BOARD_COLOR_2);
                gridComponents[row][col].setLocation(row * gridSize, col * gridSize);
                add(gridComponents[row][col]);
            }
        }
        gridComponents[0][dimension] = new SquareComponent(100, Color.WHITE, "");
        gridComponents[0][dimension].setLocation((dimension)*gridSize, 0);
        add(gridComponents[0][dimension]);
    }

    public SquareComponent getGridAt(ChessBoardLocation location) {
        return gridComponents[location.getRow()][location.getColumn()];
    }

    public void setChessAtGrid(ChessBoardLocation location, Color color) {
        removeChessAtGrid(location);
        getGridAt(location).add(new ChessComponent(color));
    }

    public void removeChessAtGrid(ChessBoardLocation location) {
        // Note re-validation is required after remove / removeAll.
        getGridAt(location).removeAll();
        getGridAt(location).revalidate();
    }

    private ChessBoardLocation getLocationByPosition(int x, int y) {
        return new ChessBoardLocation(x / gridSize, y / gridSize);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);

        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            ChessBoardLocation location = getLocationByPosition(e.getX(), e.getY());
            for (GameListener listener : listenerList) {
                if (clickedComponent.getComponentCount() == 0) {
                    listener.onPlayerClickSquare(location, (SquareComponent) clickedComponent);
                } else {
                    listener.onPlayerClickChessPiece(location, (ChessComponent) clickedComponent.getComponent(0));
                }
            }
        }
    }

    public void registerListener(GameListener listener) {
        listenerList.add(listener);
    }

    public void unregisterListener(GameListener listener) { listenerList.remove(listener);
    }
}
