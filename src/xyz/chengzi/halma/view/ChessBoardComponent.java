package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;

public class ChessBoardComponent extends JComponent {
    private String id = "";
    private static final Color BOARD_COLOR_1 = new Color(255, 255, 204);
    private static final Color BOARD_COLOR_2 = new Color(170, 170, 170);
    private List<GameListener> listenerList = new ArrayList<>();
    private SquareComponent[][] gridComponents;
    private int dimension;
    private int gridSize;
    public static HashMap<String, ChessBoardComponent> chessBoardComponentList = new HashMap<>();
    
    public String getId() {return id;}

    public void setTextField(boolean isFourMan, String id) {
        GameController temp_controller = GameController.controllerlList.get(id);
        if (isFourMan) {
            if (GameController.controllerlList.get(id).getCurrentPlayer().equals(ChessBoard.color1)) {
                this.gridComponents[0][dimension].getCurrentPlayerText().setText("→");
                this.gridComponents[1][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[2][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[3][dimension].getCurrentPlayerText().setText("");

                this.gridComponents[0][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color1));
                this.gridComponents[1][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color2));
                this.gridComponents[2][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color3));
                this.gridComponents[3][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color4));
            }
            if (GameController.controllerlList.get(id).getCurrentPlayer().equals(ChessBoard.color2)) {
                this.gridComponents[0][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[1][dimension].getCurrentPlayerText().setText("→");
                this.gridComponents[2][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[3][dimension].getCurrentPlayerText().setText("");

                this.gridComponents[0][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color1));
                this.gridComponents[1][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color2));
                this.gridComponents[2][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color3));
                this.gridComponents[3][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color4));
            }
            if (GameController.controllerlList.get(id).getCurrentPlayer().equals(ChessBoard.color3)) {
                this.gridComponents[0][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[1][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[2][dimension].getCurrentPlayerText().setText("→");
                this.gridComponents[3][dimension].getCurrentPlayerText().setText("");

                this.gridComponents[0][dimension + 2].getCurrentPlayerText().setText(
                    "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color1));
                this.gridComponents[1][dimension + 2].getCurrentPlayerText().setText(
                    "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color2));
                this.gridComponents[2][dimension + 2].getCurrentPlayerText().setText(
                    "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color3));
                this.gridComponents[3][dimension + 2].getCurrentPlayerText().setText(
                    "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color4));
            }
            if (GameController.controllerlList.get(id).getCurrentPlayer().equals(ChessBoard.color4)) {
                this.gridComponents[0][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[1][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[2][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[3][dimension].getCurrentPlayerText().setText("→");

                this.gridComponents[0][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color1));
                this.gridComponents[1][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color2));
                this.gridComponents[2][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color3));
                this.gridComponents[3][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color4));
            }
        } else {
            if (GameController.controllerlList.get(id).getCurrentPlayer().equals(ChessBoard.color1)) {
                this.gridComponents[0][dimension].getCurrentPlayerText().setText("→");
                this.gridComponents[1][dimension].getCurrentPlayerText().setText("");

                this.gridComponents[0][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color1));
                this.gridComponents[1][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color3));
            }
            if (GameController.controllerlList.get(id).getCurrentPlayer().equals(ChessBoard.color3)) {
                this.gridComponents[0][dimension].getCurrentPlayerText().setText("");
                this.gridComponents[1][dimension].getCurrentPlayerText().setText("→");

                this.gridComponents[0][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color1));
                this.gridComponents[1][dimension + 2].getCurrentPlayerText().setText(
                        "MOVES: " + GameController.controllerlList.get(id).getStepsMap().get(ChessBoard.color3));
            }
        }
    }
    
    public void setNextPlayerFigureField(boolean isFourMan, String id) {
        if(isFourMan) {
            ChessBoardLocation lo = new ChessBoardLocation(0, dimension+1);
            setChessAtGrid(lo, ChessBoard.color1);
            lo = new ChessBoardLocation(1, dimension+1);
            setChessAtGrid(lo, ChessBoard.color2);
            lo = new ChessBoardLocation(2, dimension+1);
            setChessAtGrid(lo, ChessBoard.color3);
            lo = new ChessBoardLocation(3, dimension+1);
            setChessAtGrid(lo, ChessBoard.color4);
        } else {
            ChessBoardLocation lo = new ChessBoardLocation(0, dimension+1);
            setChessAtGrid(lo, ChessBoard.color1);
            lo = new ChessBoardLocation(1, dimension+1);
            setChessAtGrid(lo, ChessBoard.color3);
        }
    }

    public ChessBoardComponent(int size, int dimension) {
        this.id = new Date().toString();
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null); // Use absolute layout.
        setSize(size+300, size+100);

        this.gridComponents = new SquareComponent[dimension+3][dimension+3];
        this.dimension = dimension;
        this.gridSize = size / dimension;
        ChessBoardComponent.chessBoardComponentList.put(id, this);
        initGridComponents(GameFrame.isFourMan);
    }
    
    public ChessBoardComponent(int size, int dimension, ChessBoard cb) {
        id = new Date().toString();
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null); // Use absolute layout.
        setSize(size+300, size+100);

        this.gridComponents = new SquareComponent[dimension+3][dimension+3];
        this.dimension = dimension;
        this.gridSize = size / dimension;
        ChessBoardComponent.chessBoardComponentList.put(id, this);
        initGridComponents(cb.fourman, cb);
    }

    private void initGridComponents(boolean isFourMan, ChessBoard cb) {
        if (isFourMan) {
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    gridComponents[row][col] = new SquareComponent(gridSize,
                            (row + col) % 2 == 0 ? BOARD_COLOR_1 : BOARD_COLOR_2);
                    gridComponents[row][col].setLocation(row * gridSize, col * gridSize);
                    add(gridComponents[row][col]);
                }
            }

            Color nextPlayer = cb.getNextPlayer();
            if(nextPlayer.equals(ChessBoard.color1)) {
                gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
                gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[2][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[3][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            }
            if(nextPlayer.equals(ChessBoard.color2)) {
                gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
                gridComponents[2][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[3][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            }
            if(nextPlayer.equals(ChessBoard.color3)) {
                gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[2][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
                gridComponents[3][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            }
            if(nextPlayer.equals(ChessBoard.color4)) {
                gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[2][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[3][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
            }

            // Player
            // 11111111111111111111111111111111111111111111111111111111111111111111111111111111111
            // status label
            gridComponents[0][dimension].setLocation((dimension) * gridSize, 0);
            add(gridComponents[0][dimension]);
            gridComponents[0][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[0][dimension + 1].setLocation((dimension + 1) * gridSize, 0);
            add(gridComponents[0][dimension + 1]);
            gridComponents[0][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:" + cb.getStepsMap().get(ChessBoard.color1));
            gridComponents[0][dimension + 2].setLocation((dimension + 2) * gridSize, 0);
            add(gridComponents[0][dimension + 2]);

            // Player
            // 22222222222222222222222222222222222222222222222222222222222222222222222222222222222
            // status label
            gridComponents[1][dimension].setLocation((dimension) * gridSize, gridSize);
            add(gridComponents[1][dimension]);
            gridComponents[1][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[1][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize);
            add(gridComponents[1][dimension + 1]);
            gridComponents[1][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:" + cb.getStepsMap().get(ChessBoard.color2));
            gridComponents[1][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize);
            add(gridComponents[1][dimension + 2]);

            // Player
            // 33333333333333333333333333333333333333333333333333333333333333333333333333333333333
            // status label
            gridComponents[2][dimension].setLocation((dimension) * gridSize, gridSize * 2);
            add(gridComponents[2][dimension]);
            gridComponents[2][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[2][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize * 2);
            add(gridComponents[2][dimension + 1]);
            gridComponents[2][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:" + cb.getStepsMap().get(ChessBoard.color3));
            gridComponents[2][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize * 2);
            add(gridComponents[2][dimension + 2]);

            // Player
            // 44444444444444444444444444444444444444444444444444444444444444444444444444444444444
            // status label
            gridComponents[3][dimension].setLocation((dimension) * gridSize, gridSize * 3);
            add(gridComponents[3][dimension]);
            gridComponents[3][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[3][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize * 3);
            add(gridComponents[3][dimension + 1]);
            gridComponents[3][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:" + cb.getStepsMap().get(ChessBoard.color4));
            gridComponents[3][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize * 3);
            add(gridComponents[3][dimension + 2]);

        } else {
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    gridComponents[row][col] = new SquareComponent(gridSize,
                            (row + col) % 2 == 0 ? BOARD_COLOR_1 : BOARD_COLOR_2);
                    gridComponents[row][col].setLocation(row * gridSize, col * gridSize);
                    add(gridComponents[row][col]);
                }
            }

            Color nextPlayer = cb.getNextPlayer();
            if(nextPlayer.equals(ChessBoard.color1)) {
                gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
                gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            }
            if(nextPlayer.equals(ChessBoard.color3)) {
                gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
                gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
            }

            // Player
            // 11111111111111111111111111111111111111111111111111111111111111111111111111111111111
            // status label
            gridComponents[0][dimension].setLocation((dimension) * gridSize, 0);
            add(gridComponents[0][dimension]);
            gridComponents[0][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[0][dimension + 1].setLocation((dimension + 1) * gridSize, 0);
            add(gridComponents[0][dimension + 1]);
            gridComponents[0][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:" + cb.getStepsMap().get(ChessBoard.color1));
            gridComponents[0][dimension + 2].setLocation((dimension + 2) * gridSize, 0);
            add(gridComponents[0][dimension + 2]);

            // Player
            // 22222222222222222222222222222222222222222222222222222222222222222222222222222222222
            // status label
            gridComponents[1][dimension].setLocation((dimension) * gridSize, gridSize);
            add(gridComponents[1][dimension]);
            gridComponents[1][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[1][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize);
            add(gridComponents[1][dimension + 1]);
            gridComponents[1][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:" + cb.getStepsMap().get(ChessBoard.color3));
            gridComponents[1][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize);
            add(gridComponents[1][dimension + 2]);
        }
        this.setNextPlayerFigureField(isFourMan, this.id);
    }

    private void initGridComponents(boolean isFourMan) {
        if(isFourMan) {
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    gridComponents[row][col] = new SquareComponent(gridSize,
                            (row + col) % 2 == 0 ? BOARD_COLOR_1 : BOARD_COLOR_2);
                    gridComponents[row][col].setLocation(row * gridSize, col * gridSize);
                    add(gridComponents[row][col]);
                }
            }

            //Player 11111111111111111111111111111111111111111111111111111111111111111111111111111111111 status label
            gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
            gridComponents[0][dimension].setLocation((dimension) * gridSize, 0);
            add(gridComponents[0][dimension]);
            gridComponents[0][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[0][dimension + 1].setLocation((dimension + 1) * gridSize, 0);
            add(gridComponents[0][dimension + 1]);
            gridComponents[0][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:0");
            gridComponents[0][dimension + 2].setLocation((dimension + 2) * gridSize, 0);
            add(gridComponents[0][dimension + 2]);

            // Player
            // 22222222222222222222222222222222222222222222222222222222222222222222222222222222222
            // status label
            gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            gridComponents[1][dimension].setLocation((dimension) * gridSize, gridSize);
            add(gridComponents[1][dimension]);
            gridComponents[1][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[1][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize);
            add(gridComponents[1][dimension + 1]);
            gridComponents[1][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:0");
            gridComponents[1][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize);
            add(gridComponents[1][dimension + 2]);

            // Player
            // 33333333333333333333333333333333333333333333333333333333333333333333333333333333333
            // status label
            gridComponents[2][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            gridComponents[2][dimension].setLocation((dimension) * gridSize, gridSize * 2);
            add(gridComponents[2][dimension]);
            gridComponents[2][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[2][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize * 2);
            add(gridComponents[2][dimension + 1]);
            gridComponents[2][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:0");
            gridComponents[2][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize * 2);
            add(gridComponents[2][dimension + 2]);

            // Player
            // 44444444444444444444444444444444444444444444444444444444444444444444444444444444444
            // status label
            gridComponents[3][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            gridComponents[3][dimension].setLocation((dimension) * gridSize, gridSize * 3);
            add(gridComponents[3][dimension]);
            gridComponents[3][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[3][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize * 3);
            add(gridComponents[3][dimension + 1]);
            gridComponents[3][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:0");
            gridComponents[3][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize * 3);
            add(gridComponents[3][dimension + 2]);
        } else {
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    gridComponents[row][col] = new SquareComponent(gridSize,
                            (row + col) % 2 == 0 ? BOARD_COLOR_1 : BOARD_COLOR_2);
                    gridComponents[row][col].setLocation(row * gridSize, col * gridSize);
                    add(gridComponents[row][col]);
                }
            }
            // Player
            // 11111111111111111111111111111111111111111111111111111111111111111111111111111111111
            // status label
            gridComponents[0][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "→");
            gridComponents[0][dimension].setLocation((dimension) * gridSize, 0);
            add(gridComponents[0][dimension]);
            gridComponents[0][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[0][dimension + 1].setLocation((dimension + 1) * gridSize, 0);
            add(gridComponents[0][dimension + 1]);
            gridComponents[0][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:0");
            gridComponents[0][dimension + 2].setLocation((dimension + 2) * gridSize, 0);
            add(gridComponents[0][dimension + 2]);

            // Player
            // 22222222222222222222222222222222222222222222222222222222222222222222222222222222222
            // status label
            gridComponents[1][dimension] = new SquareComponent(gridSize, gridSize, Color.WHITE, "");
            gridComponents[1][dimension].setLocation((dimension) * gridSize, gridSize);
            add(gridComponents[1][dimension]);
            gridComponents[1][dimension + 1] = new SquareComponent(gridSize, Color.WHITE);
            gridComponents[1][dimension + 1].setLocation((dimension + 1) * gridSize, gridSize);
            add(gridComponents[1][dimension + 1]);
            gridComponents[1][dimension + 2] = new SquareComponent(gridSize * 3, gridSize, Color.WHITE, "MOVES:0");
            gridComponents[1][dimension + 2].setLocation((dimension + 2) * gridSize, gridSize);
            add(gridComponents[1][dimension + 2]);
        }
        
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
