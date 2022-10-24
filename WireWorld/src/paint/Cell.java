package paint;

import java.awt.*;
import java.util.ArrayList;

public final class Cell {

    public Color color;
    public final int x, y;
    public final ArrayList<Cell> friends = new ArrayList<>();
    public Cell(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        nextColor = color;
    }

    public final boolean isPosition(int x, int y) {
        return this.x == x && this.y == y;
    }

    public final boolean isColor(Color color) {
        return this.color == color;
    }

    public Color nextColor;
    public final void update() {
        color = nextColor;
    }

}
