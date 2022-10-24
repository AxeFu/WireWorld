import engine.Game;
import engine.Input;
import paint.Camera;
import paint.Cell;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Main extends Game {

    private boolean isRunning = true;
    private final Camera camera = new Screen();

    public Main() {
        super("Cells");
        setUndecorated(true);
        add(camera);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(this);

        while (isRunning) {
            try {
                start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    boolean pause = true;
    public Color[] colors = {Color.YELLOW, Color.BLUE, Color.WHITE, Color.GREEN};
    @Override
    public void tick() {
        isRunning = !Input.getKeyDown(KeyEvent.VK_ESCAPE);
        if (!isRunning) stop();

        for (int i = 0; i <= 3; i++) {
            if (Input.getKeyDown(KeyEvent.getExtendedKeyCodeForChar(("" + (i+1)).charAt(0)))) {
                camera.setColor(colors[i]);
                break;
            }
        }

        if (Input.getKeyDown(KeyEvent.VK_C)) {
            camera.clear();
        }

        if (Input.getKeyDown(KeyEvent.VK_Z)) {
            stop();
            tick.setTpc(30);
            pause = false;
        }

        if (Input.getKeyDown(KeyEvent.VK_X)) {
            stop();
            tick.setTpc(1000);
            pause = true;
        }

        if (!pause) {
            gameTick();
        }
    }

    public void gameTick() {
        ArrayList<Cell> cells = camera.getCells();
        for (Cell cell : cells) {
            if (cell.color == colors[0]) {
                int count = 0;
                for (Cell friend : cell.friends) {
                    if (friend.color == colors[1]) {
                        count++;
                    }
                }
                if (count == 1 || count == 2) {
                    cell.nextColor = colors[1];
                }
            }
            if (cell.color == colors[1]) {
                cell.nextColor = colors[2];
            }
            if (cell.color == colors[2]) {
                cell.nextColor = colors[0];
            }
        }

        for (Cell cell : cells) cell.update();
    }

    public static void main(String[] args) {
        new Main();
    }
}
