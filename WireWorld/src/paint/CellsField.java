package paint;

import java.awt.*;
import java.util.ArrayList;

public class CellsField {

    public final ArrayList<Cell> cells = new ArrayList<>();

    public void setCell(int x, int y, Color value) {
        Cell cell = find(x, y);
        if (cell != null) removeCell(cell);
        cell = new Cell(x, y, value);
        for (Cell friend : cells) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx == 0 && dy == 0) continue;
                    if (friend.isPosition(x + dx, y + dy)) {
                        cell.friends.add(friend);
                        friend.friends.add(cell);
                    }
                }
            }
        }
        cells.add(cell);
    }

    public void removeCell(int x, int y) {
        Cell cell = find(x, y);
        if (cell != null) {
            removeCell(cell);
        }
    }

    public void removeCell(Cell cell) {
        for (Cell friend : cell.friends) {
            friend.friends.remove(cell);
        }
        cell.friends.clear();
        cells.remove(cell);
    }

    private Cell find(int x, int y) {
        for (Cell cell : cells) {
            if (cell.isPosition(x, y)){
                return cell;
            }
        }
        return null;
    }

}
