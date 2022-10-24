package paint;

import engine.GameObject;
import engine.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Camera extends JPanel implements GameObject {

    private double x, y;
    private int zoom = 20;
    private Color currentColor = Color.GREEN;
    private final CellsField cellsField = new CellsField();

    public Camera() {
        addKeyListener(Input.instance);
        addMouseListener(Input.instance);
        addMouseMotionListener(Input.instance);
        addMouseWheelListener(Input.instance);
        setFocusable(true);
    }

    private double startX, startY;
    public void tick() {
        zoom -= Input.wheel;
        if (zoom < 1) zoom = 1;
        if (Input.getKey(KeyEvent.VK_ALT)) {
            if (Input.getButtonDown(MouseEvent.BUTTON1)) {
                startX = x;
                startY = y;
            }
            if (Input.getButton(MouseEvent.BUTTON1)) {
                x = startX + (double) Input.delta.x / zoom;
                y = startY - (double) Input.delta.y / zoom;
            }
            return;
        }

        if (Input.getButton(MouseEvent.BUTTON1) || Input.getButton(MouseEvent.BUTTON3)) {
            Point current = Input.current;
            int x = (int) Math.floor((double) (current.x - getWidth() / 2) / zoom + 0.5 - this.x);
            int y = (int) Math.floor((double) (getHeight() / 2 - current.y) / zoom + 0.5 - this.y);

            if (Input.getButton(MouseEvent.BUTTON1)) cellsField.setCell(x, y, currentColor);
            else cellsField.removeCell(x, y);
        }
    }

    public ArrayList<Cell> getCells() {
        return cellsField.cells;
    }

    public void clear() {
        while (cellsField.cells.size() != 0) {
            cellsField.removeCell(cellsField.cells.get(0));
        }
    }


    public void setColor(Color color) {
        currentColor = color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(), getHeight());
        for (int i = 0; i < cellsField.cells.size(); i++) {
            Cell cell = cellsField.cells.get(i);
            g.setColor(cell.color);
            int x = getWidth() / 2 + (int) (zoom * (this.x + cell.x - 0.5));
            int y = getHeight() / 2 - (int) (zoom * (this.y + cell.y + 0.5));
            g.fillRect(x, y, zoom, zoom);
        }
    }
}
